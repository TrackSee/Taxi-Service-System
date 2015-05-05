package ua.com.tracksee.report;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.TaxiPriceDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Map;

/**
 * Created on 02.05.2015
 *
 * @author Oleksandr Kozin
 */
@Stateless
public class TOReportBean {

    @EJB
    private TaxiOrderDAO taxiOrderDAO;
    @EJB
    private TaxiPriceDAO taxiPriceDAO;

    /**
     * @param startDate start date of the period
     * @param endDate end date of the period
     * @return number of orders per period
     */
    public int getOrdersByPeriod(String startDate, String endDate){
        return taxiOrderDAO.getOrdersByPeriod(startDate, endDate);
    }

    /**
     * @param year year of report
     * @param month month of report
     * @return map with sum of prices grouped by service for all orders
     */
    public Map<String, Double> serviceProfitByMonth(String year, String month) {
        return taxiPriceDAO.serviceProfitByMonth(year, month);
    }

    /**
     * @return map with most popular additional car options overall
     */
    public Map<String, Integer> mostPopularAdditionalCarOptOverall() {
        return  taxiOrderDAO.mostPopularAdditionalCarOptOverall();
    }
}
