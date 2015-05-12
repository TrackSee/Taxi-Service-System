package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;
import ua.com.tracksee.util.EmailUtils;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.io.File.separatorChar;
import static java.lang.System.getProperty;
import static ua.com.tracksee.mailsender.MailSender.sendTemplatedEmail;

/**
 * A bean provides any email sending action used in the system.
 *
 * @author Ruslan Gunavardana
 * @author Igor Dvorskij
 */
@Stateless
public class EmailBean {

    private static final Logger logger = LogManager.getLogger();
    // website
    private static final String WEBSITE_SHORT = "tracksee.team.com/TaxiService";
    private static final String WEBSITE_FULL = "http://localhost:8080/TaxiService/";
    private static final String CONFIG_LOCATION = getProperty("jboss.server.data.dir")
            + separatorChar + "mailtemplates" + separatorChar;
    //TODO
    private static final String REGISTRATION_URL = "c";
    // template properties
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


    private @EJB UserDAO userDAO;


    /**
     * @param email
     * @param userCode
     */
    @Asynchronous
    public void sendRegistrationEmail(String email, String userCode) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("website_full", WEBSITE_FULL);
        data.put("website_short", WEBSITE_SHORT);
        data.put("userCode", userCode);
        try {
            sendTemplatedEmail(email, REGISTRATION_SUBJECT_TEMP_PROP_NAME, REGISTRATION_TEMP_PATH, data);
        } catch (MessagingException e) {
            logger.warn("Sending email to {} failed. Message: {}", email, e.getMessage());
        }
    }

    /**
     * @param user
     */
    @Asynchronous
    public void sendBlockingUserEmail(UserEntity user) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        sendTemplatedEmail(user.getEmail(), BLOCKING_ACCOUNT_SUBJECT_TEMP_PROP_NAME, BLOCKING_ACCOUNT_TEMP_PATH, data);
    }

    /**
     * @param order
     */

    @Asynchronous
    public void sendChangingTOFromAssignedToInProgress(TaxiOrderItemEntity order) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrder().getTrackingNumber());
        data.put("color", order.getDriver().getCar().getColor());
        data.put("carModel", order.getDriver().getCar().getCarModel());
        data.put("carNumber", order.getDriver().getCar().getCarNumber());
        sendTemplatedEmail(userDAO.getUserById(order.getTaxiOrder().getUserId()).getEmail(),
                CHANGING_TO_FROM_ASSIGNED_TO_INPROGRESS_SUBJECT_TEMP_PROP_NAME,
                CHANGING_TO_FROM_ASSIGNED_TO_INPROGRESS_TEMP_PATH, data);
    }

    /**
     * @param order
     */
    @Asynchronous
    public void sendChangingTOFromInProgressToCompleted(TaxiOrderItemEntity order) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrder().getTrackingNumber());
        data.put("registrationURL", REGISTRATION_URL);

        sendTemplatedEmail(userDAO.getUserById(order.getTaxiOrder().getUserId()).getEmail(), CHANGING_TO_FROM_INPROGRESS_TO_COMPLETED_SUBJECT_TEMP_PROP_NAME,
                CHANGING_TO_FROM_INPROGRESS_TO_COMPLETED_TEMP_PATH, data);
    }


    /**
     * @param order
     */
    @Asynchronous
    public void sendChangingTOFromAssignedToRefused(TaxiOrderItemEntity order) throws MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrder().getTrackingNumber());

        sendTemplatedEmail(userDAO.getUserById(order.getTaxiOrder().getUserId()).getEmail(), CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_SUBJECT_TEMP_PROP_NAME,
                CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_TEMP_PATH, data);
    }


    /**
     *
     * @param order
     * @throws MessagingException
     */
    @Asynchronous
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
        sendTemplatedEmail(driverEmails, CHANGING_TO_FROM_QUEUED_TO_UPDATED_TEMP_SUBJECT_PROP_NAME, CHANGING_TO_FROM_QUEUED_TO_UPDATED_TEMP_PATH, data);
    }

    /**
     *
     * @param user
     * @param trackingNumber
     */
    @Asynchronous
    public void sendOrderConfirmation(UserEntity user, Long trackingNumber) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
            data.put("userCode", trackingNumber);
            data.put("website_short", WEBSITE_SHORT);
            data.put("website_full", WEBSITE_FULL);
            sendTemplatedEmail(user.getEmail(), CONFIRMATION_TEMP_SUBJECT_PROP_NAME, CONFIRMATION_TEMP_PATH, data );
            logger.debug("Order confirmation email sent successfully to {}", user.getEmail());
        } catch (MessagingException e) {
            logger.warn("Order confirmation email sending to {} failed", user.getEmail());
        }
    }
}