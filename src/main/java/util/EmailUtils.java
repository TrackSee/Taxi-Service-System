package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * @author Ruslan Gunavardana.
 */
public class EmailUtils {
    private static Properties properties;

    // SMTP configs
    private static final String SMTP_PORT = "587";
    private static final String SMTP_HOST = "smtp.gmail.com";

    // email configs
    public static final String SERVER_EMAIL = "tracksee.team@gmail.com";
    private static final String EMAIL_PASSWORD = "tracksee-spring";

    static {
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
    }

    public static Session getEmailSession() {
        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SERVER_EMAIL, EMAIL_PASSWORD);
            }
        });
    }
}
