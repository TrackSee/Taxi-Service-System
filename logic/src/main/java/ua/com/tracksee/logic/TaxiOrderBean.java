package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTimeZone;
import org.postgresql.util.PGmoney;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.*;
import ua.com.tracksee.json.TaxiOrderDTO;
import ua.com.tracksee.logic.exception.OrderException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static javax.ejb.LockType.WRITE;
import static ua.com.tracksee.enumartion.OrderStatus.QUEUED;

/**
 * Stateless bean used for any order processing business logic.
 *
 * @author Sasha Avlasov
 * @author Sharaban Sasha
 * @author Ruslan Gunavardana
 */
@Stateless
public class TaxiOrderBean {
    private static final Logger logger = LogManager.getLogger();

    private @EJB TaxiOrderDAO taxiOrderDAO;
    private @EJB UserDAO userDAO;
    private @EJB EmailBean mailBean;
    private @EJB ValidationBean validationBean;
    private @EJB PriceCalculatorBean priceCalculatorBean;
    private @EJB EnumValidationBean enumValidationBean;

    /**
     * Creates taxi order for authorised user.
     *
     * @param userId id of authorised customer user
     * @param orderDTO basic information about the order
     */
   // @RolesAllowed("customer")
    public Long createAuthorisedOrder(Integer userId, TaxiOrderDTO orderDTO) {
        TaxiOrderEntity order = new TaxiOrderEntity();
        order.setUserId(userId);
        order.setStatus(QUEUED);
        //TODO service + price calculation at server order.setPrice();

        // collecting data from DTO
        try {
            order.setCarCategory(CarCategory.valueOf(orderDTO.getCarCategory()));
            order.setWayOfPayment(WayOfPayment.valueOf(orderDTO.getWayOfPayment()));
            order.setDriverSex(Sex.valueOf(orderDTO.getDriverSex()));
            order.setMusicStyle(MusicStyle.valueOf(orderDTO.getMusicStyle()));
        } catch (IllegalArgumentException e) {
            logger.warn("Could not parse enum during taxi order creation.");
            return null;
        }
        return order.getTrackingNumber();
    }

