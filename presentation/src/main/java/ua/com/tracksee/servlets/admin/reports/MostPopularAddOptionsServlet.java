
package ua.com.tracksee.servlets.admin.reports;

/**
 * Created by kstes_000 on 06-May-15.
 */

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.MostPopularOption;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.admin.AdministratorBean;
import ua.com.tracksee.logic.reports.ReportChartBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin/report/mostpopularopt")
public class MostPopularAddOptionsServlet extends HttpServlet {
    private String users = "users";
    private String idUser = "userId";
    private String optionList = "listOptions";
    @EJB
    private AdministratorBean administratorBean;

    @EJB
    private ReportChartBean reportChartBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserEntity> userList = administratorBean.getUsers();
        request.setAttribute(users, userList);
        request.getRequestDispatcher("/WEB-INF/report/popularOptions.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.parseInt(request.getParameter(idUser));
        List<MostPopularOption> listOptions = reportChartBean.getMostPopularOptionsForUser(userId);
        request.setAttribute(optionList, listOptions);
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
