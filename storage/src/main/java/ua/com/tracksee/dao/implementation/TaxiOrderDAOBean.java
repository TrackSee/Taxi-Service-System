package ua.com.tracksee.dao.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgis.LineString;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.*;
import ua.com.tracksee.enumartion.CarCategory;
import ua.com.tracksee.enumartion.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * <p>Postgresql database implementation of
 * {@link TaxiOrderDAO} interface.</p>
 * <p>Used for persisting and accessing taxi order data.</p>
 *
 * @author kstes_000
 * @author Ruslan Gunavardana
 * @author Sharaban Sasha
 * @author Maria Komar
 * @see ua.com.tracksee.dao.TaxiOrderDAO
 */
@Stateless
public class TaxiOrderDAOBean implements TaxiOrderDAO {
    private static final Logger logger = LogManager.getLogger();
    private static final String IS_DRIVER_GENDER_NULL = "'A'";
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

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
    public void addEnumCarCategory(CarCategory carCategory, long trackingNumber){
        Query query = entityManager.createNativeQuery(
                "UPDATE  taxi_order SET service = ?1 WHERE tracking_number = ?2");
        query.setParameter(1, carCategory.toString());
        query.setParameter(2, trackingNumber);
        query.executeUpdate();
    }
    @Override
    public Long addOrder(TaxiOrderEntity order) {
        return entityManager.merge(order).getTrackingNumber();
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
    public void addCelebrationTaxiParam(int amountOfCars, long trackingNumber){
        String sql = "UPDATE taxi_order SET amount_of_cars=(?1) WHERE tracking_number=(?2)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, amountOfCars);
        query.setParameter(2, trackingNumber);
        query.executeUpdate();
    }
    @Override
    public void addLongTermTaxiParams(int amountOfHours,int amountOfMinutes, long trackingNumber){
        String sql = "UPDATE taxi_order SET amount_of_hours=(?1)," +
                "amount_of_minutes=(?2) WHERE tracking_number=(?3)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, amountOfHours);
        query.setParameter(2, amountOfMinutes);
        query.setParameter(3, trackingNumber);
        query.executeUpdate();
    }

    @Override
    public List<TaxiOrderEntity> getQueuedOrders() {
        return null;
    }

    @Override
    public TaxiOrderEntity getOrder(Long trackingNumber) {
        String sql = "SELECT * FROM taxi_order " +
                "WHERE taxi_order.tracking_number=(?)";

        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, trackingNumber);
        return (TaxiOrderEntity) query.getSingleResult();
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.TaxiOrderDAO
     */
    @Override
    public boolean checkOrderPresentNonActiveUser(Long trackingNumber) {
        boolean state = false;
        String sql = "SELECT tracking_number,description,status,price,taxi_order.user_id,service,car_category," +
                "way_of_payment,driver_sex, music_style,animal_transportation,free_wifi,non_smoking_driver," +
                "air_conditioner,ordered_date,arrive_date,amount_of_cars,amount_of_hours,amount_of_minutes,comment" +
                " FROM taxi_order " +
                "INNER JOIN service_user "+
                "ON taxi_order.user_id= service_user.user_id " +
                "WHERE tracking_number=(?1) AND activated=FALSE";

        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, trackingNumber);
        List list = query.getResultList();
        try {
            if (list.size()!=0) {
                state = true;
            }
        } catch (NoResultException e) {
            logger.error("Order with such tracking number: " + trackingNumber + " was not found " + e);
        }
        return state;
    }
    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.TaxiOrderDAO
     */
    @Override
    public boolean checkOrderPresentActiveUser(Long trackingNumber) {
        boolean state = false;
        String sql = "SELECT tracking_number,description,status,price,taxi_order.user_id,service,car_category," +
                "way_of_payment,driver_sex, music_style,animal_transportation,free_wifi,non_smoking_driver," +
                "air_conditioner,ordered_date,arrive_date,amount_of_cars,amount_of_hours,amount_of_minutes,comment" +
                " FROM taxi_order " +
                "INNER JOIN service_user "+
                "ON taxi_order.user_id= service_user.user_id " +
                "WHERE tracking_number=(?1) AND activated=TRUE";

        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, trackingNumber);
        List list = query.getResultList();
        try {
            if (list.size()!=0) {
                state = true;
            }
        } catch (NoResultException e) {
            logger.error("Order with such tracking number: " + trackingNumber + " was not found " + e);
        }
        return state;
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.TaxiOrderDAO
     */
    @Override
    public boolean checkOrderPresentForActiveUser(Long trackingNumber, int userId){
        boolean state = false;
        String sql = "SELECT tracking_number,description,status,price,taxi_order.user_id,service,car_category," +
                "way_of_payment,driver_sex, music_style,animal_transportation,free_wifi,non_smoking_driver," +
                "air_conditioner,ordered_date,arrive_date,amount_of_cars,amount_of_hours,amount_of_minutes,comment" +
                " FROM taxi_order " +
                "INNER JOIN service_user "+
                "ON taxi_order.user_id= service_user.user_id " +
                "WHERE tracking_number=(?1) AND taxi_order.user_id=(?2) AND activated=TRUE";

        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, trackingNumber);
        query.setParameter(2, userId);
        List list = query.getResultList();
        try {
            if (list.size()!=0) {
                state = true;
            }
        } catch (NoResultException e) {
            logger.error("Order with such tracking number: " + trackingNumber + " was not found " + e);
        }
        return state;
    }

    @Override
    public int getOldTaxiOrderPagesCount(int userID) {
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
        Query query = entityManager.createNativeQuery("SELECT * FROM taxi_order WHERE user_id= ?1 AND (status = " +
                "'COMPLETED' OR status = 'REFUSED') " +
                "ORDER BY ordered_date DESC LIMIT ?2 OFFSET ?3", TaxiOrderEntity.class);
        query.setParameter(1, userID);
        query.setParameter(2, TO_ORDERS_PER_PAGE);
        query.setParameter(3, (partNumber - 1) * TO_ORDERS_PER_PAGE);
        System.out.println("return size: " + query.getResultList().size());
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
        StringBuilder sql = new StringBuilder("SELECT * FROM taxi_order WHERE (status =" +
                " 'QUEUED' OR status = 'UPDATED') " +
                " AND (driver_sex = ? OR driver_sex = " + IS_DRIVER_GENDER_NULL + ") ");
        if (driver.getCar().getAnimalTransportationApplicable() == false) {
            sql.append(" AND animal_transportation = false ");
        }
        if (driver.getCar().getFreeWifi() == false) {
            sql.append(" AND free_wifi = false ");
        }
        if (driver.getCar().getAirConditioner() == false) {
            sql.append(" AND air_conditioner = false ");
        }
        if (driver.getCar().getAcceptsVisa() == false) {
            sql.append(" AND way_of_payment = 'CASH' ");
        }
        if (driver.getCar().getCarCategory().toString().equals("VAN")) {
            sql.append(" AND (car_category = 'ECONOMY_CLASS' OR car_category = 'VAN' OR car_category = 'USER_CAR') ");
        } else if (driver.getCar().getCarCategory().toString().equals("BUSINESS_CLASS")) {
            sql.append(" AND (car_category = 'ECONOMY_CLASS' OR car_category = 'BUSINESS_CLASS' OR car_category = 'USER_CAR') ");
        } else if (driver.getCar().getCarCategory().toString().equals("ECONOMY_CLASS")) {
            sql.append(" AND (car_category = 'ECONOMY_CLASS' OR car_category = 'USER_CAR') ");
        }

        sql.append("LIMIT ? OFFSET ?");

        Query query = entityManager.createNativeQuery(sql.toString(), TaxiOrderEntity.class);

        query.setParameter(1, driver.getSex());
        query.setParameter(2, ORDERS_PAGE_SIZE);
        query.setParameter(3, (pageNumber - 1) * ORDERS_PAGE_SIZE);

        return query.getResultList();
    }

    @Override
    public List<TaxiOrderEntity> getHistoryOfOrders(int id, int pageNumber) {
        String sql = "SELECT * FROM taxi_order " +
                "INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_number" +
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
                "ON taxi_order.tracking_number = taxi_order_item.tracking_number " +
                "AND taxi_order_item.driver_id = ? AND (status = 'ASSIGNED' OR status ='IN_PROGRESS')" +
                "LIMIT ? OFFSET ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderEntity.class);
        query.setParameter(1, id);
        query.setParameter(2, ORDERS_PAGE_SIZE);
        query.setParameter(3, (pageNumber - 1) * ORDERS_PAGE_SIZE);
        return query.getResultList();
    }

    @Override
    public void setAssignOrder(int driverId, int trackingNumber, Timestamp carArriveTime) {
        Query query = entityManager.createNativeQuery("UPDATE taxi_order_item SET driver_id = ? ");
        query.setParameter(1, driverId);
        Query query2 = entityManager.createNativeQuery("UPDATE taxi_order SET status = 'ASSIGNED', arrive_date = ? " +
                "WHERE tracking_number = ?");
        query2.setParameter(1, carArriveTime);
        query2.setParameter(2, trackingNumber);
        query.executeUpdate();
        query2.executeUpdate();
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
        Date completedOrderDate = new Date();
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
    public int getOrdersPagesCountCompleted(int id) {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_number " +
                "AND taxi_order_item.driver_id = ? AND (taxi_order.status = 'COMPLETED' OR taxi_order.status = 'REFUSED')");
        q.setParameter(1, id);
        Integer driversCount = ((BigInteger) q.getSingleResult()).intValue();
        return (int) (Math.ceil((double) driversCount / ORDERS_PAGE_SIZE));
    }

    @Override
    public int getOrdersPagesCountAssigned(int id) {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM taxi_order INNER JOIN taxi_order_item " +
                "ON taxi_order.tracking_number = taxi_order_item.tracking_number " +
                "AND taxi_order_item.driver_id = ? AND (taxi_order.status = 'ASSIGNED' OR taxi_order.status ='IN_PROGRESS')");
        q.setParameter(1, id);
        Integer driversCount = ((BigInteger) q.getSingleResult()).intValue();
        return (int) (Math.ceil((double) driversCount / ORDERS_PAGE_SIZE));
    }

    @Override
    public int getOrdersPagesCountQueued(UserEntity driver) {
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM taxi_order WHERE (status =" +
                " 'QUEUED' OR status = 'UPDATED') " +
                " AND (driver_sex = ? OR driver_sex = " + IS_DRIVER_GENDER_NULL + ") ");
        if (driver.getCar().getAnimalTransportationApplicable() == false) {
            sql.append(" AND animal_transportation = false ");
        }
        if (driver.getCar().getFreeWifi() == false) {
            sql.append(" AND free_wifi = false ");
        }
        if (driver.getCar().getAirConditioner() == false) {
            sql.append(" AND air_conditioner = false ");
        }
        if (driver.getCar().getAcceptsVisa() == false) {
            sql.append(" AND way_of_payment = 'CASH' ");
        }
        if (driver.getCar().getCarCategory().toString().equals("VAN")) {
            sql.append(" AND (car_category = 'ECONOMY_CLASS' OR car_category = 'VAN' OR car_category = 'USER_CAR') ");
        } else if (driver.getCar().getCarCategory().toString().equals("BUSINESS_CLASS")) {
            sql.append(" AND (car_category = 'ECONOMY_CLASS' OR car_category = 'BUSINESS_CLASS' OR car_category = 'USER_CAR') ");
        } else if (driver.getCar().getCarCategory().toString().equals("ECONOMY_CLASS")) {
            sql.append(" AND (car_category = 'ECONOMY_CLASS' OR car_category = 'USER_CAR') ");
        }

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, driver.getSex());
        Integer driversCount = ((BigInteger) query.getSingleResult()).intValue();
        return (int) (Math.ceil((double) driversCount / ORDERS_PAGE_SIZE));
    }

    @Override
    public TaxiOrderItemEntity getPgPath(TaxiOrderEntity taxiOrderEntity) {
        String sql = "SELECT * FROM taxi_order_item " +
                "INNER JOIN taxi_order " +
                "ON taxi_order_item.tracking_number = taxi_order.tracking_number " +
                "AND tracking_number = ?";
        Query query = entityManager.createNativeQuery(sql, TaxiOrderItemEntity.class);
        query.setParameter(1, taxiOrderEntity.getTrackingNumber());
        return (TaxiOrderItemEntity) query.getSingleResult();
    }
}
