package ua.com.tracksee.logic;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;
import ua.com.tracksee.logic.facade.OrderFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class PriceCalculatorBean {

    private @EJB PriceListBean priceListBean;

    public BigDecimal calculatePrice(TaxiOrderEntity order) {
        BigDecimal servicePrice = getServicePrice(order);
        BigDecimal distance = getBusinessDistance(order);
        BigDecimal optionsMultiplier = getAdditionalOptionsMultiplier(order);
        return servicePrice.multiply(distance).multiply(optionsMultiplier);
    }

    private BigDecimal getAdditionalOptionsMultiplier(TaxiOrderEntity order) {
        BigDecimal multiplier = ONE;
        if (order.getAnimalTransportation()) {
            multiplier = ONE.multiply(priceListBean.getAnimalTransportationMultiplier());
        }
        return multiplier;
    }

    private BigDecimal getBusinessDistance(TaxiOrderEntity order) {
        List<TaxiOrderItemEntity> orderItems = order.getItemList();
        BigDecimal distance = ZERO;
        BigDecimal minDistance = priceListBean.getMinimalOrderDistance();
        for (TaxiOrderItemEntity item : orderItems) {
            distance = distance.add(item.getOrderedQuantity());
        }
        return distance.compareTo(minDistance) > 0 ? distance : minDistance;
    }

    private BigDecimal getServicePrice(TaxiOrderEntity order) {
        return priceListBean.getPrice(order).getPricePerKm();
    }
}
