package ua.com.tracksee.servlets.admin.reports;

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.logic.TaxiOrderBean;
import ua.com.tracksee.logic.facade.ReportFacade;
import ua.com.tracksee.logic.reports.ReportChartBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Katia Stetsiuk
 */
@WebServlet("/admin/report/profitableservice")
public class MostProfitableServiceServlet extends HttpServlet {

    private String dateStart = "startDate";
    private String dateEnd = "endDate";
    private String listProfit = "listProfit";

    @EJB
    private ReportFacade reportFacade;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/report/mostProfitableService.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter(dateStart);
        String endDate = request.getParameter(dateEnd);
        List<ServiceProfitable>  listServiceProfit = reportFacade.getProfitByService(startDate, endDate);
        request.setAttribute(listProfit, listServiceProfit);
        response.getWriter().write(getJsonFromList(listServiceProfit));
    }

    /**
     *
     * @param listServiceProfit list ServiceProfitable objects
     * @return json from list of object
     */
    private String getJsonFromList(List<ServiceProfitable> listServiceProfit){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(listServiceProfit);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }
}
