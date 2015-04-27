package ua.com.tracksee.logic;

import ua.com.tracksee.entities.TaxiOrderEntity;

import java.math.BigDecimal;

/**
 * Bean functionality is used to calculate price
 * for different types of service.
 *
 * @author Ruslan Gunavardana
 */
public interface ServiceBean {
    BigDecimal getPrice(TaxiOrderEntity order);
}
