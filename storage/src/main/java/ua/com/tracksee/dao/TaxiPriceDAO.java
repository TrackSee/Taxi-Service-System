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

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the "Simple Taxi" service
     */
    int profitByMonthSimpleTaxi(String year, String month);

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the "Sober Driver" service
     */
    int profitByMonthSoberDriver(String year, String month);

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the Convey "Corporation Employees" service
     */
    int profitByMonthConveyCorpEmp(String year, String month);

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the "Guest Delivery" service
     */
    int profitByMonthGuestDelivery(String year, String month);

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the "Cargo Taxi" service
     */
    int profitByMonthCargoTaxi(String year, String month);

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the "Taxi For Long Term" service
     */
    int profitByMonthTaxiForLongTerm(String year, String month);

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the "Meet My Guest" service
     */
    int profitByMonthMeetMuGuest(String year, String month);

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the "Celebration Taxi" service
     */
    int profitByMonthCelebrationTaxi(String year, String month);

    /**
     * @author Oleksandr Kozin
     * @param year year of report
     * @param month month of report
     * @return sum of prices for all the orders for the "Foodstuff Delivery" service
     */
    int profitByMonthFoodstuffDelivery(String year, String month);


}
