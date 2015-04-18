package com.tss.tracksee.mailsender;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 17.04.15
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */

import javax.mail.*;
import javax.mail.internet.*;
import static com.tss.tracksee.mailsender.SenderSessionSpecificator.*;

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


    public static void sendEmail(String to, String subject, String text) {
        try {
            Message message = new MimeMessage(SESSION);
            message.setFrom(FROM_ADDRESS);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}