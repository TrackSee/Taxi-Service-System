package ua.com.tracksee.servlets.orders;

/**
 * @author Sharaban Sasha
 */
public interface OrderAttributes {
    static final String TRACKING_NUMBER_ALIAS ="trackingNumber";
    static final String USER_ID_ALIAS ="userId";
    static final String AMOUNT_OF_CARS_ALIAS ="amountOfCars";
    static final String AMOUNT_OF_HOURS_ALIAS ="amountOfHours";
    static final String AMOUNT_OF_MINUTES_ALIAS ="amountOfMinutes";
    static final String PHONE_NUMBER_ALIAS ="phoneNumber";
    static final String EMAIL_ALIAS ="email";
    static final String ADDRESSES_PATH ="addressPath";
    static final String PRICE_ALIAS ="price";
    static final String ARRIVE_DATE_ALIAS ="arriveDate";
    static final String END_DATE_ALIAS ="endDate";
    static final String ADDRESS_DESTINATION_ALIAS ="addressDestination";
    static final String ADDRESS_ORIGIN_ALIAS ="addressOrigin";
    static final String ORDER_STATUS_ALIAS ="orderStatus";
    static final String DISTANCE_ALIAS ="distance";
    static final String SERVICE_ALIAS="service";
    static final String WAY_OF_PAYMENT_ALIAS ="wayOfPayment";
    static final String CAR_CATEGORY_ALIAS ="carCategory";
    static final String MUSIC_STYLE_ALIAS ="musicStyle";
    static final String DRIVER_SEX_ALIAS ="driverSex";
    static final String DESCRIPTION_ALIAS="description";
    static final String COMMENTS_ALIAS ="comments";
    static final String COMMENTS_STATE_ALIAS ="commentsState";
    static final String BUTTON_COMMENTS_HIDE_ALIAS ="buttonCommentsHide";
    static final String ANIMAL_TRANSPORTATION_ALIAS ="animalTransportation";
    static final String FREE_WIFI_ALIAS ="freeWifi";
    static final String NON_SMOKING_DRIVER_ALIAS ="nonSmokingDriver";
    static final String AIR_CONDITIONER_ALIAS ="airConditioner";

    static final String HIDE="hidden=\"hidden\"";
    static final String DISABLE="disabled=\"disabled\"";
    static final String CHECKBOX_CHECKED ="checked=\"checked\"";
    static final String OPTION_SELECTED ="selected=\"selected\"";
    static final String ORDER_STATUS_VALUE_QUEUED = "QUEUED";



    static final String ERROR_PAGE="/WEB-INF/error.jsp";
    static final String ORDER_INFO_PAGE="/WEB-INF/customer/orderInfo.jsp";
    static final String MAP_PAGE="/WEB-INF/customer/map.jsp";
    static final String ORDER_TRACK_COMPLETE_PAGE="/WEB-INF/customer/orderTrackComplete.jsp";
    static final String ORDER_TRACK_PAGE="/WEB-INF/customer/orderTrack.jsp";
    static final String ORDER_PAGE="/WEB-INF/customer/order.jsp";
    static final String ORDER_IN_PROGRESS_PAGE ="/WEB-INF/customer/orderTrackInProgress.jsp" ;

    static final String NON_EXIST_TRACKING_NUMBER_WARNING="nonExistTrackingNumberWarning";
    static final String NON_EXIST_TRACKING_NUMBER_WARNING_MESSAGE="Requested order with such tracking number" +
            " is not found or it is belong to registered user";
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
