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
 * @author Sharaban Sasha
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
    public Integer addOrder(TaxiOrderEntity orderEntity) {
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
        return (Integer) query.getSingleResult();
    }
    @Override
    public List<TaxiOrderEntity> getQueuedOrders() {
        return null;
    }

    @Override
    public TaxiOrderEntity getOrder(Integer trackingNumber) {
        return null;
    }
}
