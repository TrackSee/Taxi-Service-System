package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>Postgresql database implementation of
 * {@link TaxiOrderDAO} interface.</p>
 * <p>Used for persisting and accessing taxi order data.</p>
 *
 * @see ua.com.tracksee.dao.TaxiOrderDAO
 * @author kstes_000
 * @author Ruslan Gunavardana
 * @author Sharaban Sasha
 */
@Stateless
public class TaxiOrderDAOBean implements TaxiOrderDAO {
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;


    @Override
    public void addComment(TaxiOrderEntity entity) {
        String sql = "INSERT INTO taxi_order (comment) VALUES(?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, entity.getComment());
        query.executeUpdate();
    }
    @Override
    public Long addOrder(TaxiOrderEntity orderEntity) {
        String sql="INSERT INTO taxi_order (description,status,price,user_id,service,car_category,way_of_payment,driver_sex," +
                "music_style,animal_transportation,free_wifi,smoking_driver,air_conditioner) " +
                "VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13)" +
                "RETURNING tracking_number";

        //TODO insert into taxi items
        //     "INSERT INTO taxi_order_item (tracking_numer, path, ordered_quantity, driver_id) VALUES ()"

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,orderEntity.getDescription());
        query.setParameter(2, orderEntity.getStatus().toString());
        query.setParameter(3, orderEntity.getPrice());
        query.setParameter(4,orderEntity.getUserId());
        query.setParameter(5, orderEntity.getService().toString());
        query.setParameter(6, orderEntity.getCarCategory().toString());
        query.setParameter(7, orderEntity.getWayOfPayment().toString());
        query.setParameter(8, orderEntity.getDriverSex().toString());
        query.setParameter(9, orderEntity.getMusicStyle().toString());
        query.setParameter(10, orderEntity.getAnimalTransportation());
        query.setParameter(11, orderEntity.getFreeWifi());
        query.setParameter(12, orderEntity.getNonSmokingDriver());
        query.setParameter(13,orderEntity.getAirConditioner());


        //   Query query2 = entityManager.createNativeQuery("SELECT max(tracking_number) FROM taxi_order", OrderEntity.class);
        return (Long) query.getSingleResult();
    }
    @Override
    public List<TaxiOrderEntity> getQueuedOrders() {
        return null;
    }

    @Override
    public TaxiOrderEntity getOrder(Long trackingNumber) {
        return null;
    }

    @Override
    public List<TaxiOrderEntity> getOrdersPerPage(int partNumber) {
        if(partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order " +
                "ORDER BY ordered_date LIMIT ?1 OFFSET ?2", TaxiOrderEntity.class);
        query.setParameter(1, TO_ORDERS_PER_PAGE);
        query.setParameter(2, (partNumber - 1)*TO_ORDERS_PER_PAGE);
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getAvailableOrders(ServiceUserEntity driver, int pageNumber){

        String sql = "SELECT * FROM taxi_order WHERE " +
                "status = 'QUEUED' OR status = 'UPDATED' " +
                "AND car_category = ? " +
                "AND driver_sex = ?" +
                "AND animal_transportation = ? AND free_wifi = ?" +
                "AND air_conditioner = ? LIMIT ? OFFSET ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1,driver.getCar().getCarCategory().toString());
        query.<Boolean>setParameter(2, driver.getSex());
        query.<Boolean>setParameter(3,driver.getCar().getAnimalTransportationApplicable());
        query.<Boolean>setParameter(4,driver.getCar().getFreeWifi());
        query.<Boolean>setParameter(5,driver.getCar().getAirConditioner());
        query.setParameter(6, ORDERS_PAGE_SIZE);
        query.setParameter(7, (pageNumber - 1) * ORDERS_PAGE_SIZE);
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getHistoryOfOrders(int id, int pageNumber) {
        String sql = "SELECT * FROM taxi_order " +
                "INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer" +
                " AND status = 'COMPLETED' " +
                "AND driver_id = ? LIMIT ? OFFSET ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1,id);
        query.setParameter(2, ORDERS_PAGE_SIZE);
        query.setParameter(3, (pageNumber - 1) * ORDERS_PAGE_SIZE);
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getAssignedOrders(int id) {
        String sql = "SELECT * FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer " +
                "AND taxi_order_item.driver_id = ? AND status = 'ASSIGNED' OR status ='IN_PROGRESS'";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    //@Todo write transaction, get time
    @Override
    public void setAssignOrder(int driverId, int trackingNumber, Timestamp carArriveTime) {
//        try {
        //entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("UPDATE taxi_order_item SET driver_id = ?");
        query.setParameter(1, driverId);
        Query query2 = entityManager.createNativeQuery("UPDATE taxi_order SET status = 'ASSIGNED', ordered_date = ? " +
                "WHERE tracking_number = ?");
        query2.setParameter(1, carArriveTime);
        query2.setParameter(2, trackingNumber);
        query.executeUpdate();
        query2.executeUpdate();
        //entityManager.getTransaction().commit();
//        }
//        catch (  Exception e) {
//            entityManager.getTransaction().rollback();
//        }
//        if (entityManager != null) {
//            entityManager.close();
//            entityManager=null;
//        }
    }


    @Override
    public void setInProgressOrder(int trackingNumber) {
        String sql = "UPDATE taxi_order SET status = 'IN_PROGRESS' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public void setCompletedOrder(int trackingNumber) {
        String sql = "UPDATE taxi_order SET status = 'COMPLETED' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public void setRefusedOrder(int trackingNumber) {
        String sql = "UPDATE taxi_order SET status = 'REFUSED' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }


    @Override
    public int getOrdersPagesCount(int id) {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer " +
                "AND taxi_order_item.driver_id = ? AND taxi_order.status = 'COMPLETED'");
        q.setParameter(1, id);
        Integer driversCount = ((BigInteger) q.getSingleResult()).intValue();
        return (int) (Math.ceil((double) driversCount / ORDERS_PAGE_SIZE));
    }

    @Override
    public TaxiOrderItemEntity getPgPath(TaxiOrderEntity taxiOrderEntity){
        String sql = "SELECT * FROM taxi_order_item " +
                "INNER JOIN taxi_order " +
                "ON taxi_order_item.tracking_numer = taxi_order.tracking_number " +
                "AND tracking_numer = ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderItemEntity.class);
        query.setParameter(1,taxiOrderEntity.getTrackingNumber());
        return (TaxiOrderItemEntity) query.getSingleResult();
    }
}
