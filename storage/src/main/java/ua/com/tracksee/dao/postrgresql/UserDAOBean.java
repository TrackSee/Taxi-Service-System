package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Vadym Akymov
 * @author Ruslan Gunavardana
 */
@Stateless
public class UserDAOBean implements UserDAO {
    private static final Logger logger = LogManager.getLogger();
    //10 drivers per query by default
    public static final int DRIVERS_LIMIT = 10;
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;


    /**
     * @param partNumber - number of data part (from 1 to driver_count/DRIVERS_LIMIT)
     * @return list with part of drivers(default size of list if 10)
     */
    @Override
    public List<ServiceUserEntity> getDrivers(int partNumber) {
        if(partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
                "WHERE driver = TRUE LIMIT ?1 OFFSET ?2", ServiceUserEntity.class);
        query.setParameter(1, DRIVERS_LIMIT);
        query.setParameter(2, (partNumber-1)*DRIVERS_LIMIT);
        return query.getResultList();
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
        String sql = "SELECT  FROM service_user WHERE user_id = " + userId;
        Query query = entityManager.createNativeQuery(sql);
        return (Boolean) query.getSingleResult();
    }

    @Override
    public boolean activateAccount(Integer userId) {
        String sql = "UPDATE service_user SET activated = TRUE WHERE user_id = " + userId;
        Query query = entityManager.createNamedQuery(sql);
        return query.executeUpdate() == 0;
    }

    @Override
    public Integer addUser(ServiceUserEntity user) {
//        String sql = "INSERT INTO service_user " +
//                "(email, password, phone, sex, driver, admin, group_name, car_number, driver_license, ignored_times, activated, registration_date) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
//                "RETURNING user_id";
//        Query query = entityManager.createNativeQuery(sql, Integer.class);
//        query.<String>setParameter(1, user.getEmail());
//        query.<String>setParameter(2, user.getPassword());
//        query.<String>setParameter(3, user.getPhone());
//        query.<Sex>setParameter(4, user.getSex());
//        query.<Boolean>setParameter(5, user.getDriver());
//        query.<Boolean>setParameter(6, user.getAdmin());
//        query.<String>setParameter(7, user.getGroupName());
//        query.<Integer>setParameter(8, user.getCar() != null? user.getCar().getCarNumber() : null);
//        query.<String>setParameter(9, user.getDriverLicense());
//        query.<Integer>setParameter(10, user.getIgnoredTimes());
//        query.<Boolean>setParameter(11, user.getActivated());
//        query.<Timestamp>setParameter(12, user.getRegistrationDate());
//        if (query.executeUpdate() == 0) {
//            return null;
//        }
        entityManager.merge(user);
        return user.getUserId();
    }

    @Override
    public int getDriversCount() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM service_user WHERE driver = TRUE");
        return (int) q.getSingleResult();
    }

    @Override
    public void updateUser(ServiceUserEntity user) {
        String sql = "UPDATE service_user SET email = ?, phone = ? " +
                "WHERE user_id = " + user.getUserId();
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getPhone());
        query.setParameter(4, user.getActivated());
        query.executeUpdate();
        Query getIdQuery = entityManager.createNativeQuery("SELECT user_id FROM service_user WHERE email = ?");
        getIdQuery.setParameter(1, user.getEmail());
        return (Integer) getIdQuery.getSingleResult();
    }

    //TODO test this method
    @Override
    public int getDriverPagesCount() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM service_user WHERE driver = TRUE");
        Integer driversCount = ((BigInteger) q.getSingleResult()).intValue();
        return (int) (Math.ceil((double)driversCount / DRIVERS_LIMIT));
    
    public void deleteUser(ServiceUserEntity user) {
        String sql = "DELETE from service_user " +
                "where user_id = " + user.getUserId();
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
}
