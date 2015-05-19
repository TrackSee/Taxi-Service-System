package ua.com.tracksee.dao;

import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

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
    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return map with sum of prices grouped by service for all orders
     */
    Map<String, Double> serviceProfitByMonth(String year, String month);

    /**
     * @author Vitalii Diravka
     */
    List<TaxiPriceEntity> getAllPrices();
    void updatePricePerKm(TaxiPriceEntity newPriceEntity);
    void updatePricePerMin(TaxiPriceEntity newPriceEntity);

    /**
     * Returns list of prices
     * ordered by carCategory.
     *
     * @author Sharaban Sasha
     * @return all prices order by car category
     */
    List<TaxiPriceEntity> getPricesOrderByCarCategory();
}