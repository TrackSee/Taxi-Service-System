package ua.com.tracksee.logic;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import static ua.com.tracksee.enumartion.OrderStatus.QUEUED;

/**
 * Bean used for any order processing business logic.
 *
 * @author Ruslan Gunavardana
 */
@Stateless
@DeclareRoles({"customer", "unregistered"})
public class OrderBean {
    @EJB
    private TaxiOrderDAO taxiOrderDao;

    /**
     * Creates taxi order of authorised user.
     */
    @RolesAllowed("customer")
    public void createAuthorisedOrder(Integer userId) {
        TaxiOrderEntity order = new TaxiOrderEntity();
        order.setUserId(userId);
        order.setStatus(QUEUED);

    }


}
