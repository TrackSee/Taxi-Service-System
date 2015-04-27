package ua.com.tracksee.enumartion;

import ua.com.tracksee.entities.TaxiOrderEntity;

import java.math.BigDecimal;

/**
 * @author Ruslan Gunavardana
 */
public enum Service {
    SIMPLE_TAXI,
    SOBER_DRIVER,
    CONVEY_CORPORATION_EMPLOYEES,
    GUEST_DELIVERY,
    CARGO_TAXI,
    MEET_MY_GUEST,
    CELEBRATION_TAXI,
    FOODSTUFF_DELIVERY;

    public BigDecimal getPrice(TaxiOrderEntity order) {
        return new BigDecimal(0);
    }
}
