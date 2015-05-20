package ua.com.tracksee.servlets.admin.reports;

import ua.com.tracksee.logic.reports.ReportChartBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 20.05.2015
 *
 * @author Oleksandr Kozin
 */
@WebServlet(name = "ServiceProfitReportServlet", urlPatterns = "/admin/report/most-profit")
public class ServiceProfitReportServlet extends HttpServlet {

    @EJB
    private ReportChartBean reportChartBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String startDate = request.getParameter("startDate");
        String count = reportChartBean.serviceProfitByMonth(startDate);
        response.getOutputStream().write(count.getBytes());
    }
}
