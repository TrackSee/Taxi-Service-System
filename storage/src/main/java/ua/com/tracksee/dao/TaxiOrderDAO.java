package ua.com.tracksee.dao;

import ua.com.tracksee.entities.*;

import javax.ejb.Local;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Interface for persisting and accessing taxi order data.
 *
 * @author Katia Stetsiuk
 * @author Ruslan Gunavardana
 * @author Sharaban Sasha
 */
@Local
public interface TaxiOrderDAO {
    int TO_ORDERS_PER_PAGE = 3;
    int ORDERS_PAGE_SIZE = 10;

    void addComment(TaxiOrderEntity taxiOrderEntity);


    /**
     *@author Igor Gula
     */
    public int updateComment(long trackNumber, String comment);
    /**
     *@author Igor Gula
     */
    public void updateOrder(TaxiOrderEntity entity);
    /**
     * This method insert order into database
     *
     * @author Sharaban Sasha
     * @param order - order entity which will be insert into database
     * @return tracking number of inserted order
     */
    Long addOrder(TaxiOrderEntity order);
    /**
     * This method update inserted order by
     * setting arrive;
     *
     * @author Sharaban Sasha
     * @param arriveDate - arrive date from order
     * @param trackingNumber - tracking number of inserted order
     */
    void addArriveDate(Timestamp arriveDate,long trackingNumber);
    /**
     * This method update inserted order by
     * setting end date;
     *
     * @author Sharaban Sasha
     * @param endDate - end date from order
     * @param trackingNumber - tracking number of inserted order
     */
    void addEndDate(Timestamp endDate,long trackingNumber);

    List<TaxiOrderEntity> getQueuedOrders();

    /**
     * Returns object that contain
     * information about order with
     * such tracking number.
     *
     * @author Sharaban Sasha
     * @param trackingNumber  tracking number of order
     * @return object that contain all information about order
     */
    TaxiOrderEntity getOrder(Long trackingNumber);

    /**
     * Checks order present in
     * database with such trackingNumber.
     *
     * @author Sharaban Sasha
     * @param trackingNumber  tracking number of order
     * @return state of order presenting
     */
    boolean checkOrderPresent(Long trackingNumber);

    /**
     * @author Vadym Akymov
     * @param partNumber - number of orders portion
     * @return list of order's item
     */
    List<TaxiOrderEntity> getOrdersPerPage(int partNumber);

    List<TaxiOrderEntity> getCustomerActiveOrdersPerPage(int userID, int partNumber);
    List<TaxiOrderEntity> getCustomerOldOrdersPerPage(int userID, int partNumber);

    /**
     * @author Vadym Akymov
     * @return pages count of taxi order
     */
    int getActiveTaxiOrderPagesCount(int userID);
    int getOldTaxiOrderPagesCount(int userID);
    /**
     * @author Oleksandr Kozin
     * @param startDate start date of the period
     * @param endDate end date of the period
     * @return number of orders per period
     */
    int getOrdersByPeriod(String startDate, String endDate);

    /**
     * @author Oleksandr Kozin
     * @return map with most popular additional car options overall
     */
    Map<String, Integer> mostPopularAdditionalCarOptOverall();
    /**
     * @author Katia Stetsiuk
     * @param startDate
     * @param endDate
     * @return
     */

    List<ServiceProfitable> getProfitByService(String startDate, String endDate);
    BigInteger getCountOptionalChar(String option, Integer userId);
    List<MostPopularOption> getMostPopularOptionsForUser(Integer userId);
    BigInteger getCountOptionalBool(String option, Integer userId);

    public List<TaxiOrderEntity> getAvailableOrders(UserEntity driver, int pageNumber);

    public List<TaxiOrderEntity> getHistoryOfOrders(int id, int pageNumber);

    public List<TaxiOrderEntity> getAssignedOrders(int id);

    public void setAssignOrder(int driverId, int trackingNumber, Timestamp carArriveTime);

    public void setInProgressOrder(int trackingNumber);

    public void setCompletedOrder(int trackingNumber);

    public void setRefusedOrder(int trackingNumber);

    public int getOrdersPagesCount(int id);

    public TaxiOrderItemEntity getPgPath(TaxiOrderEntity taxiOrderEntity);
}
