package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiPriceEntity;

import javax.ejb.Local;

/**
 * @author Katia Stetsiuk
 */
@Local
public interface TaxiPriceDAO {
    /**
     *
     * @param priceEntity
     */
    void updateTariff(TaxiPriceEntity priceEntity);
}
