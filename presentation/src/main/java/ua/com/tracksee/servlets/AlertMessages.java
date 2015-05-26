package ua.com.tracksee.servlets;

/**
 * This servlets contains alert
 * messages that used for alerts.
 *
 * @author Sharaban Sasha
 */
public interface AlertMessages {


    String NON_EXIST_TRACKING_DANGER ="nonExistTrackingNumberDanger";
    String NON_EXIST_TRACKING_NUMBER_DANGER_MESSAGE ="Requested order with such tracking number" +
            " was not found! To view this order make sure that the entered number is correct!";
    String ACTIVE_USER_ORDER_WARNING ="activeUserOrderWarning";
    String ACTIVE_USER_ORDER_MESSAGE ="Requested order with such tracking number belongs to a registered user!" +
            " To view the order you need to log in as this user! ";
    String NON_ACTIVE_USER_ORDER_WARNING ="nonActiveUserOrderWarning";
    String NON_ACTIVE_USER_ORDER_MESSAGE ="Requested order with such tracking number belongs to a unregistered user!" +
            " To view this order you need to log in as a unregistered user! ";
    String NON_ACTIVE_USER_ORDER_MESSAGE_UNREGISTERED ="Requested order with such tracking number belongs to another user" +
            " To view this order make sure that the entered number and email is correct! ";
    String REFUSE_SUCCESS="refuseSuccess";
    String REFUSE_SUCCESS_MESSAGE="Your order has been refused!";
    String ORDER_SUCCESS="orderSuccess";
    String ORDER_SUCCESS_TRACK_BUTTON = "\n"+"<a class=\"btn btn-large btn-success\" " +
            "href=\"orderInfo\"><h4 class=\"outline\">Track your taxi order</h4></a>";

    String ORDER_SUCCESS_MESSAGE = " Your order has been successfully received for further processing!\n" +
            " Your order tracking number: ";
    String ORDER_DANGER ="orderDanger";
    String ORDER_WARNING ="orderWarning";
    String ORDER_DANGER_BLACK_LIST_MESSAGE = "Your order has been rejected! You refused three orders and therefore " +
            " your account has been locked, to unlock it, leave explanations and request to unblock";
    String ORDER_WARNING_AUTHORISE_MESSAGE = "Your order has been rejected! To order, you have used email" +
            " of registered user! If you are a registered user, log in to make an order.";
    String HIDE_ORDER_TRACK="hideOrderTrack";
    String UPDATE_SUCCESS="updateSuccess";
    String UPDATE_SUCCESS_MESSAGE="Your order has been updated successfully!";
    String ADD_COMMENTS_SUCCESS="addCommentsSuccess";
    String ADD_COMMENTS_SUCCESS_MESSAGE="Comments for yours order has been added successfully.";

}
