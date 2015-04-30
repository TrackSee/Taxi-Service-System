package ua.com.tracksee.logic;

import ua.com.tracksee.dao.TaxiOrderDAO;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
//        TaxiOrderEntity order = new TaxiOrderEntity();
//        order.setUserId(userId);
//        order.setStatus(QUEUED);

    }


}
