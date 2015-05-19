package ua.com.tracksee.servlets.admin.reports;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vadym Akymov on 04.05.15.
 */
@WebServlet(name = "CarAndDriverReportServlet", urlPatterns = "/admin/report/driver-car")
public class CarAndDriverReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/WEB-INF/admin/reports/carAndDriverReport.jsp").forward(request, response);
    }
}