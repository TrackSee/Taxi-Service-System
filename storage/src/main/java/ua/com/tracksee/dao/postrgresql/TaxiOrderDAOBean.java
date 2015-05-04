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


}
