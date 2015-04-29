package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.Local;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author kstes_000
 * @author Maria Komar
 */
@Local
public interface TaxiOrderDAO {
    public void addComment(TaxiOrderEntity taxiOrderEntity);

    Integer addOrder(TaxiOrderEntity taxiOrderEntity);

    public List<TaxiOrderEntity> getAvailableOrders(ServiceUserEntity driver);

    public List<TaxiOrderEntity> getHistoryOfOrders(ServiceUserEntity driver);

    public TaxiOrderEntity getAssignedOrder(ServiceUserEntity driver);

    public void setAssignOrder(ServiceUserEntity driver, TaxiOrderEntity taxiOrderEntity, Timestamp carArriveTime);

    public void setInProgressOrder(TaxiOrderEntity taxiOrderEntity);

    public void setCompletedOrder(TaxiOrderEntity taxiOrderEntity);

    public void setRefusedOrder(TaxiOrderEntity taxiOrderEntity);
}
