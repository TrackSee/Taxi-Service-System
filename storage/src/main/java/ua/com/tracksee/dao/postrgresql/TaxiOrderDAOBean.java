package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kstes_000 on 23-Apr-15.
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
    public List<TaxiOrderItemEntity> getOrderItems(Integer orderId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order_item WHERE tracking_numer = ?",
                TaxiOrderItemEntity.class);
        query.setParameter(1, orderId);
        return query.getResultList();
    }

    @Override
    public TaxiOrderEntity getOrder(Integer orderId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order_item WHERE tracking_number = ?",
                TaxiOrderEntity.class);
        query.setParameter(1, orderId);
        return (TaxiOrderEntity)query.getSingleResult();
    }

    @Override
    public int refuseOrder(Integer trackNumber) {
        Query query = entityManager.createNativeQuery(
                "UPDATE taxi_order SET status = 'Refused' WHERE tracking_number = ?");
        query.setParameter(1, trackNumber);
        return query.executeUpdate();
    }

    @Override
    public int updateComment(Integer trackNumber, String comment) {
        Query query = entityManager.createNativeQuery(
                "UPDATE  taxi_order SET comment = ?1 WHERE tracking_number = ?2");
        query.setParameter(1, comment);
        query.setParameter(2, trackNumber);
        return query.executeUpdate();
    }

    @Override
    public int updateOrder(TaxiOrderEntity entity) {
        Query query = entityManager.createNativeQuery("UPDATE taxi_order SET status = ?1," +
                "driver_sex = ?2, music_style = ?3, animal_transportation = ?4, " +
                "free_wifi = ?5, smoking_driver = ?6, air_conditioner = ?7 WHERE tracking_number = ?8");
        query.setParameter(1, entity.getStatus());
        query.setParameter(2, entity.getDriverSex());
        query.setParameter(3, entity.getMusicStyle());
        query.setParameter(4, entity.isAnimalTransportation());
        query.setParameter(5, entity.isFreeWifi());
        query.setParameter(6, entity.isSmokingDriver());
        query.setParameter(7, entity.isAirConditioner());
        query.setParameter(8, entity.getTrackingNumber());
        return query.executeUpdate();
    }


}
