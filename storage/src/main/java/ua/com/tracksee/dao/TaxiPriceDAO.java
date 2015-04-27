package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiPriceEntity;

import javax.ejb.Local;

/**
 * @author kstes_000
 */
@Local
public interface TaxiPriceDAO {
    /**
     *
     * @param priceEntity
     */
    public void updateTariff(TaxiPriceEntity priceEntity);
}
