package ua.com.tracksee.servlets.report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 03.05.2015
 *
 * @author Oleksandr Kozin
 */
@WebServlet(urlPatterns = ("/reportCount"), name = "TOReportCountServlet")
public class TOReportCountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageName", "reportCount");
        request.getRequestDispatcher("/WEB-INF/report/reportCount.jsp").forward(request,response);
    }
}
