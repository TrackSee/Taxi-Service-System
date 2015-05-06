package ua.com.tracksee.servlets.admin.reports;

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.dao.postrgresql.TaxiOrderDAOBean;
import ua.com.tracksee.entities.ServiceProfitable;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.logic.TaxiOrderBean;
import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kstes_000 on 05-May-15.
 */

@WebServlet("/admin/report/profitdiagram")
public class GetChartServlet extends HttpServlet {
    @EJB
    private TaxiOrderBean taxiOrderBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/report/profitDiagram.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
