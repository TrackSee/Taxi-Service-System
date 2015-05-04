package ua.com.tracksee.report;

import ua.com.tracksee.dao.TaxiOrderDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created on 02.05.2015
 *
 * @author Oleksandr Kozin
 */
@Stateless
public class TOReportBean {

//    New orders per period
//    Service profitability by month

    @EJB
    TaxiOrderDAO taxiOrderDAO;

    /**
     * @param startDate start date of the period
     * @param endDate end date of the period
     * @return number of orders per period
     */
    public int getOrdersByPeriod(String startDate, String endDate){
        return taxiOrderDAO.getOrdersByPeriod(startDate, endDate);
    }


}
