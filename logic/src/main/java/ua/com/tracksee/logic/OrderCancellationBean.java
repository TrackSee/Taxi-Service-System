package ua.com.tracksee.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.CanselDAOBean;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;

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
        canselDAO.canselOrder(trackingNumber);
        return false;
    }
}
