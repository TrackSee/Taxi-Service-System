package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;

/**
 * Created by Sasha on 5/1/2015.
 */
@Stateless(name = "OrderCancellationBeanEJB")
public class OrderCancellationBean {
    private static final Logger logger = LogManager.getLogger();

    public boolean cancelOrder(long trackingNumber) {
        return false;
    }
}
