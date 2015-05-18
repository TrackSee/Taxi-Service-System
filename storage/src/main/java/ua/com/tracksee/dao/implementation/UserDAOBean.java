package ua.com.tracksee.dao.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.implementation.exceptions.ServiceUserNotFoundException;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.UserEntity;

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
    public List<UserEntity> getDrivers(int partNumber) {
        if (partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
                "WHERE driver = TRUE ORDER BY email LIMIT ?1 OFFSET ?2", UserEntity.class);
        query.setParameter(1, DRIVERS_PAGE_SIZE);
        query.setParameter(2, (partNumber - 1) * DRIVERS_PAGE_SIZE);
        return query.getResultList();
    }

    /**
     * @return list of users in system
     * @author Katia Stetsiuk
     */
    @Override
    public List<UserEntity> getUsers() {
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user "
                , UserEntity.class);
        return query.getResultList();
    }

    @Override
    public List<UserEntity> getUnregisteredUsers() {
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user WHERE activated=FALSE "
                , UserEntity.class);
        return query.getResultList();
    }

    @Override
    public List<String> getDriversEmails() {

        Query query = entityManager.createNativeQuery("SELECT email FROM service_user " +
                "WHERE driver = TRUE ", String.class);
        return query.getResultList();
    }

    /**
     * @param email to customers drivers with such email
     * @return
     * @author Katia Stetsiuk
     */
    @Override
    public List<UserEntity> getCustomersByEmail(String email) {
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
                "WHERE driver = FALSE AND email LIKE ? ", UserEntity.class);
        query.setParameter(1, "%" + email + "%");
        return query.getResultList();
    }

    /**
     * @param email to get drivers with such email
     * @return
     * @author Katia Stetsiuk
     */
    @Override
    public List<UserEntity> getDriversByEmail(String email) {
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
                "WHERE driver = TRUE AND email LIKE ? ", UserEntity.class);
        query.setParameter(1, "%" + email + "%");
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
//        if (carNumber == null) {
//            logger.warn("carNumber can't be null");
//            throw new IllegalArgumentException("carNumber can't be null");
//        }
        if (driverID == null) {
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
    public Integer addUser(UserEntity user) {
        String sql = "INSERT INTO service_user " +
                "(email, password, salt, phone, first_name, last_name, sex, driver, admin, group_name, car_number, driver_license, ignored_times, activated, registration_date) " +
                "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15) " +
                "RETURNING user_id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getSalt());
        query.setParameter(4, user.getPhone());
        query.setParameter(5, user.getFirstName());
        query.setParameter(6, user.getLastName());
        query.setParameter(7, user.getSex());
        query.setParameter(8, user.getDriver());
        query.setParameter(9, user.getAdmin());
        query.setParameter(10, user.getGroupName());
        query.setParameter(11, user.getCar() != null ? user.getCar().getCarNumber() : null);
        query.setParameter(12, user.getDriverLicense());
        query.setParameter(13, user.getIgnoredTimes());
        query.setParameter(14, user.getActivated());
        query.setParameter(15, user.getRegistrationDate());
        try {
            user.setUserId((Integer) query.getSingleResult());
        } catch (PersistenceException e) {
            return null;
        }
        return user.getUserId();
    }

    @Override
    public Integer addUnregisteredUser(UserEntity user) {
        String sql = "INSERT INTO service_user " +
                "(email, phone, activated,password,salt) " +
                "VALUES (?1, ?2, ?3,?4,?5) " +
                "RETURNING user_id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getActivated());
        query.setParameter(4, user.getPassword());
        query.setParameter(5, user.getSalt());
        user.setUserId((Integer) query.getSingleResult());
        return user.getUserId();
    }

    @Override
    public void updateUser(UserEntity user) {
        String sql = "UPDATE service_user SET email = ?, password = ? , phone =?" +
                "WHERE user_id = " + user.getUserId();
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getPhone());
        query.executeUpdate();
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        String sql = "SELECT * FROM service_user WHERE email = ?";
        Query query = entityManager.createNativeQuery(sql, UserEntity.class);
        query.<String>setParameter(1, email);

        UserEntity result;
        try {
            result = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }

    public void createUser(UserEntity user) {
        String sql = "INSERT INTO service_user " +
                "(email, password, phone, driver, car_number, sex , salt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getPhone());
        query.setParameter(4, user.getDriver());
        query.setParameter(5, user.getCar() != null ? user.getCar().getCarNumber() : null);
        query.setParameter(6, user.getSex());
        query.setParameter(7, "2311323");
        query.executeUpdate();
    }

    @Override
    public UserEntity getDriverByID(int id) {
        if (id <= 0) {
            logger.warn("Driver id can't be <= 0!");
            throw new IllegalArgumentException("Driver id can't be <= 0!");
        }
        UserEntity driver = entityManager.find(UserEntity.class, id);
        if (driver == null) {
            logger.warn("There is no driver with such id");
            throw new ServiceUserNotFoundException("There is no driver with such id");
        }
        return driver;
    }

    @Override
    public UserEntity getUserById(int id) {
        if (id <= 0) {
            logger.warn("User id can't be <= 0!");
            throw new IllegalArgumentException("User id can't be <= 0!");
        }
        UserEntity user = entityManager.find(UserEntity.class, id);
        if (user == null) {
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
        if (serviceUserId <= 0) {
            logger.warn("serviceUserId can't be <= 0");
            throw new IllegalArgumentException("serviceUserId can't be <= 0");
        }
        String sql = "DELETE FROM service_user " +
                "WHERE user_id = ?1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, serviceUserId);
        query.executeUpdate();
    }

    @Override
    public boolean checkBlackListUserByEmail(String email) {
        boolean blackListPresent = false;
        Query query = entityManager.createNativeQuery("SELECT ignored_times FROM service_user WHERE email=?1");
        query.setParameter(1, email);
        Integer result;
        try {
            result = (Integer) query.getSingleResult();
            if (result > 3) {
                blackListPresent = true;
            }
        } catch (NoResultException | NullPointerException e) {
            blackListPresent = false;
        }
        return blackListPresent;
    }

    public CarEntity getDriversCar(UserEntity driver) {
        String sql = "SELECT * FROM car INNER JOIN service_user ON car.car_number = service_user.car_number " +
                "AND user_id = ?";
        Query query = entityManager.createNativeQuery(sql, CarEntity.class);
        query.setParameter(1, driver.getUserId());
        return (CarEntity) query.getSingleResult();
    }


}
