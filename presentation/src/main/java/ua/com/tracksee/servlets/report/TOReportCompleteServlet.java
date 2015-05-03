package ua.com.tracksee.servlets.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.report.TOReportBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 02.05.2015
 *
 * @author Oleksandr Kozin
 */
@WebServlet(urlPatterns = ("/report"), name = "TOReportCompleteServlet")
public class TOReportCompleteServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @EJB
    private TOReportBean toReportBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("pageName", "report");
        request.getRequestDispatcher("/WEB-INF/report/reportComplete.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String dateFrom = "2015-04-26";
//        String dateTo = "2015-04-29";

        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");

        try{
            int count = toReportBean.getOrdersByPeriod(dateFrom, dateTo);

            request.setAttribute("count", count);
            request.getRequestDispatcher("/WEB-INF/report/reportCount.jsp").forward(request, response);
        } catch (Exception e){
            logger.error(e.getMessage());
            request.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(request,response);
        }
    }
}
