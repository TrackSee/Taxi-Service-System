package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Local;
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
     * @author Sharaban Sasha
     * @param order - order entity which will be insert into database
     * @return tracking number
     */
    Long addOrder(TaxiOrderEntity order);

    List<TaxiOrderEntity> getQueuedOrders();

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

    /**
     * @author Oleksandr Kozin
     * @param orderDateFrom -
     * @param orderDateTo -
     * @return list of order's item
     */
    int getOrdersByPeriod(String orderDateFrom, String orderDateTo);
}
