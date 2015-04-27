package ua.com.tracksee.logic.services;

import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.logic.ServiceBean;

import javax.ejb.Stateless;
import java.math.BigDecimal;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class SoberDriverServiceBean implements ServiceBean {

    @Override
    public BigDecimal getPrice(TaxiOrderEntity order) {
        return null;
    }
}
