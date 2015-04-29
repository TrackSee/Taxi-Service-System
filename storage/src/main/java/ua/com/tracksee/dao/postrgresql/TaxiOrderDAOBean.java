package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
                "status = QUEUED OR status = UPDATED " +
                "AND car_category = ? AND driver_sex = ? AND animal_transportation = ? AND free_wifi = ?" +
                "AND air_conditioner = ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1,driver.getCar().getCarCategory());
        query.setParameter(2, driver.getSex());
        query.setParameter(3,driver.getCar().getAnimalTransportationApplicable());
        query.setParameter(4,driver.getCar().getFreeWifi());
        query.setParameter(5,driver.getCar().getAirConditioner());
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getHistoryOfOrders(ServiceUserEntity driver) {
        String sql = "SELECT * FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer" +
                " AND status = COMPLETED " +
                "AND driver_id = ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1,driver.getCar().getCarCategory());
        return query.getResultList();
    }

    @Override
    public TaxiOrderEntity getAssignedOrder(ServiceUserEntity driver) {
        String sql = "SELECT * FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer " +
                "AND taxi_order_item.driver_id = ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, driver.getUserId());
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



}
