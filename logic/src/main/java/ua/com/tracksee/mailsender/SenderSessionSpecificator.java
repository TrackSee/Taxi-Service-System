package ua.com.tracksee.mailsender;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

/**
 * @author Igor Dvorskij
 */

class SenderSessionSpecificator {

    public static IEmailUtil GMAIL = new GmailSpecificator();


    private static class GmailSpecificator implements IEmailUtil {
        private static final String USER_NAME = "tracksee.mail@gmail.com";
        private static final String USER_PASSWORD = "service-taxi";

        private static final String MAIL_SMTP_AUTH_PROPERTY_NAME = "mail.smtp.auth";
        private static final String MAIL_SMTP_STARTTLS_ENABLE_PROPERTY_NAME = "mail.smtp.starttls.enable";
        private static final String MAIL_SMTP_HOST_PROPERTY_NAME = "mail.smtp.host";
        private static final String MAIL_SMTP_PORT_PROPERTY_NAME = "mail.smtp.port";

        private static final String MAIL_SMTP_AUTH_PROPERTY_VALUE = "true";
        private static final String MAIL_SMTP_STARTTLS_ENABLE_PROPERTY_VALUE = "true";
        private static final String MAIL_SMTP_HOST_PROPERTY_VALUE = "smtp.gmail.com";
        private static final String MAIL_SMTP_PORT_PROPERTY_VALUE = "587";

        GmailSpecificator() {
            properties = new Properties();
            properties.put(MAIL_SMTP_AUTH_PROPERTY_NAME, MAIL_SMTP_AUTH_PROPERTY_VALUE);
            properties.put(MAIL_SMTP_STARTTLS_ENABLE_PROPERTY_NAME, MAIL_SMTP_STARTTLS_ENABLE_PROPERTY_VALUE);
            properties.put(MAIL_SMTP_HOST_PROPERTY_NAME, MAIL_SMTP_HOST_PROPERTY_VALUE);
            properties.put(MAIL_SMTP_PORT_PROPERTY_NAME, MAIL_SMTP_PORT_PROPERTY_VALUE);

            session = Session.getInstance(properties,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USER_NAME, USER_PASSWORD);
                        }
                    });
        }

        @Override
        public Session getSession() {
            return session;
        }

        @Override
        public InternetAddress getInternetAddress() throws AddressException {
            return new InternetAddress(USER_NAME);
        }

        private Properties properties = null;
        private Session session = null;
    }
}
