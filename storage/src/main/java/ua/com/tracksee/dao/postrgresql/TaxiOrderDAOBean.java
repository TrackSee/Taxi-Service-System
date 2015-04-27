package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * <p>Postgresql database implementation of
 * {@link TaxiOrderDAO} interface.</p>
 * <p>Used for persisting and accessing taxi order data.</p>
 *
 * @see ua.com.tracksee.dao.TaxiOrderDAO
 * @author kstes_000
 * @author Ruslan Gunavardana
 */
@Stateless
public class TaxiOrderDAOBean implements TaxiOrderDAO {

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
    public Long addOrder(TaxiOrderEntity order) {
        String sql = "INSERT INTO taxi_order " +
                "(status, service, price, user_id, description, car_category, way_of_payment, driver_sex, " +
                "music_style, animal_transportation, free_wifi, smoking_driver, air_conditioner, comment)  " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING tracking_number; " +
                "INSERT INTO taxi_order_item (tracking_numer, path, ordered_quantity, driver_id) VALUES ()";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, order.getStatus().toString());
        query.setParameter(3, order.getService());
        query.setParameter(4, order.getUserId());
        query.setParameter(5, order.getDescription());
        query.setParameter(6, order.getCarCategory());
        query.setParameter(7, order.getWayOfPayment());
        query.setParameter(8, order.getDriverSex());
        query.setParameter(9, order.getMusicStyle());
        query.setParameter(10, order.getAnimalTransportation());
        query.setParameter(11, order.getFreeWifi());
        query.setParameter(12, order.getSmokingDriver());
        query.setParameter(13, order.getAirConditioner());
        query.setParameter(14, order.getComment());

        //TODO not finished
        return (Long) query.getSingleResult();
    }

    @Override
    public List<TaxiOrderEntity> getQueuedOrders() {
        return null;
    }
}
