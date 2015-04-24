package com.netcracker.tracksee.dao.postrgresql;

import com.netcracker.tracksee.dao.UserDAO;
import com.netcracker.tracksee.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.netcracker.tracksee.entities.ServiceUserEntity;

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

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO address (email,phone,activated) " +
                    "VALUES("+"'"+user.getEmail()+"','"+user.getPhone()+"','"+user.isActivated()+")";
            Query query = entityManager.createNativeQuery(sql);
//            query.setParameter(1, user.getEmail());
//            query.setParameter(2, user.getPhone());
//            query.setParameter(3, user.isActivated());
            query.executeUpdate();
         }catch(Exception ex){
            System.out.println("UserDAO");
      //  ex.printStackTrace();
    }
    }

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
    public void activateAccount(Integer userId) {
        String sql = "";
        Query query = entityManager.createNamedQuery(sql);
        query.executeUpdate();
    }
}
