package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;
import ua.com.tracksee.util.EmailUtils;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.HashMap;
import java.util.Map;

import static ua.com.tracksee.mailsender.MailSender.*;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class EmailBean {
    private static final Logger logger = LogManager.getLogger();

    // website
    private static final String WEBSITE_SHORT = "tracksee.team.com/TaxiService";
    private static final String WEBSITE_FULL = "localhost:8080/TaxiService/";

    // template properties
    private static final String SITE_ADDRESS_TEMP_PROP_NAME = "siteadress";

    private static final String BLOCKING_ACCOUNT_SUBJECT_TEMP_PROP_NAME = "TrackSee Blocking Account";
    private static final String BLOCKING_ACCOUNT_TEMP_PATH = "logic/src/main/resources/mailtemplates/blockingusertemplate.ftl";

    private static final String CHANGING_TO_FROM_ASSIGNED_TO_UPDATED_TEMP_PATH = "logic/src/main/resources/mailtemplates/changingtofromassignedtoupdatedtemplate.ftl";
    private static final String CHANGING_TO_FROM_ASSIGNED_TO_UPDATED_SUBJECT_TEMP_PROP_NAME = "TrackSee Blocking Account";

    @Asynchronous
    public void sendRegistrationEmail(ServiceUserEntity user, String userCode) throws MessagingException {
        MimeMessage message = new MimeMessage(EmailUtils.getEmailSession());
        message.setFrom(new InternetAddress(EmailUtils.SERVER_EMAIL));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        message.setSubject("Registration at " + WEBSITE_SHORT);
        message.setText(getMessageText(userCode));
        Transport.send(message);
        logger.debug("Sent message successfully to {1}", user.getEmail());
    }

    private String getMessageText(String userCode) {
        return "Hi, \n"
                +  "Your email address was used for registration at "
                + WEBSITE_SHORT
                + "\nPlease click the confirmation link to complete registration: "
                + WEBSITE_FULL + "activation?code=" + userCode;
    }

    @Asynchronous
    public void sendBlockingUserEmail(ua.com.tracksee.entities.ServiceUserEntity user) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        sendTemplatedEmail(user.getEmail(), BLOCKING_ACCOUNT_SUBJECT_TEMP_PROP_NAME, BLOCKING_ACCOUNT_TEMP_PATH, data);
    }

    @Asynchronous
    public void sendChangingTOFromAssignedToCompleted(TaxiOrderItemEntity order) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SITE_ADDRESS_TEMP_PROP_NAME, WEBSITE_FULL);
        data.put("trackingNumber", order.getTaxiOrderByTrackingNumer().getTrackingNumber());
        data.put("color", order.getServiceUserByDriverId().getCar().getColor());
        data.put("carModel", order.getServiceUserByDriverId().getCar().getCarModel());
        data.put("carNumber", order.getServiceUserByDriverId().getCar().getCarNumber());
        sendTemplatedEmail(order.getServiceUserByDriverId().getEmail(),
                CHANGING_TO_FROM_ASSIGNED_TO_UPDATED_SUBJECT_TEMP_PROP_NAME,
                CHANGING_TO_FROM_ASSIGNED_TO_UPDATED_TEMP_PATH, data);

    }
}