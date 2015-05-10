package ua.com.tracksee.logic;

import ua.com.tracksee.enumartion.*;

import javax.ejb.Stateless;
import javax.persistence.criteria.Order;

/**
 * This session bean converts enum
 * to a string and back as well,
 * and specify every thrown exception.
 *
 * @author Sharaban Sasha
 */
@Stateless
public class EnumValidationBean {

    /**
     * This method checks there is the such
     * enum  and return it if it exists.
     *
     * @author Sharaban Sasha
     * @param carCategory - string representation of car category
     * @return car category enum
     */

    public CarCategory setEnumCarCategory(String carCategory) {
        CarCategory enumCarCategory;
        try {
            enumCarCategory = CarCategory.valueOf(carCategory);
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.setEnumCarCategory: car category value is null," +
                    " error message: "+e);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("EnumValidationBean.setEnumCarCategory: car category value is illegal: "
                    +carCategory
                    +", error message: "+e);
        }
        return enumCarCategory;
    }
    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @author Sharaban Sasha
     * @param wayOfPayment - string representation of way of payment
     * @return way of payment enum
     */
    public WayOfPayment setEnumWayOfPayment(String wayOfPayment) {
        WayOfPayment enumWayOfPayment;
        try {
            enumWayOfPayment = WayOfPayment.valueOf(wayOfPayment);
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.setEnumWayOfPayment: way of payment value is null," +
                    " error message: "+e);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("EnumValidationBean.setEnumWayOfPayment: way of payment value is " +
                    "illegal:"+wayOfPayment+", error message: "+e);
        }
        return enumWayOfPayment;
    }
    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @author Sharaban Sasha
     * @param driverSex - string representation of driver sex
     * @return driver sex enum
     */
    public Sex setEnumDriverSex(String driverSex) {
        Sex enumDriverSex;
        try {
            enumDriverSex = Sex.valueOf(driverSex);
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.setEnumDriverSex: driver sex value is null," +
                    " error message: "+e);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("EnumValidationBean.setEnumDriverSex: driver sex value is " +
                    "illegal:"+driverSex+", error message: "+e);
        }
        return enumDriverSex;
    }

    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @author Sharaban Sasha
     * @param service - string representation of service
     * @return service enum
     */
    public Service setEnumService(String service) {
        Service enumService;
        try {
            enumService = Service.valueOf(service);
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.setEnumService: service value is null," +
                    " error message: "+e);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("EnumValidationBean.setEnumService: service value is " +
                    "illegal:"+service+", error message: "+e);
        }
        return enumService;
    }

    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @author Sharaban Sasha
     * @param musicStyle - string representation of music style
     * @return music style enum
     */
    public MusicStyle setEnumMusicStyle(String musicStyle) {
        MusicStyle enumMusicStyle;
        try {
            enumMusicStyle = MusicStyle.valueOf(musicStyle);
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.setEnumMusicStyle: music style value is null," +
                    " error message: "+e);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("EnumValidationBean.setEnumMusicStyle: music style value is " +
                    "illegal:"+musicStyle+", error message: "+e);
        }
        return enumMusicStyle;
    }
    /**
     * This method return string
     * representation of music
     * style enum value
     *
     * @author Sharaban Sasha
     * @param enumMusicStyle - enum value of music style
     * @return string name of option that must be selected
     */
    public String getFromEnumMusicStyle(MusicStyle enumMusicStyle){
        String musicStyle;
        try {
            musicStyle = enumMusicStyle.toString();
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.getFromEnumMusicStyle: music style enum is null, " +
                    " error message: "+e);
        }
        return musicStyle;
    }
    /**
     * This method return string
     * representation of service
     * enum value
     *
     * @author Sharaban Sasha
     * @param enumService - enum value of service
     * @return string name of option that must be selected
     */
    public String getFromEnumService(Service enumService){
        String service;
        try {
            service=enumService.toString();
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.getFromEnumService: service enum is null, " +
                    " error message: "+e);
        }
        return service;
    }
    /**
     * This method return string
     * representation of car category
     * enum value
     *
     * @author Sharaban Sasha
     * @param enumCarCategory - enum value of car category
     * @return string name of option that must be selected
     */
    public String getFromEnumCarCategory(CarCategory enumCarCategory){
        String carCategory;
        try {
            carCategory=enumCarCategory.toString();
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.getFromEnumCarCategory: car category enum is null, " +
                    " error message: "+e);
        }
    return carCategory;
    }
    /**
     * This method return string
     * representation of driver
     * sex enum value
     *
     * @author Sharaban Sasha
     * @param enumDriverSex - enum value of driver sex
     * @return string name of option that must be selected
     */
    public String getFromEnumDriverSex(Sex enumDriverSex){
        String driverSex;
        try {
            driverSex=enumDriverSex.toString();
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.getFromEnumDriverSex: driver sex enum is null, " +
                    " error message: "+e);
        }
        return driverSex;
    }
    /**
     * This method return string
     * representation of way of payment
     * enum value
     *
     * @author Sharaban Sasha
     * @param enumWayOfPayment - enum value of way of payment
     * @return string name of option that must be selected
     */
    public String getFromEnumWayOfPayment(WayOfPayment enumWayOfPayment){
        String wayOfPayment;
        try {
            wayOfPayment=enumWayOfPayment.toString();
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.getFromEnumWayOfPayment: enum way of payment is null, " +
                    " error message: "+e);
        }
        return wayOfPayment;
    }

    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @author Sharaban Sasha
     * @param orderStatus - string representation of order status
     * @return order status enum
     */
    public OrderStatus setEnumOrderStatus(String orderStatus) {
        OrderStatus enumOrderStatus;
        try {
            enumOrderStatus = OrderStatus.valueOf(orderStatus);
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.setEnumOrderStatus: order status value is null," +
                    " error message: "+e);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("EnumValidationBean.setEnumOrderStatus: order status value is " +
                    "illegal:"+orderStatus+", error message: "+e);
        }
        return enumOrderStatus;
    }
    /**
     * This method return string
     * representation of order status
     * enum value
     *
     * @author Sharaban Sasha
     * @param enumOrderStatus - enum value of order status
     * @return string name of option that must be selected
     */
    public String  getFromEnumOrderStatus(OrderStatus enumOrderStatus) {
        String orderStatus;
        try {
            orderStatus=enumOrderStatus.toString();
        }catch (NullPointerException e){
            throw new NullPointerException("EnumValidationBean.getFromEnumOrderStatus: enum order status is null, " +
                    " error message: "+e);
        }
        return orderStatus;
    }
}
