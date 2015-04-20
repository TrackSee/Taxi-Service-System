package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Ruslan Gunavardana.
 */
@Local(UserDAO.class)
@Stateless
public class UserDAOBean implements UserDAO {
    //10 drivers per query by default
    public static final int DRIVERS_LIMIT = 10;
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();


    /**
     * @param partNumber - number of data part (from 1 to driver_count/DRIVERS_LIMIT)
     * @return list with part of drivers(default size of list if 10)
     * @author Vadym Akymov
     */
    @Override
    public List<ServiceUserEntity> getDrivers(int partNumber) {
        if (partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
                "WHERE driver = 'TRUE' LIMIT :limit OFFSET :offset", ServiceUserEntity.class);
        query.setParameter("limit", DRIVERS_LIMIT);
        query.setParameter("offset", (partNumber - 1) * DRIVERS_LIMIT);
        return query.getResultList();
    }

    /**
     * @param driver - entity of driver for addition by Administrator
     * @author Katia Stetsiuk
     */
    @Override
    public void addDriver(ServiceUserEntity driver) {
        entityManager.persist(driver);
    }

    /**
     * @param driverId - driver's id for deleting by Administrator
     * @author Katia Stetsiuk
     */
    @Override
    public void removeDriver(int driverId) {
        ServiceUserEntity driver = entityManager.find(ServiceUserEntity.class, driverId);
        entityManager.remove(driver);
    }

    /**
     * @param driver - - entity of driver to editing by Administrator
     * @author Katia Stetsiuk
     */

    @Override
    public void editDriver(ServiceUserEntity driver) {
        entityManager.refresh(driver);
    }

    /**
     * @param taxiOrder - entity of taxiOrder for editing by User
     * @author Katia Stetsiuk
     */
    @Override
    public void editOrder(TaxiOrderEntity taxiOrder) {
        entityManager.refresh(taxiOrder);
    }

    /**
     * @param taxiOrderId - taxiOrder's id for refusal Order by User
     * @author Katia Stetsiuk
     */
    @Override
    public void cancelOrder(int taxiOrderId) {
        TaxiOrderEntity taxiOrder = entityManager.find(TaxiOrderEntity.class, taxiOrderId);
        taxiOrder.setStatus("REFUSED");
        entityManager.refresh(taxiOrder);

    }


}
