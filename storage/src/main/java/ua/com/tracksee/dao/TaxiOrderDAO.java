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
     * Adds order to database.
     *
     * @param order taxi order entity which will be insert into database
     */
    Integer addOrder(TaxiOrderEntity order);

    List<TaxiOrderEntity> getQueuedOrders();

    TaxiOrderEntity getOrder(Integer trackingNumber);

    /**
     * @author Vadym Akymov
     * @param pageNumber - number of orders portion
     * @return list of order's item
     */
    List<TaxiOrderEntity> getOrdersPerPage(int pageNumber);
}
