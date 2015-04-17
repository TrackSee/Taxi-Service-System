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

public class MailSender {
    public static void main(String[] args) {
        Session session = SenderSessionSpecificator.GMAIL.getSession();

        try {

            Message message = new MimeMessage(session);
            message.setFrom(SenderSessionSpecificator.GMAIL.getInternetAddress());
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



    public static void sendEmail(){

    }
}