package ua.com.tracksee.logic.facade;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.enumartion.*;
import ua.com.tracksee.exception.OrderException;
import ua.com.tracksee.logic.*;
import ua.com.tracksee.logic.driver.DriverOrderBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * Session Facade Bean for order subsystem.
 * Used to access any order service system functionality.
 *
 * @author Ruslan Gunavardana
 * @author Sharaban Sasha
 * @author Maria Komar
 */
@Stateless
public class OrderFacade {
    private @EJB OrderRefusingBean orderRefusingBean;
    private @EJB TaxiOrderBean taxiOrderBean;
    private @EJB EnumValidationBean enumValidationBean;
    private @EJB ValidationBean validationBean;
    private @EJB AlertGeneratorBean alertGeneratorBean;
    private @EJB DriverOrderBean driverOrderBean;

    /**
     * @author Ruslan Gunavardana
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @see ua.com.tracksee.logic.TaxiOrderBean
     */
    public Long makeOrder(HashMap<String, String> inputData) throws OrderException {
        return taxiOrderBean.makeOrder(inputData);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.OrderRefusingBean
     */
    public boolean checkBlackListByUserEmail(String email) {
        return orderRefusingBean.checkBlackListByUserEmail(email);
    }

    /**
     * @author Avlasov Sasha
     * @see ua.com.tracksee.logic.OrderRefusingBean
     */
//    public boolean refuseOrder(long trackingNumber) {
//       // return orderRefusingBean.refuseOrder(trackingNumber);
//    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.TaxiOrderBean
     */
    public boolean checkOrderPresent(long trackingNumber) {
        return taxiOrderBean.checkOrderPresent(trackingNumber);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.TaxiOrderBean
     */
    public TaxiOrderEntity getOrderInfo(long trackingNumber) {
        return taxiOrderBean.getOrderInfo(trackingNumber);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.TaxiOrderBean
     */
    public UserEntity getUserInfo(int userId) {
        return taxiOrderBean.getUserInfo(userId);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.ValidationBean
     */
    public String convertDateForShow(Timestamp date) {
        return validationBean.convertDateForShow(date);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.EnumValidationBean
     */
    public String getFromEnumWayOfPayment(WayOfPayment wayOfPayment) {
        return enumValidationBean.getFromEnumWayOfPayment(wayOfPayment);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.EnumValidationBean
     */
    public String getFromEnumService(Service service) {
        return enumValidationBean.getFromEnumService(service);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.EnumValidationBean
     */
    public String getFromEnumMusicStyle(MusicStyle musicStyle) {
        return enumValidationBean.getFromEnumMusicStyle(musicStyle);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.EnumValidationBean
     */
    public String getFromEnumDriverSex(Sex driverSex) {
        return enumValidationBean.getFromEnumDriverSex(driverSex);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.EnumValidationBean
     */
    public String getFromEnumCarCategory(CarCategory carCategory) {
        return enumValidationBean.getFromEnumCarCategory(carCategory);
    }

    /**
     * @author Gula Igor
     * @see ua.com.tracksee.logic.TaxiOrderBean
     */
    public void addComment(long trackingNumber,String comment) {
    taxiOrderBean.updateComment(trackingNumber, comment);
    }

    /**
     * @author Sharaban Sasha
     * @author Gula Igor
     * @see ua.com.tracksee.logic.TaxiOrderBean
     */
    public void updateOrder(HashMap<String,String> inputData) throws OrderException{
         taxiOrderBean.updateOrder(inputData);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.AlertGeneratorBean
     */
    public String getSuccessAlert(String inputText){
        return alertGeneratorBean.getSuccessAlert(inputText);
    }
    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.AlertGeneratorBean
     */
    public String getInfoAlert(String inputText){
        return alertGeneratorBean.getInfoAlert(inputText);
    }
    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.AlertGeneratorBean
     */
    public String getWarningAlert(String inputText){
        return alertGeneratorBean.getWarningAlert(inputText);
    }
    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.logic.AlertGeneratorBean
     */
    public String getDangerAlert(String inputText){
        return alertGeneratorBean.getDangerAlert(inputText);
    }

    public List<TaxiOrderEntity> getHistoryOfOrders(int id, int pageNumber){
        return driverOrderBean.getHistoryOfOrders(id, pageNumber);
    }

    public int getOrdersPagesCount(int id){
        return driverOrderBean.getOrdersPagesCount(id);
    }

    public void setAssignOrder(int id, String trackingNumber, String carArriveTime){
        driverOrderBean.setAssignOrder(id, trackingNumber, carArriveTime);
    }

    public List<TaxiOrderEntity> getAssignedOrders(int id, int pageNumber){
        return driverOrderBean.getAssignedOrders(id, pageNumber);
    }

    public int setInProgressOrder(String trackingNumber){
        return driverOrderBean.setInProgressOrder(trackingNumber);
    }

    public void setCompletedOrder(String trackingNumber){
        driverOrderBean.setCompletedOrder(trackingNumber);
    }

    public void setRefusedOrder(String trackingNumber){
        driverOrderBean.setRefusedOrder(trackingNumber);
    }

    public void setToQueueOrder(String trackingNumber){
        driverOrderBean.setToQueueOrder(trackingNumber);
    }

    public List<TaxiOrderEntity> getAvailableOrders(UserEntity driver, int pageNumber){
        return driverOrderBean.getAvailableOrders(driver, pageNumber);
    }
}
