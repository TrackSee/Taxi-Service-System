package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.MostPopularOption;
//import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Postgresql database implementation of
 * {@link TaxiOrderDAO} interface.</p>
 * <p>Used for persisting and accessing taxi order data.</p>
 *
 * @author kstes_000
 * @author Ruslan Gunavardana
 * @author Sharaban Sasha
 * @see ua.com.tracksee.dao.TaxiOrderDAO
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

        return (Long) query.getSingleResult();
    }

    @Override
    public void addArriveDate(Timestamp arriveDate, long trackingNumber) {
        String sql = "UPDATE taxi_order SET arrive_date=(?1) WHERE tracking_number=(?2)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, arriveDate);
        query.setParameter(2, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public void addEndDate(Timestamp endDate, long trackingNumber) {
        String sql = "UPDATE taxi_order SET end_date=(?1) WHERE tracking_number=(?2)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, endDate);
        query.setParameter(2, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public List<TaxiOrderEntity> getQueuedOrders() {
        return null;
    }

    @Override
    public TaxiOrderEntity getOrder(Long trackingNumber) {
        String sql = "SELECT * FROM taxi_order WHERE tracking_number=(?)";

        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, trackingNumber);
        return (TaxiOrderEntity) query.getSingleResult();
    }

    @Override
    public int getOldTaxiOrderPagesCount() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order WHERE " +
                "status = 'COMPLETED'");
        BigInteger generalOrderCount = (BigInteger) q.getSingleResult();
        return (int) Math.ceil(generalOrderCount.intValue() / (double) TO_ORDERS_PER_PAGE);
    }

//    /**
//     * @author Katia Stetsiuk
//     */
//    @Override
//    public List<ServiceProfitable> getProfitByService(String startDate, String endDate) {
//        String sql = "SELECT service, SUM(price)\n" +
//                "FROM taxi_order\n " +
//                "WHERE ORDERED_DATE BETWEEN '" + startDate + "'" +
//                " AND '" + endDate + "'" +
//                "GROUP BY service";
//        Query query = entityManager.createNativeQuery(sql);
//        List<Object[]> list = query.getResultList();
//        List<ServiceProfitable> profitList = new ArrayList<>();
//        ServiceProfitable pe;
//        for (Object[] objects : list) {
//            String s = (String) objects[0];
//            BigDecimal b = (BigDecimal) objects[1];
//            pe = new ServiceProfitable(s, b.doubleValue());
//            profitList.add(pe);
//        }
//        return profitList;
//        // return query.getResultList();
//    }

    /**
     * @author Katia Stetsiuk
     */
    public List<MostPopularOption> getMostPopularOptionsForUser(Integer userId) {
        String options[] = {"AnimalTransportation", "MusicStyle", "FreeWifi", "NonSmokingDriver",
                "AirConditioner", "DriverSex", "WayOfPayment", "carCategory"};
        BigInteger counts[] = {getCountOptionalBool("animal_transportation", userId), getCountOptionalChar("music_style", userId),
                getCountOptionalBool("free_wifi", userId), getCountOptionalBool("non_smoking_driver", userId),
                getCountOptionalBool("air_conditioner", userId), getCountOptionalChar("driver_sex", userId),
                getCountOptionalChar("way_of_payment", userId), getCountOptionalChar("car_category", userId)};
        List<MostPopularOption> listOptions = new ArrayList<>();
        for (int i = 0; i < options.length; i++) {
            listOptions.add(new MostPopularOption(options[i], counts[i]));
        }
        return listOptions;
    }

    /**
     * @author Katia Stetsiuk
     */
    public BigInteger getCountOptionalBool(String option, Integer userId) {
        String sql = "select count(*) from taxi_order where " + option + " = true " +
                "and user_id = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, userId);
        return (BigInteger) query.getSingleResult();
    }

    /**
     * @author Katia Stetsiuk
     */
    public BigInteger getCountOptionalChar(String option, Integer userId) {
        String sql = "select count(*) from taxi_order where " + option + " is not null " +
                "and user_id = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, userId);
        return (BigInteger) query.getSingleResult();
    }

    @Override
    public List<TaxiOrderEntity> getActiveOrdersPerPage(int partNumber) {
        if (partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order WHERE status != " +
                "'COMPLETED' " +
                "ORDER BY ordered_date DESC LIMIT ?1 OFFSET ?2", TaxiOrderEntity.class);
        query.setParameter(1, TO_ORDERS_PER_PAGE);
        query.setParameter(2, (partNumber - 1) * TO_ORDERS_PER_PAGE);
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getOldOrdersPerPage(int partNumber) {
        if (partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order WHERE status = " +
                "'COMPLETED' " +
                "ORDER BY ordered_date DESC LIMIT ?1 OFFSET ?2", TaxiOrderEntity.class);
        query.setParameter(1, TO_ORDERS_PER_PAGE);
        query.setParameter(2, (partNumber - 1) * TO_ORDERS_PER_PAGE);
        return query.getResultList();
    }

    @Override
    public int getActiveTaxiOrderPagesCount() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order WHERE " +
                "status != 'COMPLETED'");
        BigInteger generalOrderCount = (BigInteger) q.getSingleResult();
        return (int) Math.ceil(generalOrderCount.intValue() / (double) TO_ORDERS_PER_PAGE);
    }

    @Override
    public void updateOrder(TaxiOrderEntity entity) {
        Query query = entityManager.createNativeQuery("UPDATE taxi_order SET " +
                "ordered_date=?1,service=?2,car_category=?3,driver_sex = ?4, music_style = ?5, animal_transportation = ?6, " +
                "free_wifi = ?7, non_smoking_driver = ?8, air_conditioner = ?9,description=?10 WHERE tracking_number = ?11");
        query.setParameter(1, entity.getOrderedDate());
        query.setParameter(2, entity.getService().toString());
        query.setParameter(3, entity.getCarCategory().toString());
        query.setParameter(4, entity.getDriverSex().toString());
        query.setParameter(5, entity.getMusicStyle().toString());
        query.setParameter(6, entity.getAnimalTransportation());
        query.setParameter(7, entity.getFreeWifi());
        query.setParameter(8, entity.getNonSmokingDriver());
        query.setParameter(9, entity.getAirConditioner());
        query.setParameter(10, entity.getDescription());
        query.setParameter(11, entity.getTrackingNumber());
        query.executeUpdate();
    }
}
