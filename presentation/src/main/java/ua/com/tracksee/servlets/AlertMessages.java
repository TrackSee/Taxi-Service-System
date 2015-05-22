package ua.com.tracksee.servlets;

/**
 * @author Sharaban Sasha
 */
public interface AlertMessages {


    String NON_EXIST_TRACKING_NUMBER_WARNING="nonExistTrackingNumberWarning";
    String NON_EXIST_TRACKING_NUMBER_WARNING_MESSAGE="Requested order with such tracking number" +
            " is not found or it is belong to registered user";
    String REFUSE_SUCCESS="refuseSuccess";
    String REFUSE_SUCCESS_MESSAGE="Your order has been refused!";
    String REFUSE_WARNING="refuseWarning";
    String REFUSE_WARNING_MESSAGE=" Your order  has not been canceled,if you do not change your" +
            " mind please try again.";
    String ORDER_SUCCESS="orderSuccess";
    String ORDER_SUCCESS_TRACK_BUTTON = "\n"+"<a class=\"btn btn-large btn-success\" " +
            "href=\"orderInfo\"><h4 class=\"outline\">Track your taxi order</h4></a>";
    String ORDER_SUCCESS_MESSAGE = " Your order accepted for further processing " +
            "successfully and you was assigned to such tracking number: ";
    String ORDER_SUCCESS_MESSAGE_WITHOUT_TRACK_NUMB = " Your order accepted for further processing " +
            "successfully and order number by which it can be tracked has been sent to your email.";
    String ORDER_WARNING="orderWarning";
    String ORDER_WARNING_BLACK_LIST_MESSAGE = "Your order has been rejected because you refused three" +
            " orders that you have made and so you been put in the black list.";
    String ORDER_WARNING_AUTHORISE_MESSAGE = "Your order has been rejected because you have used" +
            "email address of registered user, if you is registered user, please login to make order ";
    String HIDE_ORDER_TRACK="hideOrderTrack";
    String UPDATE_SUCCESS="updateSuccess";
    String UPDATE_SUCCESS_MESSAGE="Your order has been updated successfully!";
    String ADD_COMMENTS_SUCCESS="addCommentsSuccess";
    String ADD_COMMENTS_SUCCESS_MESSAGE="Comments for yours order has been added successfully.";

}
