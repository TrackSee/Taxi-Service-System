package ua.com.tracksee.logic.driver;


import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.OrderRefusingBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Maria Komar on 30.04.2015.
 */
@Stateless
public class DriverOrderBean {
    //private static final Logger logger = LogManager.getLogger();

    @EJB
    private TaxiOrderDAO taxiOrderDao;

    @EJB
    private OrderRefusingBean orderRefusingBean;

//    @EJB
//    private EmailBean emailBean;

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
        System.out.println("carArriveTime"+carArriveTime);
        Timestamp carArriveTimeTimestamp=null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = dateFormat.parse(carArriveTime);
            carArriveTimeTimestamp= new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            System.out.println("Invalid or missing date, cannot be parsed");
            carArriveTimeTimestamp = null;
        }
        TaxiOrderEntity taxiOrderEntity=new TaxiOrderEntity();
        taxiOrderEntity.setArriveDate(carArriveTimeTimestamp);
        System.out.println("dateTimezone"+taxiOrderEntity.getArriveDate());
       // Timestamp carArriveTimeTimestamp = Timestamp.valueOf(carArriveTime);
        taxiOrderDao.setAssignOrder(driverId, trackingNumberInt, taxiOrderEntity.getArriveDate());
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
        long trackingNumberLong = Long.parseLong(trackingNumber);
        orderRefusingBean.refuseOrder(trackingNumberLong);
    }

    //TODO complete
    public void setToQueueOrder(String trackingNumber) {
        int trackingNumberInt = Integer.parseInt(trackingNumber);
//        Long trackingNumberLong = Long.parseLong(trackingNumber);
        taxiOrderDao.setToQueueOrder(trackingNumberInt);
//        try {
//            emailBean.sendChangingTOFromAssignedToRefusedMadeByDriver(getOrder(trackingNumberLong));
//        }
//        catch(MessagingException e){
//            logger.warn("Message to customer not sent!");
//            System.out.print("Message to customer not sent!");
        //}
    }

    public BigInteger getOrdersPagesCountCompleted(int id){
        return taxiOrderDao.getOrdersPagesCountCompleted(id);
    }

    public BigInteger getOrdersPagesCountQueued(UserEntity driver){
        return taxiOrderDao.getOrdersPagesCountQueued(driver);
    }

    public BigInteger getOrdersPagesCountAssigned(int id){
        return taxiOrderDao.getOrdersPagesCountAssigned(id);
    }


}
