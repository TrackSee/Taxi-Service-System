package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
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
    public int updateComment(Integer trackNumber, String comment) {
        Query query = entityManager.createNativeQuery(
                "UPDATE  taxi_order SET comment = ?1 WHERE tracking_number = ?2");
        query.setParameter(1, comment);
        query.setParameter(2, trackNumber);
        return query.executeUpdate();
    }

    @Override
    public void addComment(TaxiOrderEntity entity) {
        String sql = "INSERT INTO taxi_order (comment) VALUES(?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, entity.getComment());
        query.executeUpdate();
    }

    @Override
    public Long addOrder(TaxiOrderEntity order) {
        List<TaxiOrderItemEntity> itemList = order.getItemList();
        StringBuilder sql = new StringBuilder("INSERT INTO taxi_order " +
                "(description,status,price,user_id,service,car_category,way_of_payment,driver_sex," +
                "music_style,animal_transportation,free_wifi,non_smoking_driver,air_conditioner,ordered_date) " +
                "VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14) RETURNING tracking_number; ");

        for (TaxiOrderItemEntity item : itemList) {
            sql.append("INSERT INTO taxi_order_item" +
                    "(tracking_numer, path, ordered_quantity, driver_id)" +
                    "VALUES (lastval(), ?, ?, ?); ");
        }

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, order.getDescription());
        query.setParameter(2, order.getStatus().toString());
        query.setParameter(3, order.getPrice());
        query.setParameter(4, order.getUserId());
        query.setParameter(5, order.getService().toString());
        query.setParameter(6, order.getCarCategory().toString());
        query.setParameter(7, order.getWayOfPayment().toString());
        query.setParameter(8, order.getDriverSex().toString());
        query.setParameter(9, order.getMusicStyle().toString());
        query.setParameter(10, order.getAnimalTransportation());
        query.setParameter(11, order.getFreeWifi());
        query.setParameter(12, order.getNonSmokingDriver());
        query.setParameter(13, order.getAirConditioner());
        query.setParameter(14, order.getOrderedDate());

        int i = 14; // incrementing inside the loop
        for (TaxiOrderItemEntity item : itemList) {
            query.setParameter(++i, item.getPath());
            query.setParameter(++i, item.getOrderedQuantity());

            Integer driverId = item.getDriver() != null ? item.getDriver().getUserId() : null;
            query.setParameter(++i, driverId);
        }

        Long trackingNumber = (Long) query.getSingleResult();
        //TODO -допилить
//        String orderItemSql = "";
//        for (int i = 0; i < orderEntity.getItems().size(); i++) {
//            orderItemSql += "INSERT INTO taxi_order_item " +
//                    "(tracking_numer, path, ordered_quantity, driver_id) " +
//                    "VALUES (?1, ?2, ?3, ?4)";
//        }


        return trackingNumber;
    }
    @Override
    public void addArriveDate(Timestamp arriveDate,long trackingNumber) {
        String sql="UPDATE taxi_order SET arrive_date=(?1) WHERE tracking_number=(?2)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,arriveDate);
        query.setParameter(2,trackingNumber);
        query.executeUpdate();
    }
    @Override
    public void addEndDate(Timestamp endDate,long trackingNumber) {
        String sql="UPDATE taxi_order SET end_date=(?1) WHERE tracking_number=(?2)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,endDate);
        query.setParameter(2,trackingNumber);
        query.executeUpdate();
    }

    @Override
    public List<TaxiOrderEntity> getQueuedOrders() {
        return null;
    }

    @Override
    public TaxiOrderEntity getOrder(Long trackingNumber) {
        String sql="SELECT * FROM taxi_order WHERE tracking_number=(?)";

        Query query = entityManager.createNativeQuery(sql,TaxiOrderEntity.class);
        query.setParameter(1,trackingNumber);
        return (TaxiOrderEntity)query.getSingleResult();
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
    public int updateOrder(TaxiOrderEntity entity) {
        Query query = entityManager.createNativeQuery("UPDATE taxi_order SET status = ?1," +
                "driver_sex = ?2, music_style = ?3, animal_transportation = ?4, " +
                "free_wifi = ?5, non_smoking_driver = ?6, air_conditioner = ?7 WHERE tracking_number = ?8");
        query.setParameter(1, entity.getStatus().toString());
        query.setParameter(2, entity.getDriverSex().toString());
        query.setParameter(3, entity.getMusicStyle().toString());
        query.setParameter(4, entity.getAnimalTransportation());
        query.setParameter(5, entity.getFreeWifi());
        query.setParameter(6, entity.getNonSmokingDriver());
        query.setParameter(7, entity.getAirConditioner());
        query.setParameter(8, entity.getTrackingNumber());
        return query.executeUpdate();
    }
}
