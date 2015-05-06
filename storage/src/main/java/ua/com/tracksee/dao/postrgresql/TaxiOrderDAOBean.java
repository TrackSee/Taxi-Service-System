package ua.com.tracksee.dao.postrgresql;

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
 * @author kstes_000
 * @author Maria Komar
 */
@Stateless
public class TaxiOrderDAOBean implements TaxiOrderDAO {

    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public void addComment(TaxiOrderEntity entity) {
        String sql = "INSERT INTO taxi_order comment VALUES(?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, entity.getComment());
        query.executeUpdate();
    }

    @Override
    public Integer addOrder(TaxiOrderEntity taxiOrderEntity) {
        return null;
    }

    @Override
    public List<TaxiOrderEntity> getAvailableOrders(ServiceUserEntity driver, int pageNumber){

        String sql = "SELECT * FROM taxi_order WHERE " +
               "status = 'Queued' OR status = 'Updated' " +
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
                " AND status = 'Completed' " +
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
                "AND taxi_order_item.driver_id = ? AND status = 'Assigned' OR status ='In progress'";
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
            Query query2 = entityManager.createNativeQuery("UPDATE taxi_order SET status = 'Assigned', ordered_date = ? " +
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
        String sql = "UPDATE taxi_order SET status = 'In progress' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public void setCompletedOrder(int trackingNumber) {
        String sql = "UPDATE taxi_order SET status = 'Completed' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public void setRefusedOrder(int trackingNumber) {
        String sql = "UPDATE taxi_order SET status = 'Refused' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }


    @Override
    public int getOrdersPagesCount(int id) {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer " +
                "AND taxi_order_item.driver_id = ? AND taxi_order.status = 'Completed'");
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
