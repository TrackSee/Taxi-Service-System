package ua.com.tracksee.dao;

import ua.com.tracksee.entities.*;

import javax.ejb.Local;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

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
    public int updateComment(Integer trackNumber, String comment);
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
     * This method returns the order by tracking
     * number.
     *
     * @author Sharaban Sasha
     * @param trackingNumber - the tracking number of which will return the order
     * @return TaxiOrderEntity which contain information about order
     */
    TaxiOrderEntity getOrder(Long trackingNumber);

    /**
     * @author Vadym Akymov
     * @param partNumber - number of orders portion
     * @return list of order's item
     */
    List<TaxiOrderEntity> getOrdersPerPage(int partNumber);

    List<TaxiOrderEntity> getActiveOrdersPerPage(int partNumber);
    List<TaxiOrderEntity> getOldOrdersPerPage(int partNumber);

    /**
     * @author Vadym Akymov
     * @return pages count of taxi order
     */
    int getActiveTaxiOrderPagesCount();
    int getOldTaxiOrderPagesCount();

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

    public List<TaxiOrderEntity> getAvailableOrders(ServiceUserEntity driver, int pageNumber);

    public List<TaxiOrderEntity> getHistoryOfOrders(int id, int pageNumber);

    public List<TaxiOrderEntity> getAssignedOrders(int id);

    public void setAssignOrder(int driverId, int trackingNumber, Timestamp carArriveTime);

    public void setInProgressOrder(int trackingNumber);

    public void setCompletedOrder(int trackingNumber);

    public void setRefusedOrder(int trackingNumber);

    public int getOrdersPagesCount(int id);

    public TaxiOrderItemEntity getPgPath(TaxiOrderEntity taxiOrderEntity);
}
