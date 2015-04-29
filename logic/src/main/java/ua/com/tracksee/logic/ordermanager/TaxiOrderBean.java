package ua.com.tracksee.logic.ordermanager;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.*;
import ua.com.tracksee.logic.EmailBean;
import ua.com.tracksee.logic.ValidationBean;
import ua.com.tracksee.logic.exception.OrderException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static javax.ejb.LockType.WRITE;


/**
 * Session Bean used for any order proceed
 *
 * @author Sasha Avlasov
 * @author Sharaban Sasha
 */
@Singleton
@Local
public class TaxiOrderBean {
    @EJB
    private TaxiOrderDAO taxiOrderDAO;
    @EJB
    private UserDAO userDAO;
    @EJB
    private EmailBean mailBean;
    @EJB
    private ValidationBean validationBean;
    private Logger logger;
    
    /**
     * Default constructor.
     */
    public TaxiOrderBean() {
        logger = LogManager.getLogger();
    }
    /**
     * This method check input data,insert order in database.
     * Also it insert user in database if he doesn't have  record
     * about him in database. And it send conformation letter with
     * tracking number, also tracking number returns to be shown to
     * user.
     *
     * @param inputData- input data about user and his order
     * @exception javax.mail.MessagingException
     * @exception java.sql.SQLException
     * @exception ua.com.tracksee.logic.exception.OrderException
     * @return Integer - tracking number of user order
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     */
    @Lock(WRITE)
    public Integer makeOrder(HashMap<String, String> inputData) throws SQLException, OrderException,MessagingException {

        ServiceUserEntity serviceUserEntity = validateForUser(inputData);
        TaxiOrderEntity taxiOrderEntity = validateForTaxiOrder(inputData);
        serviceUserEntity=checkUserPresent(serviceUserEntity);
        taxiOrderEntity.setUserId(serviceUserEntity.getUserId());
        Integer trackingNumber=taxiOrderDAO.addOrder(taxiOrderEntity);
        sendEmail(serviceUserEntity,trackingNumber);
     return trackingNumber;
    }

    /*
     *@author Vadym Akymov
     */
    public List<TaxiOrderEntity> getOrdersPerPage(int pageNumber){
        return taxiOrderDAO.getOrdersPerPage(pageNumber);
    }
    /**
     * This method checks whether there is a user who made
     * the order in database, if he present in database the
     * unique number of record attache to him,
     * if not then create record in the database and attache to him
     * new and unique generation number of created record.
     *
     * @param serviceUserEntity - data about the user
     * @return ServiceUserEntity object that contain checked

     * @author Sharaban Sasha
     * @author Avlasov Sasha
     */
    private ServiceUserEntity checkUserPresent(ServiceUserEntity serviceUserEntity) {
        if (userDAO.checkUserByEmail(serviceUserEntity.getEmail())) {
            logger.info("User was found");
            //TODO working DAO methods
//            serviceUserEntity.setUserId(userDAO.getUserIdByEmail(serviceUserEntity.getEmail()));
        } else {
            logger.info("User was not found");
            //TODO working DAO methods
//            serviceUserEntity.setActivated(false);
//            serviceUserEntity.setUserId(userDAO.addUser(serviceUserEntity));
        }
        serviceUserEntity.setUserId(1);
        return serviceUserEntity;
    }

    /**
     * This method send confirmation letter with
     * tracking number to the user who made the order
     *
     * @param serviceUserEntity- the user who made the order
     * @param trackingNumber- tracking number of made order
     * @exception javax.mail.MessagingException
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     */
    public void sendEmail(ServiceUserEntity serviceUserEntity,int trackingNumber)throws MessagingException {
        mailBean.sendOrderConfirmInfo(serviceUserEntity);
        //TODO check mail send with tracking number and check sending letter
    }

