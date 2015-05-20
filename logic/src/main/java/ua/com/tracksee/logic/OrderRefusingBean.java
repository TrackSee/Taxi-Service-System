package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.RefuseDAO;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Sasha Avlasov
 * @author Sharaban Sasha
 */
@Stateless
public class OrderRefusingBean {
    private static final Logger logger = LogManager.getLogger();

    private @EJB
    RefuseDAO refuseDAO;
    private @EJB TaxiOrderDAO taxiOrderDAO;
    private @EJB UserDAO userDAO;

    public void refuseOrder(long trackingNumber) {
        refuseDAO.refuseOrder(trackingNumber);
        int refusedTimes= refuseDAO.getUserRefusedTimes(trackingNumber);
        if(refusedTimes>2){
            sendNotification(userDAO.getUserById(taxiOrderDAO.getOrder(trackingNumber).getUserId()));
        }
    }
/*
method send mail that user refuse more then 2 order,
and cant make more order with this email.
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
