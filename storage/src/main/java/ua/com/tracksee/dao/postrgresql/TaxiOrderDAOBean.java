package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
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
                "music_style,animal_transportation,free_wifi,non_smoking_driver,air_conditioner,arrive_date,end_date) " +
                "VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15) RETURNING tracking_number";

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
        query.setParameter(14,orderEntity.getArriveDate());
        query.setParameter(15,orderEntity.getEndDate());

        BigInteger trackingNumber=(BigInteger)query.getSingleResult();
        return trackingNumber.longValue();
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
    public int getOldTaxiOrderPagesCount() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order WHERE " +
                "status = 'COMPLETED'");
        BigInteger generalOrderCount = (BigInteger) q.getSingleResult();
        return (int) Math.ceil(generalOrderCount.intValue()/ (double) TO_ORDERS_PER_PAGE);
    }

    @Override
    public List<TaxiOrderEntity> getActiveOrdersPerPage(int partNumber) {
        if(partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order WHERE status != " +
                "'COMPLETED' " +
                "ORDER BY ordered_date DESC LIMIT ?1 OFFSET ?2", TaxiOrderEntity.class);
        query.setParameter(1, TO_ORDERS_PER_PAGE);
        query.setParameter(2, (partNumber - 1)*TO_ORDERS_PER_PAGE);
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getOldOrdersPerPage(int partNumber) {
        if(partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order WHERE status = " +
                "'COMPLETED' " +
                "ORDER BY ordered_date DESC LIMIT ?1 OFFSET ?2", TaxiOrderEntity.class);
        query.setParameter(1, TO_ORDERS_PER_PAGE);
        query.setParameter(2, (partNumber - 1)*TO_ORDERS_PER_PAGE);
        return query.getResultList();
    }
    @Override
    public int getActiveTaxiOrderPagesCount() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order WHERE " +
                "status != 'COMPLETED'");
        BigInteger generalOrderCount = (BigInteger) q.getSingleResult();
        return (int) Math.ceil(generalOrderCount.intValue()/ (double) TO_ORDERS_PER_PAGE);
    }

    @Override
    public int getOrdersByPeriod(String dateFrom, String dateTo) {
//        date format '2015-04-26'
//        SELECT COUNT(*) FROM taxi_order WHERE ordered_date >= '2015-04-26' AND ordered_date <= '2015-04-29'
        String sql = "SELECT COUNT(*) FROM taxi_order WHERE ordered_date >= '" + dateFrom + "'" +
                " AND ordered_date <= '" + dateTo + "'";
        Query query = entityManager.createNativeQuery(sql);
        BigInteger count = (BigInteger) query.getSingleResult();
        return count.intValue();
    }
}
