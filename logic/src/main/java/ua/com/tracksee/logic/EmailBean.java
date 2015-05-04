package ua.com.tracksee.logic;

import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.com.tracksee.mailsender.MailSender.sendTemplatedEmail;
import static ua.com.tracksee.util.EmailUtils.SERVER_EMAIL;
import static ua.com.tracksee.util.EmailUtils.getEmailSession;

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
    private static final String REGISTRATION_URL = "c";
    // template properties
    private static final String SITE_ADDRESS_TEMP_PROP_NAME = "siteadress";
    private static final String REGISTRATION_EMAIL_SUBJECT = "Registration at TrackSee";
    private static final String REGISTRATION_TEMPLATE_PATH = "logic/mailtemplates/registration-template.ftl";
    private static final String BLOCKING_ACCOUNT_SUBJECT_TEMP_PROP_NAME = "TrackSee Blocking Account";

    private static final String BLOCKING_ACCOUNT_TEMP_PATHSTRING = "mailtemplates/blockingusertemplate.ftl";

    private static final String BLOCKING_ACCOUNT_TEMP_PATH = "logic/src/main/resources/mailtemplates/blockingusertemplate.ftl";

    private static final String CHANGING_TO_FROM_ASSIGNED_TO_INPROGRESS_TEMP_PATH = "logic/src/main/resources/mailtemplates/changing_to-from-assigned_to_inprogress_template.ftl";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_INPROGRESS_SUBJECT_TEMP_PROP_NAME = "TrackSee Order in progress";
    private static final String CHANGING_TO_FROM_INPROGRESS_TO_COMPLETED_TEMP_PATH = "logic/src/main/resources/mailtemplates/changing_to-from-inprogress_to_copleted_template.ftl";
    private static final String CHANGING_TO_FROM_INPROGRESS_TO_COMPLETED_SUBJECT_TEMP_PROP_NAME = "TrackSee Order completed";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_TEMP_PATH = "logic/src/main/resources/mailtemplates/changing_to-from-assigned_to_refused.ftl";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_SUBJECT_TEMP_PROP_NAME = "TrackSee Order refused";
    private static final String REGISTRATION_TEMP_PATH = "logic/src/main/resources/mailtemplates/registration_template.ftl";
    private static final String REGISTRATION_SUBJECT_TEMP_PROP_NAME = "TrackSee Confirm User Registration";
    private
    @EJB
    UserDAO userDAO;

    @Asynchronous
    public void sendRegistrationEmail(String email, String userCode) throws MessagingException {

        MimeMessage message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(SERVER_EMAIL));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Registration at " + WEBSITE_SHORT);
        message.setText(getMessageText(userCode));
        Transport.send(message);
//        Map<String, Object> data = new HashMap<>();
//        data.put("activationLink", WEBSITE_FULL + "activation?code=" + userCode);
//        sendTemplatedEmail(user.getEmail(), REGISTRATION_EMAIL_SUBJECT,
//                REGISTRATION_TEMPLATE_PATH, data);
        logger.debug("Sent message successfully to {}", email);
    }

    private String getMessageText(String userCode) {
        return "Hi, \n"
                + "Your email address was used for registration at "
                + WEBSITE_SHORT
                + "\nPlease click the confirmation link to complete registration: "
                + WEBSITE_FULL + "activation?code=" + userCode;
    }

    /**
     * @param user
     * @param userCode
     */
    @Asynchronous
    public void sendRegistration(ServiceUserEntity user, String userCode) throws TemplateException, IOException, MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("website_full", WEBSITE_FULL);
        data.put("website_short", WEBSITE_SHORT);
        data.put("userCode", userCode);//userCode
        sendTemplatedEmail(user.getEmail(), REGISTRATION_SUBJECT_TEMP_PROP_NAME, REGISTRATION_TEMP_PATH, data);
    }

    /**
     * @param user
     */
    @Asynchronous
    public void sendBlockingUserEmail(ua.com.tracksee.entities.ServiceUserEntity user) throws TemplateException, IOException, MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        sendTemplatedEmail(user.getEmail(), BLOCKING_ACCOUNT_SUBJECT_TEMP_PROP_NAME, BLOCKING_ACCOUNT_TEMP_PATH, data);
    }

    /**
     * @param order
     */

    @Asynchronous
    public void sendChangingTOFromAssignedToInProgress(TaxiOrderItemEntity order) throws TemplateException, IOException, MessagingException {
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
    public void sendChangingTOFromInProgressToCompleted(TaxiOrderItemEntity order) throws TemplateException, IOException, MessagingException {
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
    public void sendChangingTOFromAssignedToRefused(TaxiOrderItemEntity order) throws TemplateException, IOException, MessagingException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrder().getTrackingNumber());

        sendTemplatedEmail(userDAO.getUserById(order.getTaxiOrder().getUserId()).getEmail(), CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_SUBJECT_TEMP_PROP_NAME,
                CHANGING_TO_FROM_ASSIGNED_TO_REFUSED_TEMP_PATH, data);
    }


    public void sendOrderConfirmation(ServiceUserEntity user) {

    }


    @Asynchronous
    public void sendOrderConfirmInfo(ServiceUserEntity user) throws MessagingException {
        MimeMessage message = new MimeMessage(EmailUtils.getEmailSession());
        message.setFrom(new InternetAddress(EmailUtils.SERVER_EMAIL));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        message.setSubject("Order Taxi " + WEBSITE_SHORT);
        message.setText(getOrderConfirmMessageText(user.getUserId()));
        Transport.send(message);
        logger.debug("Sent message successfully to {1}", user.getEmail());
    }

    private String getOrderConfirmMessageText(int id) {
        return "Hello! \n"
                + "Order taxi confirm message! "
                + WEBSITE_SHORT
                + "\nLink for your dashbord whith orders: "
                + WEBSITE_FULL + "user_orders?user_id=" + id;
    }
}