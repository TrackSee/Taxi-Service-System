package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.mailsender.MailSender;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A bean provides any email sending action used in the system.
 *
 * @author Ruslan Gunavardana
 * @author Igor Dvorskij
 */
@Stateless
public class EmailBean {

    private static final Logger logger = LogManager.getLogger(EmailBean.class);
    // website
    private static final String WEBSITE_SHORT = "tracksee.tk";
    private static final String WEBSITE_FULL = "http://tracksee.tk/";
    private static final String REGISTRATION_URL = "http://tracksee.tk/signup";

    // template properties
    private static final String CONFIG_LOCATION = "/mailtemplates/";
    private static final String SITE_ADDRESS_TEMP_PROP_NAME = "siteadress";
    private static final String BLOCKING_ACCOUNT_SUBJECT_TEMP_PROP_NAME = "TrackSee Blocking Account";
    private static final String BLOCKING_ACCOUNT_TEMP_PATH = CONFIG_LOCATION + "blockingusertemplate.ftl";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_INPROGRESS_TEMP_PATH = CONFIG_LOCATION + "changing_to-from-assigned_to_inprogress_template.ftl";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_INPROGRESS_SUBJECT_TEMP_PROP_NAME = "TrackSee Order in progress";
    private static final String CHANGING_TO_FROM_INPROGRESS_TO_COMPLETED_TEMP_PATH = CONFIG_LOCATION + "changing_to-from-inprogress_to_copleted_template.ftl";
    private static final String CHANGING_TO_FROM_INPROGRESS_TO_COMPLETED_SUBJECT_TEMP_PROP_NAME = "TrackSee Order completed";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_TEMP_PATH = CONFIG_LOCATION + "changing_to-from-assigned_to_refused.ftl";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_SUBJECT_TEMP_PROP_NAME = "TrackSee Order refused";
    private static final String REGISTRATION_TEMP_PATH = CONFIG_LOCATION + "registration_template.ftl";
    private static final String REGISTRATION_SUBJECT_TEMP_PROP_NAME = "TrackSee: Confirm User Registration";
    private static final String CHANGING_TO_FROM_QUEUED_TO_UPDATED_TEMP_PATH = CONFIG_LOCATION + "changing_to-from-queued-to-updated.ftl";
    private static final String CHANGING_TO_FROM_QUEUED_TO_UPDATED_TEMP_SUBJECT_PROP_NAME = "TrackSee Order Updated";

    private static final String CONFIRMATION_TEMP_PATH = CONFIG_LOCATION + "confirmation_template.ftl";
    private static final String CONFIRMATION_TEMP_SUBJECT_PROP_NAME = "TrackSee Order Confirmation";

    private static final String CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_BY_DRIVER_TEMP_PATH = CONFIG_LOCATION + "changing_to_refused_by_driver.ftl";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_BY_DRIVER_TEMP_SUBJECT_PROP_NAME = "TrackSee Order Refused";


    private
    @EJB
    UserDAO userDAO;

    private
    @EJB
    MailSender sender;


