package ua.com.tracksee.mailsender;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
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
import java.util.List;
import java.util.Map;

import static ua.com.tracksee.mailsender.SenderSessionSpecificator.GMAIL;

/**
 * This bean provides standard basic mechanism for email notification
 *
 * @author Igor Dvorskij
 */
@Stateless
public class MailSender {

    private static final Logger logger = LogManager.getLogger();
    private static Session SESSION = GMAIL.getSession();
    private static final String ROOT_PATH = "/";

    private static InternetAddress FROM_ADDRESS;

    static {
        try {
            FROM_ADDRESS = SenderSessionSpecificator.GMAIL.getInternetAddress();
        } catch (AddressException e) {
            logger.error("ERROR, can not parse server_email: {1} to getInternetAddress", "tracksee.mail@gmail.com");
        }
    }

    /**
     * This method sends an email to specified user
     *
     * @param to      - specifies the user email
     * @param subject - specifies the mail subject
     * @param body    - specifies the mail body
     */
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        Message message = new MimeMessage(SESSION);
        message.setFrom(FROM_ADDRESS);
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(body, "text/html");
        Transport.send(message);
    }

    /**
     * This method sends an email to specified users
     *
     * @param recipients - specifies the user emails list
     * @param subject    - specifies the mail subject
     * @param body       - specifies the mail body
     */
    public void sendEmailToGroup(List<String> recipients,
                                        String subject, String body) throws MessagingException {
        Message message = new MimeMessage(SESSION);
        message.setFrom(FROM_ADDRESS);

        for (String to : recipients) {
            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
        }
        message.setSubject(subject);
        message.setContent(body, "text/html");
        Transport.send(message);
    }


    /**
     * @param to
     * @param subject
     * @param templatePath
     * @param data
     * @throws MessagingException
     */
    public void sendTemplatedEmail(String to, String subject,
                                          String templatePath, Map<String, Object> data) throws MessagingException {
        String body;
        try {
            Template template = loadTemplate(templatePath);
            Writer out = new StringWriter();
            template.process(data, out);
            body = out.toString();
            out.flush();
        } catch (TemplateException | IOException e) {
            throw new MessagingException("Unable to create template due: " + e.getMessage());
        }
        sendEmail(to, subject, body);
    }

    /**
     * @param to
     * @param subject
     * @param templatePath
     * @param data
     * @throws MessagingException
     */
    public void sendTemplatedEmail(List<String> to, String subject,
                                          String templatePath, Map<String, Object> data) throws MessagingException {
        String body;
        try {
            Template template = loadTemplate(templatePath);
            Writer out = new StringWriter();
            template.process(data, out);
            body = out.toString();
            out.flush();
        } catch (TemplateException | IOException e) {
            throw new MessagingException("Unable to create template due: " + e.getMessage());
        }
        sendEmailToGroup(to, subject, body);

    }

    /**
     * @param path
     * @return
     * @throws IOException
     */
    public Template loadTemplate(String path) throws IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), ROOT_PATH);
        return cfg.getTemplate(path);
    }

}