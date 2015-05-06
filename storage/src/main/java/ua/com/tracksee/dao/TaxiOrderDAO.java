package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

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
    int ORDERS_PAGE_SIZE = 2;

    void addComment(TaxiOrderEntity taxiOrderEntity);

    /**
     * Adds order to database.
     *
     * @param order taxi order entity which will be insert into database
     */
    Long addOrder(TaxiOrderEntity order);

    List<TaxiOrderEntity> getQueuedOrders();

    TaxiOrderEntity getOrder(Long trackingNumber);

    /**
     * @author Vadym Akymov
     * @param partNumber - number of orders portion
     * @return list of order's item
     */
    List<TaxiOrderEntity> getOrdersPerPage(int partNumber);

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
