package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.implementation.RefuseDAOBean;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Sasha Avlasov
 * @author Sharaban Sasha
 */
@Stateless(name = "OrderCancellationBeanEJB")
public class OrderRefusingBean {
    private static final Logger logger = LogManager.getLogger();

    private @EJB
    RefuseDAOBean canselDAO;
    private @EJB TaxiOrderDAO taxiOrderDAO;
    private @EJB UserDAO userDAO;

    public void refuseOrder(long trackingNumber) {
        canselDAO.refuseOrder(trackingNumber);
        int refusedTimes= canselDAO.getUserRefusedTimes(trackingNumber);
        if(refusedTimes>2){
            sendNotification(userDAO.getUserById(taxiOrderDAO.getOrder(trackingNumber).getUserId()));
        }
    }
/*
method send mail thet user refuse more then 2 order,
and cand make more order whith this email
 */
    private void sendNotification(UserEntity trackingNumber) {
        //TODO complete mail send
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.UserDAO
     */
    public boolean checkBlackListByUserEmail(String email){
        return userDAO.checkBlackListUserByEmail(email);
    }
}
