package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;

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
    public List<TaxiOrderEntity> getAvailableOrders(ServiceUserEntity driver){

        String sql = "SELECT * FROM taxi_order WHERE " +
               "status = 'Queued' OR status = 'Updated' " +
                "AND car_category = ? " +
                        "AND driver_sex = ?" +
"AND animal_transportation = ? AND free_wifi = ?" +
                "AND air_conditioner = ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1,driver.getCar().getCarCategory().toString());
        query.<Boolean>setParameter(2, driver.getSex());
        query.<Boolean>setParameter(3,driver.getCar().getAnimalTransportationApplicable());
        query.<Boolean>setParameter(4,driver.getCar().getFreeWifi());
        query.<Boolean>setParameter(5,driver.getCar().getAirConditioner());
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getHistoryOfOrders(int id) {
        String sql = "SELECT * FROM taxi_order " +
//                "WHERE status = 'Completed'";
                "INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer" +
                " AND status = 'Completed' " +
                "AND driver_id = ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1,id);
        return query.getResultList();
    }

    @Override
    public TaxiOrderEntity getAssignedOrder(int id) {
        String sql = "SELECT * FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer " +
                "AND taxi_order_item.driver_id = ? AND status = 'Assigned'";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, id);
        return (TaxiOrderEntity) query.getSingleResult();
    }

    //write transaction
    @Override
    public void setAssignOrder(ServiceUserEntity driver, TaxiOrderEntity taxiOrderEntity, Timestamp carArriveTime) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery("UPDATE taxi_order_item SET driver_id = ?");
            query.setParameter(1, driver.getUserId());
            Query query2 = entityManager.createNativeQuery("UPDATE taxi_order SET status = ASSIGNED, car_arrive_time = ? " +
                    "WHERE tracking_number = ?");
            query2.setParameter(1, carArriveTime);
            query2.setParameter(2, taxiOrderEntity.getTrackingNumber());
            query.executeUpdate();
            query2.executeUpdate();
            entityManager.getTransaction().commit();
        }
        catch (  Exception e) {
            entityManager.getTransaction().rollback();
        }
        if (entityManager != null) {
            entityManager.close();
            entityManager=null;
        }
        }


    @Override
    public void setInProgressOrder(TaxiOrderEntity taxiOrderEntity) {
        String sql = "UPDATE taxi_order SET status = INPROGRESS WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, taxiOrderEntity.getTrackingNumber());
        query.executeUpdate();
    }

    @Override
    public void setCompletedOrder(TaxiOrderEntity taxiOrderEntity) {
        String sql = "UPDATE taxi_order SET status = COMPLETED WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, taxiOrderEntity.getTrackingNumber());
        query.executeUpdate();
    }

    @Override
    public void setRefusedOrder(TaxiOrderEntity taxiOrderEntity) {
        String sql = "UPDATE taxi_order SET status = REFUSED WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, taxiOrderEntity.getTrackingNumber());
        query.executeUpdate();
    }


    @Override
    public int getOrdersPagesCount(int id) {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer " +
                "AND taxi_order_item.driver_id = ? AND taxi_order.status = COMPLETED");
        Integer driversCount = ((BigInteger) q.getSingleResult()).intValue();
        return (int) (Math.ceil((double) driversCount / ORDERS_PAGE_SIZE));
    }



}
