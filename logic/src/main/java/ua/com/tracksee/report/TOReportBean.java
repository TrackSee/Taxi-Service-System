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
     *
     * @param dateFrom -
     * @param dateTo -
     * @return -
     */
    public int getOrdersByPeriod(String dateFrom, String dateTo){
        return taxiOrderDAO.getOrdersByPeriod(dateFrom, dateTo);
    }


}
