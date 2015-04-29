package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.HashMap;

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

    /**
     * Creates taxi order for authorised user.
     *
     * @param userId id of authorised customer user
     * @param orderDTO basic information about the order
     */
    @RolesAllowed("customer")
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

        order.setDescription(orderDTO.getDescription());
        order.setAnimalTransportation(orderDTO.getAnimalTransportation());
        order.setFreeWifi(orderDTO.getFreeWiFi());
        order.setNonSmokingDriver(orderDTO.getSmokingDriver());
        order.setAirConditioner(orderDTO.getAirConditioner());
        return taxiOrderDAO.addOrder(order);
    }

    /**
     * Creates taxi order for non-authorised user.
     *
     * @param inputData hash map, that specifies taxi order
     * @throws OrderException
     */
    public void createNonAuthorisedOrder(HashMap<String, String> inputData) throws OrderException {
        ServiceUserEntity serviceUserEntity = validateForUser(inputData);
        TaxiOrderEntity taxiOrderEntity = validateForTaxiOrder(inputData);

        taxiOrderDAO.addOrder(taxiOrderEntity);

        logger.info("Check user :" + serviceUserEntity.getEmail());
        if (!userDAO.checkUserByEmail(serviceUserEntity.getEmail())) {
            logger.info("Create new user: email-" + serviceUserEntity.getEmail() + " phone-" + serviceUserEntity.getPhone());
            serviceUserEntity.setActivated(false);
            userDAO.addUser(serviceUserEntity);
        } else {
            logger.info("User was found");
        }

        serviceUserEntity.setUserId(userDAO.getUserIdByEmail(serviceUserEntity.getEmail()));
        //TODO check mail send
        try {
            mailBean.sendOrderConfirmInfo(serviceUserEntity);
        } catch (MessagingException e) {
            throw new OrderException("Failed to send tracking number email.", "");
        }
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

    /**
     * This method send email with
     * tracking number
     *
     * @param user- user that make order
     * @param trackingNumber- user that make order
     */
    public void sendEmail(ServiceUserEntity user,int trackingNumber) {
    // TODO send email
    }

}