    /**
     * This method checks incoming origin
     * address and insert it into AddressEntity object.
     *
     * @param inputData - input data from the client
     * @return AddressEntity object that contain checked origin address
     * @exception ua.com.tracksee.logic.exception.OrderException
     * @author Sharaban Sasha
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
     * @param inputData - input data from the client
     * @return AddressEntity object that contain checked origin address
     * @exception ua.com.tracksee.logic.exception.OrderException
     * @author Sharaban Sasha
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
     * @param inputData - input data from the client
     * @return AddressEntity object that contain checked destination address
     * @exception ua.com.tracksee.logic.exception.OrderException
     * @author Sharaban Sasha
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
     * @param inputData - input data from the client
     * @return TaxiOrderEntity object that contain checked values
     * @exception ua.com.tracksee.logic.exception.OrderException
     * @author Sharaban Sasha
     */
    private TaxiOrderEntity validateForTaxiOrder(HashMap<String, String> inputData) throws OrderException {
        TaxiOrderEntity taxiOrderEntity = new TaxiOrderEntity();
        CarCategory carCategory;
        WayOfPayment wayOfPayment;
        Sex driverSex;
        Service service;
        MusicStyle musicStyle;
        OrderStatus orderStatus = OrderStatus.QUEUED;

        if(inputData.get("orderStatus").equals("QUEUED")){
        taxiOrderEntity.setStatus(orderStatus);
        }

        carCategory = setEnumCarCategory(inputData.get("carCategory"));
        if (carCategory != null) {
            taxiOrderEntity.setCarCategory(carCategory);
        } else {
            throw new OrderException("Invalid carCategory enum value", "invalid-carCategory");
        }
        wayOfPayment = setEnumWayOfPayment(inputData.get("wayOfPayment"));
        if (wayOfPayment != null) {
            taxiOrderEntity.setWayOfPayment(wayOfPayment);
        } else {
            throw new OrderException("Invalid wayOfPayment enum value", "invalid-wayOfPayment");
        }
        driverSex = setEnumDriverSex(inputData.get("driverSex"));
        if (driverSex != null) {
            taxiOrderEntity.setDriverSex(driverSex);
        } else {
            throw new OrderException("Invalid driverSex enum value", "invalid-driverSex");
        }
        service = setEnumService(inputData.get("service"));
        if (service != null) {
            taxiOrderEntity.setService(service);
        } else {
            throw new OrderException("Invalid service enum value", "invalid-service");
        }
        musicStyle = setEnumMusicStyle(inputData.get("musicStyle"));
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
            double price = Double.parseDouble(inputData.get("price"));
            taxiOrderEntity.setPrice(new BigDecimal(price));
        } catch (Exception e) {
            throw new OrderException("Invalid price .", "wrong-");
        }
        if(!inputData.get("description").equals("")){
        taxiOrderEntity.setDescription(inputData.get("description"));
        }else{
            taxiOrderEntity.setDescription("");
        }
        return taxiOrderEntity;


    }
    /**
     * This method checks there is the such
     * enum  and return it if it exists.
     *
     * @param carCategory - string representation of car category
     * @return car category enum
     * @author Sharaban Sasha
     */

    private CarCategory setEnumCarCategory(String carCategory) {
        CarCategory enumCarCategory;
        switch (carCategory) {
            case "business":
                enumCarCategory = CarCategory.BUSINESS_CLASS;
                break;
            case "economyClass":
                enumCarCategory = CarCategory.ECONOMY_CLASS;
                break;
            case "van":
                enumCarCategory = CarCategory.VAN;
                break;
            case "userCar":
                enumCarCategory = CarCategory.USER_CAR;
                break;
            default:
                enumCarCategory = null;
        }
        return enumCarCategory;
    }
    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @param wayOfPayment - string representation of way of payment
     * @return way of payment enum
     * @author Sharaban Sasha
     */
    private WayOfPayment setEnumWayOfPayment(String wayOfPayment) {
        WayOfPayment enumWayOfPayment;
        switch (wayOfPayment) {
            case "cash":
                enumWayOfPayment = WayOfPayment.CASH;
                break;
            case "visaCard":
                enumWayOfPayment = WayOfPayment.VISA_CARD;
                break;
            default:
                enumWayOfPayment = null;
        }
        return enumWayOfPayment;
    }
    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @param driverSex - string representation of driver sex
     * @return driver sex enum
     * @author Sharaban Sasha
     */
    private Sex setEnumDriverSex(String driverSex) {
        Sex enumDriverSex;
        switch (driverSex) {
            case "male":
                enumDriverSex = Sex.M;
                break;
            case "female":
                enumDriverSex = Sex.F;
                break;
            default:
                enumDriverSex = null;
        }
        return enumDriverSex;
    }
    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @param service - string representation of service
     * @return service enum
     * @author Sharaban Sasha
     */

    private Service setEnumService(String service) {
        Service enumService;
        switch (service) {
            case "default":
                enumService = Service.SIMPLE_TAXI;
                break;
            case "soberDriver":
                enumService = Service.SOBER_DRIVER;
                break;
            case "guestDelivery":
                enumService = Service.GUEST_DELIVERY;
                break;
            case "cargoTaxi":
                enumService = Service.CARGO_TAXI;
                break;
            case "meetMyGuest":
                enumService = Service.MEET_MY_GUEST;
                break;
            case "celebrationTaxi":
                enumService = Service.CELEBRATION_TAXI;
                break;
            case "foodStuffDelivery":
                enumService = Service.FOODSTUFF_DELIVERY;
                break;
            default:
                enumService = null;

        }
        return enumService;
    }

    /**
     * This method checks there is the such
     * enum and return it if it exists.
     *
     * @param musicStyle - string representation of music style
     * @return music style enum
     * @author Sharaban Sasha
     */

    private MusicStyle setEnumMusicStyle(String musicStyle) {
        MusicStyle enumMusicStyle;
        switch (musicStyle) {
            case "default":
                enumMusicStyle = MusicStyle.DEFAULT;
                break;
            case "blues":
                enumMusicStyle = MusicStyle.BLUES;
                break;
            case "classicalMusic":
                enumMusicStyle = MusicStyle.CLASSICAL_MUSIC;
                break;
            case "rock":
                enumMusicStyle = MusicStyle.ROCK;
                break;
            case "jazz":
                enumMusicStyle = MusicStyle.JAZZ;
                break;
            case "danceMusic":
                enumMusicStyle = MusicStyle.DANCE_MUSIC;
                break;
            case "electronicMusic":
                enumMusicStyle = MusicStyle.ELECTRONIC_MUSIC;
                break;
            case "hipHop":
                enumMusicStyle = MusicStyle.HIP_HOP;
                break;
            default:
                enumMusicStyle = null;
        }
        return enumMusicStyle;
    }

    private boolean checkBlackList(long phone) {

        //TODO Check phone in black list

        return false;
    }

    /**
     * This method calculate price,
     * multiplying the price per 1 km on distance
     *
     * @param origin      - address from
     * @param destination - address to
     * @return price of order
     */

    @Lock(WRITE)
    public long calculatePrice(String origin, String destination) throws SQLException {
        long distance = 0;
        long price = 0;
        //TODO calculate price
        return 1;
    }



}
