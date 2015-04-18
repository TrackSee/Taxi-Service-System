package logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static util.EmailUtils.SERVER_EMAIL;
import static util.EmailUtils.getEmailSession;

/**
 * @author Ruslan Gunavardana.
 */
@Stateless
public class EmailController {
    private Logger logger;

    @PostConstruct
    private void postActivate() {
        logger = LogManager.getLogger();
    }

    // website
    private static final String WEBSITE_SHORT = "tracksee.com";
    private static final String WEBSITE_FULL = "http://tracksee.com/";

    @Asynchronous
    public void sendRegistrationEmail(User user, String userCode) throws MessagingException {
        MimeMessage message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(SERVER_EMAIL));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        message.setSubject("Registration at " + WEBSITE_SHORT);
        message.setText(getMessageText(userCode));
        Transport.send(message);
        logger.debug("Sent message successfully to {1}", user.getEmail());
    }

    private static String getMessageText(String userCode) {
        return "Dear friend, \n"
                +  "Your email address was used for registration at "
                + WEBSITE_SHORT
                + "\nPlease click the confirmation link to complete registration: "
                + WEBSITE_FULL + "activation?code=" + userCode;
    }
}
