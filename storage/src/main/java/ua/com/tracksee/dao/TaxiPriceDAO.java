package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;

import javax.ejb.Local;

/**
 * @author Ruslan Gunavardana
 * @author Katia Stetsiuk
 */
@Local
public interface TaxiPriceDAO {

    /**
     * Returns a Taxi Price entity for the specified parameters.
     *
     * @param category
     * @param weekend
     * @param nightTariff
     * @return
     */
    TaxiPriceEntity getPriceFor(CarCategory category, boolean weekend, boolean nightTariff);

    /**
     *
     * @param priceEntity
     */
    void updateTariff(TaxiPriceEntity priceEntity);
}
