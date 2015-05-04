package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.ejb.Local;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
@Local
public interface TaxiOrderDAO {
    public void addComment(TaxiOrderEntity taxiOrderEntity);
    public List<TaxiOrderItemEntity> getOrderItems(Integer orderId);
    public TaxiOrderEntity getOrder(Integer orderId);
    public int refuseOrder(Integer trackNumber);
    public int updateComment(Integer trackNumber, String comment);
    public int updateOrder(TaxiOrderEntity entity);
}
