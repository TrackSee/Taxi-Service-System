package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.RefuseDAO;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;

/**
 * @author Sasha Avlasov
 * @author Sharaban Sasha
 */
@Stateless
public class OrderRefusingBean {
    private static final Logger logger = LogManager.getLogger();
    private static final int IGNORED_TIMES=2;
    private @EJB
    RefuseDAO refuseDAO;
    private @EJB TaxiOrderDAO taxiOrderDAO;
    private @EJB UserDAO userDAO;
    private @EJB EmailBean mailBean;

    public void refuseOrder(long trackingNumber) {
        refuseDAO.refuseOrder(trackingNumber);
        int refusedTimes= refuseDAO.getUserRefusedTimes(trackingNumber);
        if(refusedTimes>IGNORED_TIMES){
            sendEmail(taxiOrderDAO.getOrder(trackingNumber).getUser(), trackingNumber);
        }
    }
    /**
     * Sends confirmation letter with
     * tracking number to the user who made the order
     *
     * @author Sharaban Sasha
     * @author Avlasov Sasha
     * @param userEntity- the user who made the order
     * @param trackingNumber-    tracking number of made order
     * @throws javax.mail.MessagingException
     */
    public void sendEmail(UserEntity userEntity, Long trackingNumber) {
        try {
            mailBean.sendBlockingUserEmail(userEntity);
        } catch (MessagingException e) {
            logger.warn("Fail sending blocking email to user : "+userEntity.getEmail());
        }
    }

    /**
     * @author Sharaban Sasha
     * @see ua.com.tracksee.dao.UserDAO
     */
    public boolean checkBlackListByUserEmail(String email){
        return userDAO.checkBlackListUserByEmail(email);
    }
}
