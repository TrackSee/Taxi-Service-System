package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;
import ua.com.tracksee.logic.facade.ReportFacade;
import ua.com.tracksee.servlets.PageAddresses;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/tariffsCarCategory")
public class TariffsCarCategoryServlet extends HttpServlet implements PageAddresses{
private static final Logger logger = LogManager.getLogger();
    private static final int MIN_DISTANCE=5;
    private static final int MINUTES_IN_HOUR=60;
    private @EJB
    ReportFacade reportFacade;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getParameter("carCategory").equals("economyClass")){
                List<TaxiPriceEntity> taxiPriceEntityList=reportFacade.getPricesByCarCategory(CarCategory.ECONOMY_CLASS);
                setData(req,resp,taxiPriceEntityList);
            }else if(req.getParameter("carCategory").equals("businessClass")){
                List<TaxiPriceEntity> taxiPriceEntityList=reportFacade.getPricesByCarCategory(CarCategory.BUSINESS_CLASS);
                setData(req,resp,taxiPriceEntityList);
            } else  if(req.getParameter("carCategory").equals("van")) {
                List<TaxiPriceEntity> taxiPriceEntityList = reportFacade.getPricesByCarCategory(CarCategory.VAN);
                setData(req,resp,taxiPriceEntityList);
            }

            req.getRequestDispatcher(TARIFFS_PAGE).forward(req, resp);
        }catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
    private void setData(HttpServletRequest req, HttpServletResponse resp, List<TaxiPriceEntity> taxiPriceEntityList){
        for (int i = 0; i < taxiPriceEntityList.size() ; i++) {
            if(taxiPriceEntityList.get(i).getNightTariff()&&!taxiPriceEntityList.get(i).getWeekend()){
                BigDecimal pricePerMinBigDecimal=taxiPriceEntityList.get(i).getPricePerMin();
                long pricePerMin=pricePerMinBigDecimal.longValue();
                BigDecimal pricePerKmBigDecimal=taxiPriceEntityList.get(i).getPricePerKm();
                long pricePerKm=pricePerKmBigDecimal.longValue();

                req.setAttribute("pricePerKmNight",pricePerKm);
                req.setAttribute("minPriceNight",pricePerKm*MIN_DISTANCE);
                req.setAttribute("pricePerMinNight",pricePerMin);
                req.setAttribute("pricePerHourNight",pricePerMin*MINUTES_IN_HOUR);
            }else
            if(taxiPriceEntityList.get(i).getWeekend()&&!taxiPriceEntityList.get(i).getNightTariff()){
                BigDecimal pricePerMinBigDecimal=taxiPriceEntityList.get(i).getPricePerMin();
                long pricePerMin=pricePerMinBigDecimal.longValue();
                BigDecimal pricePerKmBigDecimal=taxiPriceEntityList.get(i).getPricePerKm();
                long pricePerKm=pricePerKmBigDecimal.longValue();

                req.setAttribute("pricePerKmWeekend",pricePerKm);
                req.setAttribute("minPriceWeekend",pricePerKm*MIN_DISTANCE);
                req.setAttribute("pricePerMinWeekend",pricePerMin);
                req.setAttribute("pricePerHourWeekend",pricePerMin*MINUTES_IN_HOUR);

            }else
            if(!taxiPriceEntityList.get(i).getWeekend()&&!taxiPriceEntityList.get(i).getNightTariff()){
                BigDecimal pricePerMinBigDecimal=taxiPriceEntityList.get(i).getPricePerMin();
                long pricePerMin=pricePerMinBigDecimal.longValue();
                BigDecimal pricePerKmBigDecimal=taxiPriceEntityList.get(i).getPricePerKm();
                long pricePerKm=pricePerKmBigDecimal.longValue();

                req.setAttribute("pricePerKmNone",pricePerKm);
                req.setAttribute("minPriceNone",pricePerKm*MIN_DISTANCE);
                req.setAttribute("pricePerMinNone",pricePerMin);
                req.setAttribute("pricePerHourNone",pricePerMin*MINUTES_IN_HOUR);
            }else if(taxiPriceEntityList.get(i).getNightTariff()&&taxiPriceEntityList.get(i).getWeekend()){
                BigDecimal pricePerMinBigDecimal=taxiPriceEntityList.get(i).getPricePerMin();
                long pricePerMin=pricePerMinBigDecimal.longValue();
                BigDecimal pricePerKmBigDecimal=taxiPriceEntityList.get(i).getPricePerKm();
                long pricePerKm=pricePerKmBigDecimal.longValue();

                req.setAttribute("pricePerKmNightWeekend",pricePerKm);
                req.setAttribute("minPriceNightWeekend",pricePerKm*MIN_DISTANCE);
                req.setAttribute("pricePerMinNightWeekend",pricePerMin);
                req.setAttribute("pricePerHourNightWeekend",pricePerMin*MINUTES_IN_HOUR);
            }
        }
    }
}
