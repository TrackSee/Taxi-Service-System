package ua.com.tracksee.mailsender;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 17.04.15
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailSender {
    private static Session SESSION = GMAIL.getSession();
    private static InternetAddress FROM_ADDRESS;

    static {
        try {
            FROM_ADDRESS = GMAIL.getInternetAddress();
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sends an email to specified user
     *
     * @param to      - specifies the user email
     * @param subject - specifies the mail subject
     * @param body    - specifies the mail body
     */
    public static void sendEmail(String to, String subject, String body) {
        try {
            Message message = new MimeMessage(SESSION);
            message.setFrom(FROM_ADDRESS);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method sends an email to specified users
     *
     * @param recipients - specifies the user emails list
     * @param subject    - specifies the mail subject
     * @param body       - specifies the mail body
     */
    public static void sendEmailToGroup(java.util.List<String> recipients, String subject, String body) {
        try {
            Message message = new MimeMessage(SESSION);
            message.setFrom(FROM_ADDRESS);

            for (String to : recipients) {
                message.addRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                System.out.println(to);
            }

            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}