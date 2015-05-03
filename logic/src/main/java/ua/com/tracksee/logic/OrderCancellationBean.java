package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.CanselDAOBean;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Sasha on 5/1/2015.
 */
@Stateless(name = "OrderCancellationBeanEJB")
public class OrderCancellationBean {
    private static final Logger logger = LogManager.getLogger();
    private
    @EJB
    TaxiOrderDAO taxiOrderDAO;
    private
    @EJB
    UserDAO userDAO;
    @EJB
    CanselDAOBean canselDAO;
    public boolean cancelOrder(long trackingNumber) {
        //TODO check is exist order first
        canselDAO.canselOrder(trackingNumber);
        int refusedTimes= canselDAO.getUserRefusedTimes(trackingNumber);
        if(refusedTimes>2){
            sendNotification(userDAO.getUserById(taxiOrderDAO.getOrder(trackingNumber).getUserId()));
        }
        return true;
    }
/*
method send mail thet user refuse more then 2 order,
and cand make more order whith this email
 */
    private void sendNotification(ServiceUserEntity trackingNumber) {
        //TODO complete mail send
    }
}
