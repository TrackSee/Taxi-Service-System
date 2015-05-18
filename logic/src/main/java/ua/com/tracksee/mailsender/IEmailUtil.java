package ua.com.tracksee.mailsender;

/**
 * @author Igor Dvorskij
 */

import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

interface IEmailUtil {

    /**
     * this method gets session for secure seance via TLS/SSL
     *
     * @return properties
     */
    Session getSession();

    /**
     * this method gets InternetAddress gotten from username
     *
     * @return properties
     */
    InternetAddress getInternetAddress() throws AddressException;
}
