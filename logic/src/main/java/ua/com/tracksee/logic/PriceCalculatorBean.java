package ua.com.tracksee.logic;

import ua.com.tracksee.enumartion.Service;
import javax.ejb.Stateless;
import java.math.BigDecimal;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class PriceCalculatorBean {

    public BigDecimal calculatePrice(Service service) {
        return new BigDecimal(0);
    }
}
