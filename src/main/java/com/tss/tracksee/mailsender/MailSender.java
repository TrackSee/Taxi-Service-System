package com.tss.tracksee.mailsender;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 17.04.15
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSender {
    public static void main(String[] args) {
        final String username = "tracksee.mail@gmail.com";
        final String password = "service-taxi";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("idvorskij@mail.ru"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}