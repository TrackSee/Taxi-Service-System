package ua.com.tracksee.logic.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Vadym Akymov on 09.05.15.
 * Session Facade for customer bean
 */
@Stateless
public class CustomerFacade {
    private static final Logger logger = LogManager.getLogger();
    private @EJB TaxiOrderDAO taxiOrderDAO;

    public List<TaxiOrderEntity> getOrdersPerPage(OrderStatusBO orderStatus, int userID, int pageNumber){
        switch (orderStatus){
            case ACTIVE: return taxiOrderDAO.getCustomerActiveOrdersPerPage(userID, pageNumber);
            case COMPLETED: return taxiOrderDAO.getCustomerOldOrdersPerPage(userID, pageNumber);
            default:
                logger.warn("wrong order status param");
                throw new IllegalArgumentException("wrong order status param");
        }
    }
}
