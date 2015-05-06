package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.ejb.Local;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author kstes_000
 * @author Maria Komar
 */
@Local
public interface TaxiOrderDAO {
    int ORDERS_PAGE_SIZE = 1;

    public void addComment(TaxiOrderEntity taxiOrderEntity);

    Integer addOrder(TaxiOrderEntity taxiOrderEntity);

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
