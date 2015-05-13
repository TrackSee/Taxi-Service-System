package ua.com.tracksee.dao.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String IS_DRIVER_GENDER_NULL = "'A'";

    @Override
    public int updateComment(long trackNumber, String comment) {
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
        BigInteger trackingNumber;
        List<TaxiOrderItemEntity> itemList = order.getItemList();
        StringBuilder sql = new StringBuilder("INSERT INTO taxi_order " +
                "(description,status,price,user_id,service,car_category,way_of_payment,driver_sex," +
                "music_style,animal_transportation,free_wifi,non_smoking_driver,air_conditioner,ordered_date) " +
                "VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14) RETURNING tracking_number; ");

        for (int i = 0; i < itemList.size(); i++) {
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

        int i = 14; // should be incremented before use
        for (TaxiOrderItemEntity item : itemList) {
            query.setParameter(++i, item.getPath());
            query.setParameter(++i, item.getOrderedQuantity());

            Integer driverId = item.getDriver() != null ? item.getDriver().getUserId() : null;
            query.setParameter(++i, driverId);
        }
        trackingNumber = (BigInteger) query.getSingleResult();
        return trackingNumber.longValue();
    }

    @Override
    public int getOrdersByPeriod(String startDate, String endDate) {
        String sql = "SELECT COUNT(*) FROM taxi_order" +
                " WHERE ordered_date" +
                " BETWEEN '" + startDate + "'" +
                " AND '" + endDate + "'";
        return getInteger(sql);
    }

    /**
     * @param sql query to count the number of orders with an additional option
     * @return number of orders with this additional option
     * @author Oleksandr Kozin
     */
    private Integer getInteger(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        BigInteger bigInteger = (BigInteger) query.getSingleResult();
        return bigInteger.intValue();
    }

    @Override
    public Map<String, Integer> mostPopularAdditionalCarOptOverall() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT COUNT(*) FROM taxi_order";
        Integer integer = getInteger(sql);
        map.put("all_orders", integer);
        String[] options = {"free_wifi", "animal_transportation", "non_smoking_driver", "air_conditioner"};
        for (int i = 0; i < options.length; i++) {
            Integer amount = getAmount(options[i]);
            map.put(options[i], amount);
        }
        return map;
    }

    /**
     * It counts the number of orders from this additional option
     *
     * @param option the name of an additional option
     * @return number of orders with this additional option
     * @author Oleksandr Kozin
     */
    private Integer getAmount(String option) {
        String sql = "SELECT COUNT(*) FROM taxi_order" +
                " WHERE " + option + " = TRUE";
        return getInteger(sql);
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
    public boolean checkOrderPresent(Long trackingNumber) {
        boolean state = false;
        String sql = "SELECT * FROM taxi_order WHERE tracking_number=(?)";

        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, trackingNumber);
        try {
            if (query.getSingleResult() != null) {
                state = true;
            }
        } catch (NoResultException e) {
            logger.error("Order with such tracking number: " + trackingNumber + " was not found " + e);
        }
        return state;
    }

    @Override
    public int getOldTaxiOrderPagesCount(int userID) {
        if (userID < 0) {
            logger.warn("userID can't be < 0");
            throw new IllegalArgumentException("userID can't be < 0");
        }
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order WHERE " +
                "status = 'COMPLETED' AND user_id = ?1");
        q.setParameter(1, userID);
        BigInteger generalOrderCount = (BigInteger) q.getSingleResult();
        return (int) Math.ceil(generalOrderCount.intValue() / (double) TO_ORDERS_PER_PAGE);
    }

    /**
     * @author Katia Stetsiuk
     */
    @Override
    public List<ServiceProfitable> getProfitByService(String startDate, String endDate) {
        String sql = "SELECT service, SUM(price)\n" +
                "FROM taxi_order\n " +
                "WHERE ORDERED_DATE BETWEEN '" + startDate + "'" +
                " AND '" + endDate + "'" +
                "GROUP BY service";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        List<ServiceProfitable> profitList = new ArrayList<>();
        ServiceProfitable pe;
        for (Object[] objects : list) {
            String s = (String) objects[0];
            BigDecimal b = (BigDecimal) objects[1];
            pe = new ServiceProfitable(s, b.doubleValue());
            profitList.add(pe);
        }
        return profitList;
        // return query.getResultList();
    }

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
    public List<TaxiOrderEntity> getCustomerActiveOrdersPerPage(int userID, int partNumber) {
        if (partNumber <= 0 || userID < 0) {
            logger.error("One of params is wrong!");
            throw new IllegalArgumentException("One of params is wrong!");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order WHERE user_id =?1 AND status != " +
                "'COMPLETED' AND status != 'REFUSED' " +
                "ORDER BY ordered_date DESC LIMIT ?2 OFFSET ?3", TaxiOrderEntity.class);
        query.setParameter(1, userID);
        query.setParameter(2, TO_ORDERS_PER_PAGE);
        query.setParameter(3, (partNumber - 1) * TO_ORDERS_PER_PAGE);
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getCustomerOldOrdersPerPage(int userID, int partNumber) {
        if (partNumber <= 0 || userID < 0) {
            logger.error("One of params is wrong!");
            throw new IllegalArgumentException("One of params is wrong!");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order WHERE user_id=?1 AND status = " +
                "'COMPLETED' or status = 'REFUSED' " +
                "ORDER BY ordered_date DESC LIMIT ?2 OFFSET ?3", TaxiOrderEntity.class);
        query.setParameter(1, userID);
        query.setParameter(2, TO_ORDERS_PER_PAGE);
        query.setParameter(3, (partNumber - 1) * TO_ORDERS_PER_PAGE);
        return query.getResultList();
    }

    @Override
    public int getActiveTaxiOrderPagesCount(int userID) {
        if (userID < 0) {
            logger.warn("userID can't be < 0");
            throw new IllegalArgumentException("userID can't be < 0");
        }
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order WHERE " +
                "status != 'COMPLETED' AND user_id = ?1");
        q.setParameter(1, userID);
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


    @Override
    public List<TaxiOrderEntity> getOrdersPerPage(int partNumber) {
        if (partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order " +
                "ORDER BY ordered_date LIMIT ?1 OFFSET ?2", TaxiOrderEntity.class);
        query.setParameter(1, TO_ORDERS_PER_PAGE);
        query.setParameter(2, (partNumber - 1) * TO_ORDERS_PER_PAGE);
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getAvailableOrders(UserEntity driver, int pageNumber){
        boolean isNullFreeWifi = false;
        boolean isNullAnimalTransp = false;
        boolean isNullConditioner = false;
        int ADDITIONAL_PARAMETERS = 3;
        StringBuffer sql = new StringBuffer("SELECT * FROM taxi_order WHERE (status =" +
                " 'QUEUED' OR status = 'UPDATED') AND car_category = ? " +
                "AND (driver_sex = ? OR driver_sex = " + IS_DRIVER_GENDER_NULL + ") ");
        if (driver.getCar().getAnimalTransportationApplicable() == false) {
            sql.append("AND animal_transportation = ? ");
            isNullAnimalTransp = true;
        }
        if (driver.getCar().getFreeWifi() == false) {
            sql.append("AND free_wifi = ? ");
            isNullFreeWifi = true;
        }
        if (driver.getCar().getAirConditioner() == false) {
            sql.append("AND air_conditioner = ? ");
            isNullConditioner = true;
        }
        sql.append("LIMIT ? OFFSET ?");
        System.out.println(sql.toString());

        Query query = entityManager.createNativeQuery(sql.toString(), TaxiOrderEntity.class);
        query.setParameter(1,driver.getCar().getCarCategory().toString());
        query.setParameter(2, driver.getSex());

        if (isNullAnimalTransp) {
            query.setParameter(ADDITIONAL_PARAMETERS++,driver.getCar().getAnimalTransportationApplicable());
        }
        if (isNullFreeWifi) {
            query.setParameter(ADDITIONAL_PARAMETERS++,driver.getCar().getFreeWifi());
        }
        if (isNullConditioner) {
            query.setParameter(ADDITIONAL_PARAMETERS++,driver.getCar().getAirConditioner());
        }
        query.setParameter(ADDITIONAL_PARAMETERS++, ORDERS_PAGE_SIZE);
        query.setParameter(ADDITIONAL_PARAMETERS, (pageNumber - 1) * ORDERS_PAGE_SIZE);

        return query.getResultList();
    }

//    @Override
//    public List<TaxiOrderEntity> getAvailableOrders(UserEntity driver, int pageNumber) {
//
//        String sql = "SELECT * FROM taxi_order WHERE " +
//                "status = 'QUEUED' OR status = 'UPDATED' " +
//                "AND car_category = ? " +
//                "AND driver_sex = ?" +
//                "AND animal_transportation = ? AND free_wifi = ?" +
//                "AND air_conditioner = ? LIMIT ? OFFSET ?";
//        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
//        query.setParameter(1, driver.getCar().getCarCategory().toString());
//        query.<Boolean>setParameter(2, driver.getSex());
//        query.<Boolean>setParameter(3, driver.getCar().getAnimalTransportationApplicable());
//        query.<Boolean>setParameter(4, driver.getCar().getFreeWifi());
//        query.<Boolean>setParameter(5, driver.getCar().getAirConditioner());
//        query.setParameter(6, ORDERS_PAGE_SIZE);
//        query.setParameter(7, (pageNumber - 1) * ORDERS_PAGE_SIZE);
//        return query.getResultList();
//    }

    @Override
    public List<TaxiOrderEntity> getHistoryOfOrders(int id, int pageNumber) {
        String sql = "SELECT * FROM taxi_order " +
                "INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer" +
                " AND (status = 'COMPLETED' OR status = 'REFUSED')" +
                "AND driver_id = ? LIMIT ? OFFSET ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, id);
        query.setParameter(2, ORDERS_PAGE_SIZE);
        query.setParameter(3, (pageNumber - 1) * ORDERS_PAGE_SIZE);
        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getAssignedOrders(int id, int pageNumber) {
        String sql = "SELECT * FROM taxi_order " +
                "INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer " +
                "AND taxi_order_item.driver_id = ? AND (status = 'ASSIGNED' OR status ='IN_PROGRESS')" +
                "LIMIT ? OFFSET ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, id);
        query.setParameter(2, ORDERS_PAGE_SIZE);
        query.setParameter(3, (pageNumber - 1) * ORDERS_PAGE_SIZE);
        return query.getResultList();
    }

    //@Todo write transaction, get time
    @Override
    public void setAssignOrder(int driverId, int trackingNumber, Timestamp carArriveTime) {
//        try {
        //entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("UPDATE taxi_order_item SET driver_id = ? ");
        //+ "WHERE tracking_number = ?"
        query.setParameter(1, driverId);
        //query.setParameter(2, trackingNumber);
        Query query2 = entityManager.createNativeQuery("UPDATE taxi_order SET status = 'ASSIGNED', ordered_date = ? " +
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
    public int setInProgressOrder(int trackingNumber) {
        String sql = "UPDATE taxi_order SET status = 'IN_PROGRESS' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        return query.executeUpdate();
    }

    @Override
    public void setCompletedOrder(int trackingNumber) {
        String sql = "UPDATE taxi_order SET status = 'COMPLETED' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public void setRefusedOrder(int trackingNumber) {
        String sql = "UPDATE taxi_order SET status = 'REFUSED' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public void setToQueueOrder(int trackingNumber){
        String sql = "UPDATE taxi_order SET status = 'QUEUED' WHERE tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, trackingNumber);
        query.executeUpdate();
    }


    @Override
    public int getOrdersPagesCount(int id) {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_numer " +
                "AND taxi_order_item.driver_id = ? AND taxi_order.status = 'COMPLETED'");
        q.setParameter(1, id);
        Integer driversCount = ((BigInteger) q.getSingleResult()).intValue();
        return (int) (Math.ceil((double) driversCount / ORDERS_PAGE_SIZE));
    }

    @Override
    public TaxiOrderItemEntity getPgPath(TaxiOrderEntity taxiOrderEntity) {
        String sql = "SELECT * FROM taxi_order_item " +
                "INNER JOIN taxi_order " +
                "ON taxi_order_item.tracking_numer = taxi_order.tracking_number " +
                "AND tracking_numer = ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderItemEntity.class);
        query.setParameter(1, taxiOrderEntity.getTrackingNumber());
        return (TaxiOrderItemEntity) query.getSingleResult();
    }
}
