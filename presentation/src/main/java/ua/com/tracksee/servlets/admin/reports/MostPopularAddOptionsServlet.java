
package ua.com.tracksee.servlets.admin.reports;

/**
 * Created by kstes_000 on 06-May-15.
 */

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.MostPopularOption;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/report/mostpopularopt")
public class MostPopularAddOptionsServlet extends HttpServlet {
    @EJB
    private AdministratorBean administratorBean;

    @EJB
    private TaxiOrderBean taxiOrderBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ServiceUserEntity> users = administratorBean.getUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/report/popularOptions.jsp").forward(request, response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        List<MostPopularOption> listOptions = taxiOrderBean.getMostPopularOptionsForUser(userId);
        request.setAttribute("listOptions", listOptions);
        System.out.println("listOptions JSON" + getJsonFromList(listOptions));
        response.getWriter().write(getJsonFromList(listOptions));
    }

    private String getJsonFromList(List<MostPopularOption> listOptions) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(listOptions);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }
}
