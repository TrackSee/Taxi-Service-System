package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.util.EmailUtils;

import ua.com.tracksee.mailsender.MailSender;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.lang.String;


/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class EmailBean {
    private static final Logger logger = LogManager.getLogger();

    // website
    private static final String WEBSITE_SHORT = "tracksee.com";
    private static final String WEBSITE_FULL = "http://tracksee.com/";

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
    public void sendBlockingUserEmail(User user){
        String body = "Hi! Sorry, but our cooperation have become impossible from now. Have a nice day! ";
        String subject = "Blocking account";
        MailSender.sendEmail(user.getEmail(), subject, body);
    }
}