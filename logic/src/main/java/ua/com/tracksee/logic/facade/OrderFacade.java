package ua.com.tracksee.logic.facade;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.logic.TaxiOrderBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class OrderFacade {
    private @EJB TaxiOrderBean taxiOrderBean;

    public List<TaxiOrderEntity> getOrdersFor(int userId) {
        return null; //TODO
    }

    public void placeOrder(TaxiOrderEntity order, String email, String phone) {
        //taxiOrderBean.makeOrder(order, email, phone);
    }
}