    /**
     * This method notifies user about their registration
     * and sends confirmation link for registration
     *
     * @param email    - specifies the email to sent notification
     * @param userCode - specifies the code that is used for confirmation of the user's registration
     */
    @Asynchronous
    public void sendRegistrationEmail(String email, String userCode) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("website_full", WEBSITE_FULL);
        data.put("website_short", WEBSITE_SHORT);
        data.put("userCode", userCode);
        try {
            sender.sendTemplatedEmail(email, REGISTRATION_SUBJECT_TEMP_PROP_NAME,
                    REGISTRATION_TEMP_PATH, data);
        } catch (MessagingException e) {
            logger.warn("Sending email to {} failed. Message: {}", email, e.getMessage());
        }
    }

    /**
     * This method notifies user about blocking in the system
     *
     * @param user - specifies the user to send notification
     * @throws MessagingException
     */
    public void sendBlockingUserEmail(UserEntity user) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        sender.sendTemplatedEmail(user.getEmail(), BLOCKING_ACCOUNT_SUBJECT_TEMP_PROP_NAME,
                BLOCKING_ACCOUNT_TEMP_PATH, data);
    }

    /**
     * This method notifies user about changing the status of their order from assigned to in progress
     *
     * @param order - specifies order that have been recently changed
     * @throws MessagingException
     */
    public void sendChangingTOFromAssignedToInProgress(TaxiOrderItemEntity order) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrder().getTrackingNumber());
        data.put("color", order.getDriver().getCar().getColor());
        data.put("carModel", order.getDriver().getCar().getCarModel());
        data.put("carNumber", order.getDriver().getCar().getCarNumber());
        sender.sendTemplatedEmail(order.getTaxiOrder().getUser().getEmail(),
                CHANGING_TO_FROM_ASSIGNED_TO_INPROGRESS_SUBJECT_TEMP_PROP_NAME,
                CHANGING_TO_FROM_ASSIGNED_TO_INPROGRESS_TEMP_PATH, data);
    }

    /**
     * This method notifies user about changing the status of their order from in progress to completed
     *
     * @param order - specifies order that have been recently changed
     * @throws MessagingException
     */
    public void sendChangingTOFromInProgressToCompleted(TaxiOrderItemEntity order) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrder().getTrackingNumber());
        data.put("registrationURL", REGISTRATION_URL);

        sender.sendTemplatedEmail(order.getTaxiOrder().getUser().getEmail(),
                CHANGING_TO_FROM_INPROGRESS_TO_COMPLETED_SUBJECT_TEMP_PROP_NAME,
                CHANGING_TO_FROM_INPROGRESS_TO_COMPLETED_TEMP_PATH, data);
    }


    /**
     * This method notifies user about changing the status of their order from assigned to refused
     * because user ordered and didn't use taxi service
     *
     * @param order - specifies order that have been recently changed
     * @throws MessagingException
     */
    public void sendChangingTOFromAssignedToRefused(TaxiOrderItemEntity order) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrder().getTrackingNumber());

        sender.sendTemplatedEmail(order.getTaxiOrder().getUser().getEmail(),
                CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_SUBJECT_TEMP_PROP_NAME,
                CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_TEMP_PATH, data);
    }


    /**
     * This method notifies all the drivers about changing the status of their order from queued to updated
     *
     * @param order - specifies order that have been recently changed
     * @throws MessagingException
     */
    public void sendChangingTOFromQueuedToUpdated(TaxiOrderItemEntity order) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrder().getTrackingNumber());
        data.put("carCat", order.getTaxiOrder().getCarCategory().toString());
        data.put("wayOfPay", order.getTaxiOrder().getFreeWifi().toString());
        data.put("animal", order.getTaxiOrder().getAnimalTransportation() ? "yes" : "no");
        data.put("wifi", order.getTaxiOrder().getFreeWifi() ? "yes" : "no");
        data.put("smoking", order.getTaxiOrder().getNonSmokingDriver() ? "yes" : "no");
        data.put("music", order.getTaxiOrder().getMusicStyle());
        List<String> driverEmails = userDAO.getDriversEmails();
        sender.sendTemplatedEmail(driverEmails, CHANGING_TO_FROM_QUEUED_TO_UPDATED_TEMP_SUBJECT_PROP_NAME,
                CHANGING_TO_FROM_QUEUED_TO_UPDATED_TEMP_PATH, data);
    }

    /**
     *This method notifies user that their order have been processed by the system
     * and sends the confirmation code for this order
     *
     * @param user           - specifies user to send confirmation link
     * @param trackingNumber - specifies code that is used for confirmation
     */
    @Asynchronous
    public void sendOrderConfirmation(UserEntity user, Long trackingNumber) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
            data.put("userCode", trackingNumber);
            data.put("website_short", WEBSITE_SHORT);
            data.put("website_full", WEBSITE_FULL);
            sender.sendTemplatedEmail(user.getEmail(), CONFIRMATION_TEMP_SUBJECT_PROP_NAME,
                    CONFIRMATION_TEMP_PATH, data);
            logger.debug("Order confirmation email sent successfully to {}", user.getEmail());
        } catch (MessagingException e) {
            logger.warn("Order confirmation email sending to {} failed", user.getEmail());
        }
    }

    /**
     * This method notifies user about
     * changing the status of their order from assigned to refused due to some problem caused by organization
     *
     * @param order - specifies order that have been recently changed
     * @throws MessagingException
     */
    public void sendChangingTOFromAssignedToRefusedMadeByDriver(TaxiOrderEntity order) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
                data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTrackingNumber());
        sender.sendTemplatedEmail(order.getUser().getEmail(),
                CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_BY_DRIVER_TEMP_SUBJECT_PROP_NAME,
                CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_BY_DRIVER_TEMP_PATH,  data);
    }
}