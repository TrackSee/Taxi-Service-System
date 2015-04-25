package ua.com.tracksee.mailsender;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 17.04.15
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */

import javax.mail.*;
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
