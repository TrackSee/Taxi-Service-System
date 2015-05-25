package ua.com.tracksee.config;

import ua.com.tracksee.dao.ConfigDAO;
import ua.com.tracksee.entities.ConfigEntity;
import ua.com.tracksee.exception.ConfigException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.math.BigDecimal;

import static java.lang.Double.parseDouble;
import static ua.com.tracksee.config.ConfigName.MINIMAL_ORDER_DISTANCE;
import static ua.com.tracksee.config.ConfigName.ANIMAL_TRANSPORTATION_MULTIPLIER;

/**
 * Used to guarantee fast access to configs
 * @author Ruslan Gunavardana
 */
@Stateless
public class ConfigManagerBean {
    private @EJB ConfigDAO configDao;

    public double getMinimalOrderDistance() {
        String string = configDao.getConfig(MINIMAL_ORDER_DISTANCE).getValue();
        try {
            return parseDouble(string);
        } catch (NumberFormatException | NullPointerException e) {
            throw new ConfigException("MINIMAL_ORDER_DISTANCE configured in the wrong way");
        }
    }

    public void setMinimalOrderDistance(double minimalOrderDistance) {
       configDao.saveConfig(new ConfigEntity(MINIMAL_ORDER_DISTANCE, Double.toString(minimalOrderDistance)));
    }

    public BigDecimal getAnimalTransportationMultiplier() {
        String string = configDao.getConfig(ANIMAL_TRANSPORTATION_MULTIPLIER).getValue();
        try {
            return new BigDecimal(string);
        } catch (NumberFormatException | NullPointerException e) {
            throw new ConfigException("MINIMAL_ORDER_DISTANCE configured in the wrong way");
        }
    }
}
