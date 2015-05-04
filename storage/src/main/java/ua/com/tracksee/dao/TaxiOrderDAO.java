package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Local;
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

    void addComment(TaxiOrderEntity taxiOrderEntity);

    /**
     *@author Igor Gula
     */
    public int updateComment(Integer trackNumber, String comment);
    /**
     *@author Igor Gula
     */
    public int updateOrder(TaxiOrderEntity entity);
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
    List<TaxiOrderEntity> getActiveOrdersPerPage(int partNumber);
    List<TaxiOrderEntity> getOldOrdersPerPage(int partNumber);

    /**
     * @author Vadym Akymov
     * @return pages count of taxi order
     */
    int getActiveTaxiOrderPagesCount();
    int getOldTaxiOrderPagesCount();
}
