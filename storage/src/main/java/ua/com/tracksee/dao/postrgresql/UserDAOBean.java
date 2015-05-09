package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.exceptions.ServiceUserNotFoundException;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Vadym Akymov
 * @author Ruslan Gunavardana
 * @author Katia Stetsiuk
 * @author Avlasov Sasha
 */
@Stateless
public class UserDAOBean implements UserDAO {
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;


    /**
     * @param partNumber - number of data part (from 1 to driver_count/DRIVERS_PAGE_SIZE)
     * @return list with part of drivers(default size of list if 10)
     */
    @Override
    public List<ServiceUserEntity> getDrivers(int partNumber) {
        if(partNumber <= 0) {
        logger.error("partNumber can't be <= 0");
        throw new IllegalArgumentException("partNumber can't be <= 0");
    }
    Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
            "WHERE driver = TRUE ORDER BY email LIMIT ?1 OFFSET ?2", ServiceUserEntity.class);
    query.setParameter(1, DRIVERS_PAGE_SIZE);
    query.setParameter(2, (partNumber - 1) * DRIVERS_PAGE_SIZE);
    return query.getResultList();
}

    /**
     * @author Katia Stetsiuk
     * @return list of users in system
     */
    @Override
    public List<ServiceUserEntity> getUsers() {
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user "
                , ServiceUserEntity.class);
        return query.getResultList();
    }

    @Override
    public List<String> getDriversEmails() {

        Query query = entityManager.createNativeQuery("SELECT email FROM service_user " +
                "WHERE driver = TRUE ", String.class);
        return query.getResultList();
    }

    /**
     * @author Katia Stetsiuk
     * @param email to customers drivers with such email
     * @return
     */
    @Override
    public List<ServiceUserEntity> getCustomersByEmail(String email) {
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
                "WHERE driver = FALSE and email LIKE ? ", ServiceUserEntity.class);
        query.setParameter(1 ,"%" + email + "%");
        return query.getResultList();
    }

    /**
     * @author Katia Stetsiuk
     * @param email to get drivers with such email
     * @return
     */
    @Override
    public List<ServiceUserEntity> getDriversByEmail(String email) {
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
                "WHERE driver = TRUE and email LIKE ? ", ServiceUserEntity.class);
        query.setParameter(1 ,"%" + email + "%");
        return query.getResultList();
    }

    @Override
    public Integer getUserIdByEmail(String email) {
        Query query = entityManager.createNativeQuery("SELECT user_id FROM service_user WHERE email=?1");
        query.setParameter(1, email);
        Integer result;
        try {
            result = (Integer) query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }


    public void assignCar(String carNumber, Integer driverID) {
        if(carNumber == null){
            logger.warn("carNumber can't be null");
            throw new IllegalArgumentException("carNumber can't be null");
        }
        if(driverID == null){
            logger.warn("driverID can't be null");
            throw new IllegalArgumentException("driverID can't be null");
        }
        Query q = entityManager.createNativeQuery("UPDATE service_user SET car_number = ?1 " +
                "WHERE user_id = ?2");
        q.setParameter(1, carNumber);
        q.setParameter(2, driverID);
        q.executeUpdate();
    }

    @Override
    public void clearUnactivatedAccounts(int unactivatedDays) {
        String sql = "DELETE FROM service_user " +
                "WHERE activated = FALSE " +
                "AND CURRENT_TIMESTAMP - registration_date  > '" + unactivatedDays + " days' :: INTERVAL";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Boolean accountIsActivated(Integer userId) {
        String sql = "SELECT activated FROM service_user WHERE user_id = " + userId;
        Query query = entityManager.createNativeQuery(sql);
        Boolean result;
        try {
            result = (Boolean) query.getSingleResult();
        } catch (PersistenceException e) {
            result = null;
        }

        return result;
    }

    @Override
    public boolean activateAccount(Integer userId) {
        String sql = "UPDATE service_user SET activated = TRUE WHERE user_id = " + userId;
        Query query = entityManager.createNativeQuery(sql);
        return query.executeUpdate() == 0;
    }

    @Override
    public Integer addUser(ServiceUserEntity user) {
        String sql = "INSERT INTO service_user " +
                "(email, password, phone, sex, driver, admin, group_name, car_number, driver_license, ignored_times, activated, registration_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING user_id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getPhone());
        query.setParameter(4, user.getSex());
        query.setParameter(5, user.getDriver());
        query.setParameter(6, user.getAdmin());
        query.setParameter(7, user.getGroupName());
        query.setParameter(8, user.getCar() != null? user.getCar().getCarNumber() : null);
        query.setParameter(9, user.getDriverLicense());
        query.setParameter(10, user.getIgnoredTimes());
        query.setParameter(11, user.getActivated());
        query.setParameter(12, user.getRegistrationDate());
        try {
            user.setUserId((Integer)query.getSingleResult());
        } catch (PersistenceException e) {
            return null;
        }
        return user.getUserId();
    }

    @Override
    public void updateUser(ServiceUserEntity user) {
        String sql = "UPDATE service_user SET email = ?, password = ? , phone =?" +
                "WHERE user_id = " + user.getUserId();
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getPhone());
        query.executeUpdate();
    }

    @Override
    public ServiceUserEntity getUserByEmail(String email) {
        String sql = "SELECT * FROM service_user WHERE email = ?";
        Query query = entityManager.createNativeQuery(sql, ServiceUserEntity.class);
        query.<String>setParameter(1, email);

        ServiceUserEntity result;
        try {
            result = (ServiceUserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }

    public void createUser(ServiceUserEntity user) {
        String sql = "INSERT INTO service_user " +
                "(email, password, phone, driver, car_number, sex) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getPhone());
        query.setParameter(4, user.getDriver());
        query.setParameter(5, user.getCar() != null ? user.getCar().getCarNumber() : null);
        query.setParameter(6, user.getSex());
        query.executeUpdate();
    }

    @Override
    public ServiceUserEntity getDriverByID(int id) {
        if(id <= 0){
            logger.warn("Driver id can't be <= 0!");
            throw new IllegalArgumentException("Driver id can't be <= 0!");
        }
        ServiceUserEntity driver = entityManager.find(ServiceUserEntity.class, id);
        if(driver == null){
            logger.warn("There is no driver with such id");
            throw new ServiceUserNotFoundException("There is no driver with such id");
        }
        return driver;
    }

    @Override
    public ServiceUserEntity getUserById(int id) {
        if(id <= 0){
            logger.warn("User id can't be <= 0!");
            throw new IllegalArgumentException("User id can't be <= 0!");
        }
        ServiceUserEntity user = entityManager.find(ServiceUserEntity.class, id);
        if(user == null){
            logger.warn("There is no User with such id");
            throw new ServiceUserNotFoundException("There is no User with such id");
        }
        return user;
    }

    //TODO test this method
    @Override
    public int getDriverPagesCount() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM service_user WHERE driver = TRUE");
        Integer driversCount = ((BigInteger) q.getSingleResult()).intValue();
        return (int) (Math.ceil((double) driversCount / DRIVERS_PAGE_SIZE));
    }

    public void deleteUser(int serviceUserId) {
        if(serviceUserId <= 0){
            logger.warn("serviceUserId can't be <= 0");
            throw new IllegalArgumentException("serviceUserId can't be <= 0");
        }
        String sql = "DELETE from service_user " +
                "where user_id = ?1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, serviceUserId);
        query.executeUpdate();
    }
    @Override
    public int checkBlackListUserByEmail(String email){
            String sql = "SELECT * FROM service_user WHERE email = ?";
        // TODO normal select ignored_times from database
            Query query = entityManager.createNativeQuery(sql, ServiceUserEntity.class);
            query.setParameter(1, email);
            ServiceUserEntity serviceUserEntity=(ServiceUserEntity)query.getSingleResult();
        return serviceUserEntity.getIgnoredTimes();
    }

    public CarEntity getDriversCar(ServiceUserEntity driver){
        String sql = "SELECT * FROM car INNER JOIN service_user ON car.car_number = service_user.car_number " +
                "AND user_id = ?";
        Query query = entityManager.createNativeQuery(sql, CarEntity.class);
        query.setParameter(1, driver.getUserId());
        return (CarEntity) query.getSingleResult();
    }


}
