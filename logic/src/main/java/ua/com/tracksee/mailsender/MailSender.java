package ua.com.tracksee.mailsender;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import static java.lang.System.getProperty;

import static ua.com.tracksee.mailsender.SenderSessionSpecificator.GMAIL;
import static ua.com.tracksee.mailsender.SenderSessionSpecificator.TRACK_SEE_MAIL;

/**
 * This bean provides standard basic mechanism for email notification
 *
 * @author Igor Dvorskij
 */
@Stateless
public class MailSender {

    private static final Logger logger = LogManager.getLogger();
    private static final Session SESSION = GMAIL.getSession();
//    private static Session SESSION = TRACK_SEE_MAIL.getSession();
    private static final String ROOT_PATH = getProperty("jboss.server.data.dir");
    private static final InternetAddress FROM_ADDRESS;
    private static final File TEMPLATE_DIR = new File(ROOT_PATH);

    static {
        InternetAddress fromAddressTemp;
        try {
            fromAddressTemp = SenderSessionSpecificator.TRACK_SEE_MAIL.getInternetAddress();
        } catch (AddressException e) {
            fromAddressTemp = null;
            logger.error("ERROR, can not parse server_email: {1} to getInternetAddress", "tracksee.mail@gmail.com");
        }
        FROM_ADDRESS = fromAddressTemp;
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
     * This method sends a email decorated with template to specified user
     *
     * @param to           - specifies the list of the users' email
     * @param subject      - specifies the mail subject
     * @param templatePath - specifies the mail body
     * @param data         - specifies the map of properties that is used for template generation
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
     * This method sends a email decorated with template to specified users
     *
     * @param to           - specifies the user email
     * @param subject      - specifies the mail subject
     * @param templatePath - specifies the mail body
     * @param data         - specifies the map of properties that is used for template generation
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
     * This method loads templates by the given path
     *
     * @param path - specifies the path that template should be loaded
     * @return the instance of Template
     * @throws IOException
     */
    public Template loadTemplate(String path) throws IOException {
        Configuration cfg = new Configuration();
       // cfg.setClassForTemplateLoading(this.getClass(), ROOT_PATH);
        cfg.setDirectoryForTemplateLoading(TEMPLATE_DIR);
        return cfg.getTemplate(path);
    }

}