package ua.com.tracksee.servlets.orders;

/**
 * @author Sharaban Sasha
 */
public interface OrderAttributes {
    static final String ORDER_TRACKING_NUMBER="orderTrackingNumber";
    static final String TRACKING_NUMBER="trackingNumber";
    static final String PHONE_NUMBER="phoneNumber";
    static final String EMAIL="email";
    static final String ARRIVE_DATE="arriveDate";
    static final String END_DATE="endDate";
    static final String ADDRESS_DESTINATION="addressDestination";
    static final String ADDRESS_ORIGIN="addressOrigin";
    static final String ORDER_STATUS="addressOrigin";
    static final String DISTANCE="distance";
    static final String SERVICE="service";
    static final String WAY_OF_PAYMENT="wayOfPayment";
    static final String CAR_CATEGORY="carCategory";
    static final String MUSIC_STYLE="musicStyle";
    static final String DRIVER_SEX="driverSex";
    static final String DESCRIPTION="description";
    static final String COMMENTS="comments";
    static final String COMMENTS_STATE="commentsState";
    static final String BUTTON_COMMENTS_HIDE="buttonCommentsHide";
    static final String ANIMAL_TRANSPORTATION="animalTransportation";
    static final String FREE_WIFI="freeWifi";
    static final String SMOKING_DRIVER="smokingDriver";
    static final String AIR_CONDITIONER="airConditioner";
    static final String CHECKBOX_CHECKED="checked=\"checked\"";
    static final String OPTION_SELECTED="selected=\"selected\"";
    static final String ORDER_STATUS_VALUE_QUEUED = "QUEUED";

    static final String HIDE="hidden=\"hidden\"";
    static final String DISABLE="disabled=\"disabled\"";

    static final String ERROR_PAGE="/WEB-INF/error.jsp";
    static final String ORDER_INFO_PAGE="/WEB-INF/customer/orderTrack.jsp";
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
    static final String ORDER_SUCCESS_TRACK_BUTTON = " \n <a class=\"btn btn-large btn-success\" href=\"orderInfo\">" +
            "<h4>Track your taxi order</h4></a>";
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
