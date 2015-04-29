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

    void addComment(TaxiOrderEntity taxiOrderEntity);

    /**
     * Adds order to database.
     *
     * @param order taxi order entity which will be insert into database
     */
    Integer addOrder(TaxiOrderEntity order);

    List<TaxiOrderEntity> getQueuedOrders();

    TaxiOrderEntity getOrder(Integer trackingNumber);
}
