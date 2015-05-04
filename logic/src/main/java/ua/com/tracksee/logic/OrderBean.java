package ua.com.tracksee.logic;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class OrderBean {

    @EJB
    private TaxiOrderDAO taxiOrderDAO;

    public void createOrder() {

    }

    public List<TaxiOrderItemEntity> getItemEntities(Integer trackNumber) {
        return taxiOrderDAO.getOrderItems(trackNumber);
    }

    public TaxiOrderEntity getTaxiOrderEntity (Integer trackNumber) {
        return taxiOrderDAO.getOrder(trackNumber);
    }

    public void refuseOrder(Integer trackNumber) {
        taxiOrderDAO.refuseOrder(trackNumber);
    }

    public void updateComment(Integer trackNumber, String comment) {
        taxiOrderDAO.updateComment(trackNumber, comment);
    }
}
