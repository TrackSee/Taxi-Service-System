package ua.com.tracksee.logic.driver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.EmailBean;
import ua.com.tracksee.logic.OrderRefusingBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
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
    private static final Logger logger = LogManager.getLogger(DriverOrderBean.class);


    private @EJB TaxiOrderDAO taxiOrderDao;
    private @EJB OrderRefusingBean orderRefusingBean;
    private @EJB EmailBean emailBean;
    private @EJB DriverBean driverBean;

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
        Timestamp carArriveTimeTimestamp;
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
//        long trackingNumberLong = trackingNumberInt;
//        UserEntity driver = driverBean.getUserById(driverId);
//        TaxiOrderEntity taxiOrderEntity = taxiOrderDao.getOrder(trackingNumberLong);
//        try {
//            emailBean.sendChangingTOFromAssignedToInProgress(taxiOrderEntity, driver);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        return taxiOrderDao.setInProgressOrder(trackingNumberInt);
    }

    public void setCompletedOrder(String trackingNumber){
        int trackingNumberInt = Integer.parseInt(trackingNumber);
        long trackingNumberLong = trackingNumberInt;
        TaxiOrderEntity taxiOrderEntity = taxiOrderDao.getOrder(trackingNumberLong);
        try {
            emailBean.sendChangingTOFromInProgressToCompleted(taxiOrderEntity);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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
        Long trackingNumberLong = Long.parseLong(trackingNumber);
        TaxiOrderEntity taxiOrderEntity = taxiOrderDao.getOrder(trackingNumberLong);
        taxiOrderDao.setToQueueOrder(trackingNumberInt);
        try {
            emailBean.sendChangingTOFromAssignedToRefusedMadeByDriver(taxiOrderEntity);
        }
        catch(MessagingException e){
            logger.warn("Message to customer not sent!");
            System.out.print("Message to customer not sent!");
        }
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
