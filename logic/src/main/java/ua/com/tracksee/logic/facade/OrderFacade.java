package ua.com.tracksee.logic.facade;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.logic.TaxiOrderBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class OrderFacade {
    private @EJB TaxiOrderBean taxiOrderBean;

    public void placeOrder(TaxiOrderEntity order) {
        //taxiOrderBean.makeOrder(order);
    }
}
