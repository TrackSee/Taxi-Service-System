package ua.com.tracksee.servlets.orders;

/**
 * @author Sharaban Sasha
 * @author Ruslan Gunavardana
 */
public interface OrderAttributes {
    String ORDER_ALIAS ="order";
    String TRACKING_NUMBER_ALIAS ="trackingNumber";
    String AMOUNT_OF_CARS_ALIAS ="amountOfCars";
    String AMOUNT_OF_HOURS_ALIAS ="amountOfHours";
    String AMOUNT_OF_MINUTES_ALIAS ="amountOfMinutes";
    String PHONE_NUMBER_ALIAS ="phoneNumber";
    String EMAIL_ALIAS ="email";
    String ARRIVE_DATE_ALIAS ="arriveDate";
    String END_DATE_ALIAS ="endDate";
    String ADDRESS_DESTINATION_ALIAS ="addressDestination";
    String ADDRESS_ORIGIN_ALIAS ="addressOrigin";
    String ORDER_STATUS_ALIAS ="orderStatus";
    String DISTANCE_ALIAS ="distance";
    String SERVICE_ALIAS="service";
    String WAY_OF_PAYMENT_ALIAS ="wayOfPayment";
    String CAR_CATEGORY_ALIAS ="carCategory";
    String MUSIC_STYLE_ALIAS ="musicStyle";
    String DRIVER_SEX_ALIAS ="driverSex";
    String DESCRIPTION_ALIAS="description";
    String COMMENTS_ALIAS ="comments";
    String COMMENTS_STATE_ALIAS ="commentsState";
    String BUTTON_COMMENTS_HIDE_ALIAS ="buttonCommentsHide";
    String ANIMAL_TRANSPORTATION_ALIAS ="animalTransportation";
    String FREE_WIFI_ALIAS ="freeWifi";
    String NON_SMOKING_DRIVER_ALIAS ="nonSmokingDriver";
    String AIR_CONDITIONER_ALIAS ="airConditioner";

    String HIDE="hidden=\"hidden\"";
    String DISABLE="disabled=\"disabled\"";
    String CHECKBOX_CHECKED ="checked=\"checked\"";
    String OPTION_SELECTED ="selected=\"selected\"";
    String ORDER_STATUS_VALUE_QUEUED = "QUEUED";



    static final String ERROR_PAGE="/WEB-INF/error.jsp";
    static final String ORDER_INFO_PAGE="/WEB-INF/customer/orderInfo.jsp";
    static final String MAP_PAGE="/WEB-INF/customer/map.jsp";
    static final String ORDER_TRACK_COMPLETE_PAGE="/WEB-INF/customer/orderTrackComplete.jsp";
    static final String ORDER_TRACK_PAGE="/WEB-INF/customer/orderTrack.jsp";
    static final String ORDER_PAGE="/WEB-INF/customer/order.jsp";

    static final String NON_EXIST_TRACKING_NUMBER_WARNING="nonExistTrackingNumberWarning";
    static final String NON_EXIST_TRACKING_NUMBER_WARNING_MESSAGE="requested order with such tracking number is not found";
    static final String REFUSE_SUCCESS="refuseSuccess";
    static final String REFUSE_SUCCESS_MESSAGE="Your order has been refused!";
    static final String REFUSE_WARNING="refuseWarning";
    static final String REFUSE_WARNING_MESSAGE=" Your order  has not been canceled,if you do not change your" +
            " mind please try again.";
    static final String ORDER_SUCCESS="orderSuccess";
    static final String ORDER_SUCCESS_TRACK_BUTTON = "\n"+"<a class=\"btn btn-large btn-success\" " +
            "href=\"orderInfo\"><h4 class=\"outline\">Track your taxi order</h4></a>";
    static final String ORDER_SUCCESS_MESSAGE = " Your order accepted for further processing " +
            "successfully and you was assigned to such tracking number: ";
    static final String ORDER_WARNING="orderWarning";
    static final String ORDER_WARNING_MESSAGE = "Your order has been rejected because you refused three" +
            " orders that you have made and so you been put in the black list.";
    static final String HIDE_ORDER_TRACK="hideOrderTrack";
    static final String UPDATE_SUCCESS="updateSuccess";
    static final String UPDATE_SUCCESS_MESSAGE="Your order has been updated successfully!";
    static final String ADD_COMMENTS_SUCCESS="addCommentsSuccess";
    static final String ADD_COMMENTS_SUCCESS_MESSAGE="Comments for yours order has been added successfully.";

}
