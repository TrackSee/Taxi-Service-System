package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.CancelDAOBean;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Sasha Avlasov
 * @author Sharaban Sasha
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
    CancelDAOBean canselDAO;
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

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.AddressDAO
     */
    public boolean checkBlackListByUserEmail(String email){
        boolean blackListPresent=false;
        int ignoredTimes=userDAO.checkBlackListUserByEmail(email);
        if(ignoredTimes>3){
        blackListPresent=true;
        }
        return blackListPresent;
    }
}
