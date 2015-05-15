package ua.com.tracksee.logic.driver;


import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.EmailBean;
import ua.com.tracksee.logic.OrderRefusingBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by Maria Komar on 30.04.2015.
 */
@Stateless
public class DriverOrderBean {
    @EJB
    private TaxiOrderDAO taxiOrderDao;

    @EJB
    private OrderRefusingBean orderRefusingBean;

    @EJB
    private EmailBean emailBean;

    public List<TaxiOrderEntity> getAvailableOrders(UserEntity driver, int pageNumber){
        return taxiOrderDao.getAvailableOrders(driver, pageNumber);
    }

    public List<TaxiOrderEntity> getHistoryOfOrders(int id, int pageNumber){
        return taxiOrderDao.getHistoryOfOrders(id, pageNumber);
    }

    public List<TaxiOrderEntity> getAssignedOrders(int id, int pageNumber){
        return taxiOrderDao.getAssignedOrders(id, pageNumber);
    }

    public void setAssignOrder(int driverId, String trackingNumber, String carArriveTime){
        int trackingNumberInt = Integer.parseInt(trackingNumber);
        Timestamp carArriveTimeTimestamp = Timestamp.valueOf(carArriveTime);
        taxiOrderDao.setAssignOrder(driverId, trackingNumberInt, carArriveTimeTimestamp);
    }

    public int setInProgressOrder(String trackingNumber){
        int trackingNumberInt = Integer.parseInt(trackingNumber);
        return taxiOrderDao.setInProgressOrder(trackingNumberInt);
    }

    public void setCompletedOrder(String trackingNumber){
        int trackingNumberInt = Integer.parseInt(trackingNumber);
        taxiOrderDao.setCompletedOrder(trackingNumberInt);
    }

    //When customer not arrived to car, driver refuse order and we +1 to refusedTimes by this user.
    public void setRefusedOrder(String trackingNumber){
        long trackingNumberInt = Long.parseLong(trackingNumber);
        orderRefusingBean.refuseOrder(trackingNumberInt);
    }

    public TaxiOrderEntity getOrder(Long trackingNumber){
        return taxiOrderDao.getOrder(trackingNumber);
    }

    //TODO write some complicated business logic. What to do with time of car aarive?
    public void setToQueueOrder(String trackingNumber) throws MessagingException {
        int trackingNumberInt = Integer.parseInt(trackingNumber);
        Long trackingNumberLong = Long.parseLong(trackingNumber);
        taxiOrderDao.setToQueueOrder(trackingNumberInt);
        emailBean.sendChangingTOFromAssignedToRefusedMadeByDriver(getOrder(trackingNumberLong));
    }

    public int getOrdersPagesCount(int id){
        return taxiOrderDao.getOrdersPagesCount(id);
    }
}
