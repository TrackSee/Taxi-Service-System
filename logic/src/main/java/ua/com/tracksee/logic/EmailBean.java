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
    private @EJB UserDAO userDAO;


//    @Asynchronous
//    public void sendRegistrationEmail(ServiceUserEntity user, String userCode) throws MessagingException {
//        MimeMessage message = new MimeMessage(getEmailSession());
//        message.setFrom(new InternetAddress(SERVER_EMAIL));
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
//        message.setSubject("Registration at " + WEBSITE_SHORT);
//        message.setText(getMessageText(userCode));
//        Transport.send(message);
////        Map<String, Object> data = new HashMap<>();
////        data.put("activationLink", WEBSITE_FULL + "activation?code=" + userCode);
////        sendTemplatedEmail(user.getEmail(), REGISTRATION_EMAIL_SUBJECT,
////                REGISTRATION_TEMPLATE_PATH, data);
//        logger.debug("Sent message successfully to {}", user.getEmail());
//    }
//
//    private String getMessageText(String userCode) {
//        return "Hi, \n"
//                + "Your email address was used for registration at "
//                + WEBSITE_SHORT
//                + "\nPlease click the confirmation link to complete registration: "
//                + WEBSITE_FULL + "activation?code=" + userCode;
//    }

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


    @Asynchronous
    public void sendOrderConfirmation(UserEntity user, Long trackingNumber) {
//TODO use trackingNumber and use template
        MimeMessage message = new MimeMessage(EmailUtils.getEmailSession());
        try {
            message.setFrom(new InternetAddress(EmailUtils.SERVER_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Order Taxi " + WEBSITE_SHORT);
            message.setText(getOrderConfirmMessageText(user.getUserId()));
            Transport.send(message);
            logger.debug("Order confirmation email sent successfully to {}", user.getEmail());
        } catch (MessagingException e) {
            logger.warn("Order confirmation email sending to {} failed", user.getEmail());
        }
    }

    //TODO why not template?
    private String getOrderConfirmMessageText(int id) {
        return "Hello! \n"
                + "Order taxi confirm message! "
                + WEBSITE_SHORT
                + "\nLink for your dashbord whith orders: "
                + WEBSITE_FULL + "user_orders?user_id=" + id;
    }
}