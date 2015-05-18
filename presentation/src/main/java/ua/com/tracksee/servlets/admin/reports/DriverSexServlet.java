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
 * Created by Vadym Akymov on 18.05.15.
 */
@WebServlet(urlPatterns = "/admin/report/driver-sex")
public class DriverSexServlet extends HttpServlet {
    private @EJB ReportChartBean reportChartBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().write(reportChartBean.getDriverSexReport().getBytes());
    }
}
