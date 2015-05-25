package ua.com.tracksee.logic;

import com.vividsolutions.jts.geom.LineString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dto.RouteDTO;
import ua.com.tracksee.entities.*;
import ua.com.tracksee.enumartion.Service;
import ua.com.tracksee.exception.OrderException;
import ua.com.tracksee.dto.TaxiOrderDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.FALSE;

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

    public List<ServiceProfitable> getProfitByService(String startDate, String endDate) {
        return taxiOrderDAO.getProfitByService(startDate, endDate);
    }

    public List<MostPopularOption> getMostPopularOptionsForUser(Integer userId) {
        return taxiOrderDAO.getMostPopularOptionsForUser(userId);
    }

    /**
     * Checks input data,insert order in database.
     * Also it insert user in database if he doesn't have  record
     * about him in database. And it send conformation letter with
     * tracking number, also tracking number returns to be shown to
     * user.
     *
     * @author Ruslan Gunavardana
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @param inputData - input data about user and his order
     * @param orderDTO
     * @return Integer - tracking number of user order
     * @throws ua.com.tracksee.exception.OrderException
     */
    public Long addOrder(HashMap<String, String> inputData, TaxiOrderDTO orderDTO) throws OrderException {
        String email = inputData.get("email");
        String phone = inputData.get("phoneNumber");
        validateUserCredentials(email, phone);

        TaxiOrderEntity order = validateAndAssignDataToTaxiOrderEntity(inputData);
        UserEntity user = getUser(email, phone);
        order.setUserId(user.getUserId());

        // items initialisation
        for (RouteDTO route : orderDTO.getRoutes()) {
            LineString path = route.getRouteLineString();
            BigDecimal orderedQuantity = new BigDecimal(route.getDistance());
            order.getItemList().add(new TaxiOrderItemEntity(path, orderedQuantity, order));
        }


        //calculating price
        order.setPrice(priceCalculatorBean.calculatePrice(order));

        // adding to database
        order.setTrackingNumber(taxiOrderDAO.addOrder(order));

        if (order.getService() == Service.SOBER_DRIVER) {
            taxiOrderDAO.addEnumCarCategory(enumValidationBean.setEnumCarCategory(inputData.get("carCategory")),
                    order.getTrackingNumber());
        }

        if (order.getArriveDate() != null) {
            taxiOrderDAO.addArriveDate(order.getArriveDate(), order.getTrackingNumber());
        }
        if (order.getAmountOfHours() != null&&order.getAmountOfMinutes() != null) {
            taxiOrderDAO.addLongTermTaxiParams(order.getAmountOfHours(),
                    order.getAmountOfMinutes(),order.getTrackingNumber());
        }
        if (order.getAmountOfCars() != null) {
            taxiOrderDAO.addCelebrationTaxiParam(order.getAmountOfCars(), order.getTrackingNumber());
        }
        sendEmail(user, order.getTrackingNumber());
        return order.getTrackingNumber();
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public List<TaxiOrderEntity> getActiveOrdersPerPage(int userID, int partNumber) {
        return taxiOrderDAO.getCustomerActiveOrdersPerPage(userID, partNumber);
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public List<TaxiOrderEntity> getOldOrdersPerPage(int userID, int partNumber) {
        return taxiOrderDAO.getCustomerOldOrdersPerPage(userID, partNumber);
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public int getActiveTaxiOrderPagesCount(int userID) {
        return taxiOrderDAO.getActiveTaxiOrderPagesCount(userID);
    }

    /**
     * Updates order.
     *
     * @author Sharaban Sasha
     * @author Igor Gula
     * @param inputData information about changed order
     */
    public void updateOrder(HashMap<String, String> inputData) throws OrderException {
        TaxiOrderEntity order = validateAndAssignDataToTaxiOrderEntity(inputData);
        long trackingNumber = Long.parseLong(inputData.get("trackingNumber"));
        if (trackingNumber >= 0) {
            order.setTrackingNumber(trackingNumber);
            taxiOrderDAO.updateOrder(order);

            if (order.getService() == Service.SOBER_DRIVER) {
                taxiOrderDAO.addEnumCarCategory(enumValidationBean.setEnumCarCategory(inputData.get("carCategory")),
                        order.getTrackingNumber());
            }

            if (order.getArriveDate() != null) {
                taxiOrderDAO.addArriveDate(order.getArriveDate(), order.getTrackingNumber());
            }
            if (order.getAmountOfHours() != null&&order.getAmountOfMinutes() != null) {
                taxiOrderDAO.addLongTermTaxiParams(order.getAmountOfHours(),
                        order.getAmountOfMinutes(),order.getTrackingNumber());
            }
            if (order.getAmountOfCars() != null) {
                taxiOrderDAO.addCelebrationTaxiParam(order.getAmountOfCars(), order.getTrackingNumber());
            }
        } else {
            throw new OrderException("Invalid tracking number", "invalid-track-numb");
        }
    }


    public void updateComment(long trackNumber, String comment) {
        taxiOrderDAO.updateComment(trackNumber, comment);
    }

    /**
     * @author Vadym Akymov
     * @see TaxiOrderDAO
     */
    public int getOldTaxiOrderPagesCount(int userID) {
        return taxiOrderDAO.getOldTaxiOrderPagesCount(userID);
    }

    /**
     * Checks whether there is a user who made
     * the order in database, if he present in database the
     * unique number of record attache to him,
     * if not then creates record in the database and attache to him
     * new and unique number of created record.
     * Password and salt are set by default as empty
     * string for unregistered user.
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @return ServiceUserEntity object that contain checked
     */
    private UserEntity getUser(String email, String phone) {
        UserEntity user = userDAO.getUserByEmail(email);
        if (user != null) {
            logger.debug("User was found");
            user.setUserId(userDAO.getUserIdByEmail(user.getEmail()));
        } else {
            logger.debug("User was not found, record about him will be created");
            user = new UserEntity();
            user.setEmail(email);
            user.setPhone(phone);
            user.setActivated(FALSE);
            user.setPassword("");
            user.setSalt("");
            user.setUserId(userDAO.addUnregisteredUser(user));
        }
        return user;
    }

    /**
     * Sends confirmation letter with
     * tracking number to the user who made the order
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @param userEntity- the user who made the order
     * @param trackingNumber-    tracking number of made order
     * @throws javax.mail.MessagingException
     */
    public void sendEmail(UserEntity userEntity, Long trackingNumber) {
        mailBean.sendOrderConfirmation(userEntity, trackingNumber);
    }

    /**
     * Checks incoming origin
     * address and insert it into AddressEntity object.
     *
     * @author Sharaban Sasha
     * @author Ruslan Gunavardana
     * @param email - client's email
     * @param phone - client's phone number
     * @throws ua.com.tracksee.exception.OrderException *
     */
    private void validateUserCredentials(String email, String phone) throws OrderException {
        if (!validationBean.isValidEmail(email)) {
            throw new OrderException("Invalid email.", "wrong-email");
        }

        if (!validationBean.isValidPhoneNumber(phone)) {
            throw new OrderException("Invalid phone number.", "wrong-phone");
        }
    }

    /**
     * This method checks incoming values and
     * insert it into TaxiOrderEntity object.
     *
     * @author Sharaban Sasha
     * @param inputData - input data from the client
     * @return TaxiOrderEntity object that contain checked values
     * @throws ua.com.tracksee.exception.OrderException
     */
    private TaxiOrderEntity validateAndAssignDataToTaxiOrderEntity(HashMap<String, String> inputData) throws OrderException {
        TaxiOrderEntity taxiOrderEntity = new TaxiOrderEntity();

        taxiOrderEntity.setArriveDate(convertToTimestamp(inputData.get("arriveDate")));

        taxiOrderEntity.setAmountOfCars(convertToInt(inputData.get("amountOfCars")));
        taxiOrderEntity.setAmountOfHours(convertToInt(inputData.get("amountOfHours")));
        taxiOrderEntity.setAmountOfMinutes(convertToInt(inputData.get("amountOfMinutes")));
        taxiOrderEntity.setStatus(enumValidationBean.setEnumOrderStatus(inputData.get("orderStatus")));
        taxiOrderEntity.setWayOfPayment(enumValidationBean.setEnumWayOfPayment(inputData.get("wayOfPayment")));
        taxiOrderEntity.setDriverSex(enumValidationBean.setEnumDriverSex(inputData.get("driverSex")));
        taxiOrderEntity.setService( enumValidationBean.setEnumService(inputData.get("service")));
        taxiOrderEntity.setMusicStyle(enumValidationBean.setEnumMusicStyle(inputData.get("musicStyle")));

        taxiOrderEntity.setAnimalTransportation(convertCheckBoxToBoolean(inputData.get("animalTransportation")));
        taxiOrderEntity.setFreeWifi(convertCheckBoxToBoolean(inputData.get("freeWifi")));
        taxiOrderEntity.setNonSmokingDriver(convertCheckBoxToBoolean(inputData.get("nonSmokingDriver")));
        taxiOrderEntity.setAirConditioner(convertCheckBoxToBoolean(inputData.get("airConditioner")));

            taxiOrderEntity.setDescription(inputData.get("description"));

        return taxiOrderEntity;
    }

    /**
     * Converts date from
     * string to Timestamp
     *
     * @author Sharaban Sasha
     * @param date - date in string format
     * @return date converted from string to Timestamp
     * @throws ua.com.tracksee.exception.OrderException
     */
    private Timestamp convertToTimestamp(String date) throws OrderException {
        Timestamp timestamp;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            logger.info("Invalid or missing date, cannot be parsed");
            timestamp = null;
        }
        return timestamp;
    }
    /**
     * Converts string value
     * to integer and make code
     * simplest
     *
     * @author Sharaban Sasha
     * @param number - number in string format
     * @return number converted from string to int
     * @throws ua.com.tracksee.exception.OrderException
     */
    private Integer convertToInt(String number)  {
        Integer intNumber;
        try {
          intNumber=Integer.parseInt(number);
        } catch (NumberFormatException e) {
            logger.info("TaxiOrderBean.convertToInt: input value:"+number+" ,exception "+ e);
            intNumber=null;
        }
        return intNumber;
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.TaxiOrderDAO
     */
    public TaxiOrderEntity getOrderInfo(long trackingNumber) {
        return taxiOrderDAO.getOrder(trackingNumber);
    }



    /**
     * Converts string
     * representation of checkbox state
     * to boolean
     *
     * @author Sharaban Sasha
     * @param checkBoxState - string representation of checkbox state
     * @return checkbox boolean state
     */
    private boolean convertCheckBoxToBoolean(String checkBoxState) {
        boolean booleanCheckBoxState = false;
        if (checkBoxState == null) {
            booleanCheckBoxState = false;
        } else if (checkBoxState.equals("on")) {
            booleanCheckBoxState = true;
        }
        return booleanCheckBoxState;
    }


    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.TaxiOrderDAO
     */
    public boolean checkOrderPresentNonActiveUser(long trackingNumber) {
        return taxiOrderDAO.checkOrderPresentNonActiveUser(trackingNumber);
    }
    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.TaxiOrderDAO
     */
    public boolean checkOrderPresentForActiveUser(long trackingNumber,int userId) {
        return taxiOrderDAO.checkOrderPresentForActiveUser(trackingNumber, userId);
    }
    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.TaxiOrderDAO
     */
    public boolean checkOrderPresentActiveUser(long trackingNumber) {
        return taxiOrderDAO.checkOrderPresentActiveUser(trackingNumber);
    }

}
