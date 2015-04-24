package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiPriceEntity;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
public interface TaxiPriceDAO {
    /**
     *
     * @param priceEntity
     */
    public void updateTariff(TaxiPriceEntity priceEntity);
}