    /**
     * This method check input data,insert order in database.
     * Also it insert user in database if he doesn't have  record
     * about him in database. And it send conformation letter with
     * tracking number, also tracking number returns to be shown to
     * user.
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @param inputData- input data about user and his order
     * @exception javax.mail.MessagingException
     * @exception ua.com.tracksee.logic.exception.OrderException
     * @return Integer - tracking number of user order
     */
    public Long makeOrder(HashMap<String, String> inputData) throws OrderException,MessagingException {

        ServiceUserEntity serviceUserEntity = validateForUser(inputData);
        TaxiOrderEntity taxiOrderEntity = validateForTaxiOrder(inputData);
        serviceUserEntity=checkUserPresent(serviceUserEntity);
        taxiOrderEntity.setUserId(serviceUserEntity.getUserId());
        Long trackingNumber = taxiOrderDAO.addOrder(taxiOrderEntity);
        sendEmail(serviceUserEntity,trackingNumber);
        return trackingNumber;
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public List<TaxiOrderEntity> getOrdersPerPage(int pageNumber){
        return taxiOrderDAO.getOrdersPerPage(pageNumber);
    }
    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    int getTaxiOrdersPageCount(){
        return taxiOrderDAO.getTaxiOrdersPageCount();
    }
    /**
     * This method checks whether there is a user who made
     * the order in database, if he present in database the
     * unique number of record attache to him,
     * if not then create record in the database and attache to him
     * new and unique generation number of created record.
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @param serviceUserEntity - data about the user
     * @return ServiceUserEntity object that contain checked
     */
    private ServiceUserEntity checkUserPresent(ServiceUserEntity serviceUserEntity) {
        if (userDAO.getUserIdByEmail(serviceUserEntity.getEmail()) != null) {
            logger.info("User was found");
            //TODO working DAO methods
//            serviceUserEntity.setUserId(userDAO.getUserIdByEmail(serviceUserEntity.getEmail()));
        } else {
            logger.info("User was not found");
            //TODO working DAO methods
//            serviceUserEntity.setActivated(false);
//            serviceUserEntity.setUserId(userDAO.addUser(serviceUserEntity));
        }
        serviceUserEntity.setUserId(32);
        return serviceUserEntity;
    }

    /**
     * This method send confirmation letter with
     * tracking number to the user who made the order
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @param serviceUserEntity- the user who made the order
     * @param trackingNumber- tracking number of made order
     * @exception javax.mail.MessagingException
     */
    public void sendEmail(ServiceUserEntity serviceUserEntity, Long trackingNumber)throws MessagingException {
        mailBean.sendOrderConfirmInfo(serviceUserEntity);
        //TODO check mail send with tracking number and check sending letter
    }

    /**
     * This method checks incoming origin
     * address and insert it into AddressEntity object.
     *
     * @author Sharaban Sasha
     * @param inputData - input data from the client
     * @return AddressEntity object that contain checked origin address
     * @exception ua.com.tracksee.logic.exception.OrderException
     *
     */
    private ServiceUserEntity validateForUser(HashMap<String, String> inputData) throws OrderException {
        ServiceUserEntity serviceUserEntity = new ServiceUserEntity();

        if (validationBean.isValidEmail(inputData.get("email"))) {
            serviceUserEntity.setEmail(inputData.get("email"));
        } else {
            throw new OrderException("Invalid email.", "wrong-email");
        }
        if (validationBean.isValidPhoneNumber(inputData.get("phoneNumber"))) {
            serviceUserEntity.setPhone(inputData.get("phoneNumber"));
        } else {
            throw new OrderException("Invalid phone number.", "wrong-phone");
        }

        return serviceUserEntity;
    }
    /**
     * This method checks incoming origin
     * address and insert it into AddressEntity object.
     *
     * @author Sharaban Sasha
     * @param inputData - input data from the client
     * @return AddressEntity object that contain checked origin address
     * @exception ua.com.tracksee.logic.exception.OrderException
     */
    private AddressEntity validateForOriginAddress(HashMap<String, String> inputData) throws OrderException {
        AddressEntity addressEntityOrigin = new AddressEntity();
        addressEntityOrigin.setStringRepresentation(inputData.get("addressOrigin"));
        return addressEntityOrigin;
    }
    /**
     * This method checks incoming destination
     * address and insert it into AddressEntity object.
     *
     * @author Sharaban Sasha
     * @param inputData - input data from the client
     * @return AddressEntity object that contain checked destination address
     * @exception ua.com.tracksee.logic.exception.OrderException
     */
    private AddressEntity validateForDestinationAddress(HashMap<String, String> inputData) throws OrderException {
        AddressEntity addressEntityDestination = new AddressEntity();
        System.out.println(inputData.get("addressDestination"));
        System.out.println("addressDestination before");
        addressEntityDestination.setStringRepresentation(inputData.get("addressDestination"));
        System.out.println("add");
        return addressEntityDestination;
    }

    /**
     * This method checks incoming values and
     * insert it into TaxiOrderEntity object.
     *
     * @author Sharaban Sasha
     * @param inputData - input data from the client
     * @return TaxiOrderEntity object that contain checked values
     * @exception ua.com.tracksee.logic.exception.OrderException
     */
    private TaxiOrderEntity validateForTaxiOrder(HashMap<String, String> inputData) throws OrderException {
        TaxiOrderEntity taxiOrderEntity = new TaxiOrderEntity();
        CarCategory carCategory;
        WayOfPayment wayOfPayment;
        Sex driverSex;
        Service service;
        MusicStyle musicStyle;
        OrderStatus orderStatus = OrderStatus.QUEUED;

        if(!inputData.get("arriveDate").equals("")){
            Timestamp timestamp=convertToTimestamp(inputData.get("arriveDate"));
            taxiOrderEntity.setArriveDate(timestamp);
        }
        if(!inputData.get("endDate").equals("")){
            Timestamp timestamp=convertToTimestamp(inputData.get("endDate"));
            taxiOrderEntity.setEndDate(timestamp);
        }
        if(inputData.get("orderStatus").equals("QUEUED")){
        taxiOrderEntity.setStatus(orderStatus);
        }

        carCategory = enumValidationBean.setEnumCarCategory(inputData.get("carCategory"));
        if (carCategory != null) {
            taxiOrderEntity.setCarCategory(carCategory);
        } else {
            throw new OrderException("Invalid carCategory enum value", "invalid-carCategory");
        }
        wayOfPayment = enumValidationBean.setEnumWayOfPayment(inputData.get("wayOfPayment"));
        if (wayOfPayment != null) {
            taxiOrderEntity.setWayOfPayment(wayOfPayment);
        } else {
            throw new OrderException("Invalid wayOfPayment enum value", "invalid-wayOfPayment");
        }
        driverSex = enumValidationBean.setEnumDriverSex(inputData.get("driverSex"));
        if (driverSex != null) {
            taxiOrderEntity.setDriverSex(driverSex);
        } else {
            throw new OrderException("Invalid driverSex enum value", "invalid-driverSex");
        }
        service = enumValidationBean.setEnumService(inputData.get("service"));
        if (service != null) {
            taxiOrderEntity.setService(service);
        } else {
            throw new OrderException("Invalid service enum value", "invalid-service");
        }
        musicStyle = enumValidationBean.setEnumMusicStyle(inputData.get("musicStyle"));
        if (musicStyle != null) {
            taxiOrderEntity.setMusicStyle(musicStyle);
        } else {
            throw new OrderException("Invalid musicStyle enum value", "invalid-musicStyle");
        }
        taxiOrderEntity.setAnimalTransportation(Boolean.parseBoolean(inputData.get("animalTransportation")));
        taxiOrderEntity.setFreeWifi(Boolean.parseBoolean(inputData.get("freeWifi")));
        taxiOrderEntity.setNonSmokingDriver(Boolean.parseBoolean(inputData.get("smokingDriver")));
        taxiOrderEntity.setAirConditioner(Boolean.parseBoolean(inputData.get("airConditioner")));
        try {
            int distance=Integer.parseInt(inputData.get("distance"));
            taxiOrderEntity.setPrice(new BigDecimal(priceCalculatorBean.simpleCalculatePrice(distance)));
        } catch (Exception e) {
            throw new OrderException("Invalid price", "wrong-price");
        }
        if(!inputData.get("description").equals("")){
        taxiOrderEntity.setDescription(inputData.get("description"));
        }else{
            taxiOrderEntity.setDescription("");
        }
        return taxiOrderEntity;
    }
    /**
     * This method convert date from
     * string to Timestamp
     *
     * @author Sharaban Sasha
     * @param date - date in string format
     * @return date converted from string to Timestemp
     * @exception ua.com.tracksee.logic.exception.OrderException
     */
    private Timestamp convertToTimestamp(String date) throws OrderException {
        Timestamp timestamp=null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        }catch(ParseException e){
            throw new OrderException("Invalid date, cannot be parsed","invalid-date");
        }
        return timestamp;
    }

}
