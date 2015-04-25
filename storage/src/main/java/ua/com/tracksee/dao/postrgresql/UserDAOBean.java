package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        String sql = "INSERT INTO service_user (email, password, phone, registration_date) " +
                "VALUES (?, ?, ?, ?)" +
                "WHERE user_id = " + user.getUserId();
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, user.getEmail());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getPhone());
        query.setParameter(4, null);
        query.executeUpdate();
        Query getIdQuery = entityManager.createNativeQuery("SELECT user_id FROM service_user WHERE email = ?");
        getIdQuery.setParameter(1, user.getEmail());
        return (Integer) getIdQuery.getSingleResult();
    }
}
