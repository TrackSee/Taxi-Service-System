package ua.com.tracksee.mailsender;

/**
 * @author Igor Dvorskij
 */

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ua.com.tracksee.mailsender.SenderSessionSpecificator.GMAIL;

public class MailSender {
    private static Session SESSION = GMAIL.getSession();
    private static InternetAddress FROM_ADDRESS;

    static {
        try {
            FROM_ADDRESS = SenderSessionSpecificator.GMAIL.getInternetAddress();
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
            message.setContent(body, "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
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
            }

            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public static void sendTemplatedEmail(String to, String subject,
                                          String templatePath, Map<String, Object> data) {
        try {
            Template template = loadTemplate(templatePath);
            Writer out = new StringWriter();
            template.process(data, out);
            String body = out.toString();
            out.flush();

            sendEmail(to, subject, body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static Template loadTemplate(String path) throws IOException {
        Configuration cfg = new Configuration();
        return cfg
                .getTemplate(path);
    }

}