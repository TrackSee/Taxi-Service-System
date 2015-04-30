package ua.com.tracksee.logic.driver;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Maria Komar on 30.04.2015.
 */
@Stateless
public class DriverOrderBean {
    @EJB
    private TaxiOrderDAO taxiOrderDao;

    public List<TaxiOrderEntity> getAvailableOrders(ServiceUserEntity driver){
        return taxiOrderDao.getAvailableOrders(driver);
    }

    public List<TaxiOrderEntity> getHistoryOfOrders(int id){
        return taxiOrderDao.getHistoryOfOrders(id);
    }

    public TaxiOrderEntity getAssignedOrder(ServiceUserEntity driver){
        return taxiOrderDao.getAssignedOrder(driver);
    }

    public void setAssignOrder(ServiceUserEntity driver, TaxiOrderEntity taxiOrderEntity, Timestamp carArriveTime){
        taxiOrderDao.setAssignOrder(driver, taxiOrderEntity, carArriveTime);
    }

    public void setInProgressOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setInProgressOrder(taxiOrderEntity);
    }

    public void setCompletedOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setCompletedOrder(taxiOrderEntity);
    }

    public void setRefusedOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setRefusedOrder(taxiOrderEntity);
    }

    public int getOrdersPagesCount(int id){
        return taxiOrderDao.getOrdersPagesCount(id);
    }
}
