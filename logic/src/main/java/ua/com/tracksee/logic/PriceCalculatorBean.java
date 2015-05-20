package ua.com.tracksee.logic;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class PriceCalculatorBean {

    private @EJB PriceListBean priceListBean;

    public BigDecimal calculatePrice(TaxiOrderEntity order) {
        BigDecimal servicePrice = getServicePrice(order);
        List<TaxiOrderItemEntity> orderItems = order.getItemList();
        BigDecimal distance = ZERO;
        BigDecimal minDistance = priceListBean.getMinimalOrderDistance();
        for (TaxiOrderItemEntity item : orderItems) {
            distance = distance.add(item.getOrderedQuantity());
        }
        distance = distance.compareTo(minDistance) > 1 ? distance : minDistance;

        return servicePrice.multiply(distance);
    }

    private BigDecimal getServicePrice(TaxiOrderEntity order) {
        return priceListBean.getPrice(order).getPricePerKm();
    }
}
