package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.*;
import ua.com.tracksee.enumartion.*;
import ua.com.tracksee.json.TaxiOrderDTO;
import ua.com.tracksee.logic.exception.OrderException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    private
    @EJB
    TaxiOrderDAO taxiOrderDAO;
    private
    @EJB
    UserDAO userDAO;
    private
    @EJB
    AddressDAO addressDAO;
    private
    @EJB
    EmailBean mailBean;
    private
    @EJB
    ValidationBean validationBean;
    private
    @EJB
    PriceCalculatorBean priceCalculatorBean;
    private
    @EJB
    EnumValidationBean enumValidationBean;

    /**
     *
     * @param startDate start date to get the most profitable service
     * @param endDate end date to get the most profitable service
     * @return list object with statistic data
     */
    public List<ServiceProfitable> getProfitByService(String startDate, String endDate){return taxiOrderDAO.getProfitByService(startDate, endDate);}

    /**
     *
     * @param userId user id for whom getting statistic about popular options
     * @return list object with statistic data
     */
    public List<MostPopularOption> getMostPopularOptionsForUser(Integer userId){return taxiOrderDAO.getMostPopularOptionsForUser(userId);}





    /**
     * Creates taxi order for authorised user.
     *
     * @param userId   id of authorised customer user
     * @param orderDTO basic information about the order
     */
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
     * @author Ruslan Gunavardana
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @param inputData- input data about user and his order
     * @return Integer - tracking number of user order
     * @throws ua.com.tracksee.logic.exception.OrderException
     */
    public Long makeOrder(HashMap<String, String> inputData) throws OrderException {
        String email = inputData.get("email");
        String phone = inputData.get("phoneNumber");
        validateForUser(email, phone);
        ServiceUserEntity user = new ServiceUserEntity();
        user.setEmail(email);
        user.setPhone(phone);

        TaxiOrderEntity taxiOrderEntity = validateForTaxiOrder(inputData);
        user = checkUserPresent(user);
        taxiOrderEntity.setUserId(user.getUserId());
        taxiOrderEntity.setTrackingNumber(taxiOrderDAO.addOrder(taxiOrderEntity));
        if (taxiOrderEntity.getArriveDate() != null) {
            taxiOrderDAO.addArriveDate(taxiOrderEntity.getArriveDate(), taxiOrderEntity.getTrackingNumber());
        }
        if (taxiOrderEntity.getEndDate() != null) {
            taxiOrderDAO.addEndDate(taxiOrderEntity.getEndDate(), taxiOrderEntity.getTrackingNumber());
        }
        sendEmail(user, taxiOrderEntity.getTrackingNumber());
        return taxiOrderEntity.getTrackingNumber();
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public List<TaxiOrderEntity> getActiveOrdersPerPage(int partNumber) {
        return taxiOrderDAO.getActiveOrdersPerPage(partNumber);
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public List<TaxiOrderEntity> getOldOrdersPerPage(int partNumber) {
        return taxiOrderDAO.getOldOrdersPerPage(partNumber);
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public int getActiveTaxiOrderPagesCount() {
        return taxiOrderDAO.getActiveTaxiOrderPagesCount();
    }

    /**
     * @author Sharaban Sasha
     * @author Igor Gula
     */
    public void updateOrder(HashMap<String, String> inputData) throws OrderException {
        TaxiOrderEntity taxiOrderEntity = validateForUpdateTaxiOrder(inputData);
        long trackingNumber = Long.parseLong(inputData.get("trackingNumber"));
        if (trackingNumber >= 0) {
            taxiOrderEntity.setTrackingNumber(trackingNumber);
            taxiOrderDAO.updateOrder(taxiOrderEntity);
            if (taxiOrderEntity.getArriveDate() != null) {
                taxiOrderDAO.addArriveDate(taxiOrderEntity.getArriveDate(), taxiOrderEntity.getTrackingNumber());
            }
            if (taxiOrderEntity.getEndDate() != null) {
                taxiOrderDAO.addEndDate(taxiOrderEntity.getEndDate(), taxiOrderEntity.getTrackingNumber());
            }
        } else {
            throw new OrderException("Invalid tracking number", "invalid-track-numb");
        }
    }

    public void updateComment(Integer trackNumber, String comment) {
        taxiOrderDAO.updateComment(trackNumber, comment);
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public int getOldTaxiOrderPagesCount() {
        return taxiOrderDAO.getOldTaxiOrderPagesCount();
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
            serviceUserEntity.setUserId(userDAO.getUserIdByEmail(serviceUserEntity.getEmail()));
        } else {
            logger.info("User was not found");
            serviceUserEntity.setActivated(false);
            serviceUserEntity.setPassword("");
            serviceUserEntity.setUserId(userDAO.addUser(serviceUserEntity));
        }
        return serviceUserEntity;
    }

    /**
     * This method send confirmation letter with
     * tracking number to the user who made the order
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @param serviceUserEntity- the user who made the order
     * @param trackingNumber-    tracking number of made order
     * @throws javax.mail.MessagingException
     */
    public void sendEmail(ServiceUserEntity serviceUserEntity, Long trackingNumber) {
        mailBean.sendOrderConfirmation(serviceUserEntity, trackingNumber);
    }

    /**
     * This method checks incoming origin
     * address and insert it into AddressEntity object.
     *
     * @author Sharaban Sasha
     * @author Ruslan Gunavardana
     * @param email - client's email
     * @param phone - client's phone number
     * @throws ua.com.tracksee.logic.exception.OrderException *
     */
    private void validateForUser(String email, String phone) throws OrderException {
        if (!validationBean.isValidEmail(email)) {
            throw new OrderException("Invalid email.", "wrong-email");
        }

        if (!validationBean.isValidPhoneNumber(phone)) {
            throw new OrderException("Invalid phone number.", "wrong-phone");
        }
    }

    /**
     * This method checks incoming origin
     * address and insert it into AddressEntity object.
     *
     * @author Sharaban Sasha
     * @param inputData - input data from the client
     * @return AddressEntity object that contain checked origin address
     * @throws ua.com.tracksee.logic.exception.OrderException
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
     * @throws ua.com.tracksee.logic.exception.OrderException
     */
    private AddressEntity validateForDestinationAddress(HashMap<String, String> inputData) throws OrderException {
        AddressEntity addressEntityDestination = new AddressEntity();
        addressEntityDestination.setStringRepresentation(inputData.get("addressDestination"));
        return addressEntityDestination;
    }

    /**
     * This method checks incoming values and
     * insert it into TaxiOrderEntity object.
     *
     * @author Sharaban Sasha
     * @param inputData - input data from the client
     * @return TaxiOrderEntity object that contain checked values
     * @throws ua.com.tracksee.logic.exception.OrderException
     */
    private TaxiOrderEntity validateForTaxiOrder(HashMap<String, String> inputData) throws OrderException {
        TaxiOrderEntity taxiOrderEntity = new TaxiOrderEntity();
        CarCategory carCategory;
        WayOfPayment wayOfPayment;
        Sex driverSex;
        Service service;
        MusicStyle musicStyle;
        OrderStatus orderStatus = OrderStatus.QUEUED;

        if (!inputData.get("arriveDate").equals("")) {
            Timestamp timestamp = convertToTimestamp(inputData.get("arriveDate"));
            taxiOrderEntity.setArriveDate(timestamp);
        } else {
            taxiOrderEntity.setArriveDate(null);
        }
        if (!inputData.get("endDate").equals("")) {
            Timestamp timestamp = convertToTimestamp(inputData.get("endDate"));
            taxiOrderEntity.setEndDate(timestamp);
        } else {
            taxiOrderEntity.setArriveDate(null);
        }

        if (inputData.get("orderStatus").equals("QUEUED")) {
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
        taxiOrderEntity.setAnimalTransportation(convertCheckBoxToBoolean(inputData.get("animalTransportation")));
        taxiOrderEntity.setFreeWifi(convertCheckBoxToBoolean(inputData.get("freeWifi")));
        taxiOrderEntity.setNonSmokingDriver(convertCheckBoxToBoolean(inputData.get("smokingDriver")));
        taxiOrderEntity.setAirConditioner(convertCheckBoxToBoolean(inputData.get("airConditioner")));
        try {
            int distance = Integer.parseInt(inputData.get("distance"));
            taxiOrderEntity.setPrice(new BigDecimal(priceCalculatorBean.simpleCalculatePrice(distance)));
        } catch (Exception e) {
            throw new OrderException("Invalid price", "wrong-price");
        }
        if (!inputData.get("description").equals("")) {
            taxiOrderEntity.setDescription(inputData.get("description"));
        } else {
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
     * @return date converted from string to Timestamp
     * @throws ua.com.tracksee.logic.exception.OrderException
     */
    private Timestamp convertToTimestamp(String date) throws OrderException {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            throw new OrderException("Invalid date, cannot be parsed", "invalid-date");
        }
        return timestamp;
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.TaxiOrderDAO
     */
    public TaxiOrderEntity getOrderInfo(long trackingNumber) {
        return taxiOrderDAO.getOrder(trackingNumber);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.UserDAO
     */
    public ServiceUserEntity getUserInfo(int id) {

        return userDAO.getUserById(id);
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.AddressDAO
     */
    public AddressEntity getAddressInfo(int userId) {
        return addressDAO.getAddressByUserId(userId);
    }

    /**
     * This method convert string
     * representation of checkbox state
     * to boolean
     *
     * @author Sharaban Sasha
     * @param checkBoxState - string representation of checkbox state
     * @return checkbox boolean state
     */
    private boolean convertCheckBoxToBoolean(String checkBoxState) {
        boolean booleanCheckBoxState = false;
        if (checkBoxState==null) {
            booleanCheckBoxState = false;
        }else
        if(checkBoxState.equals("on")){
            booleanCheckBoxState = true;
        }
        return booleanCheckBoxState;
    }

    /**
     * This method checks incoming values and
     * insert it into TaxiOrderEntity object
     * it is used for updating taxi order
     *
     * @author Sharaban Sasha
     * @param inputData - input data from the client
     * @return TaxiOrderEntity object that contain checked values
     * @throws ua.com.tracksee.logic.exception.OrderException
     */
    private TaxiOrderEntity validateForUpdateTaxiOrder(HashMap<String, String> inputData) throws OrderException {
        TaxiOrderEntity taxiOrderEntity = new TaxiOrderEntity();
        CarCategory carCategory;
        WayOfPayment wayOfPayment;
        Sex driverSex;
        Service service;
        MusicStyle musicStyle;

        if (inputData.get("arriveDate")!=null) {
            Timestamp timestamp = convertToTimestamp(inputData.get("arriveDate"));
            taxiOrderEntity.setArriveDate(timestamp);
        } else {
            taxiOrderEntity.setArriveDate(null);
        }
        if (inputData.get("endDate")!=null) {
            Timestamp timestamp = convertToTimestamp(inputData.get("endDate"));
            taxiOrderEntity.setEndDate(timestamp);
        } else {
            taxiOrderEntity.setArriveDate(null);
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
        taxiOrderEntity.setAnimalTransportation(convertCheckBoxToBoolean(inputData.get("animalTransportation")));
        taxiOrderEntity.setFreeWifi(convertCheckBoxToBoolean(inputData.get("freeWifi")));
        taxiOrderEntity.setNonSmokingDriver(convertCheckBoxToBoolean(inputData.get("smokingDriver")));
        taxiOrderEntity.setAirConditioner(convertCheckBoxToBoolean(inputData.get("airConditioner")));

        if (!inputData.get("description").equals("")) {
            taxiOrderEntity.setDescription(inputData.get("description"));
        } else {
            taxiOrderEntity.setDescription("");
        }
        return taxiOrderEntity;
    }
}
