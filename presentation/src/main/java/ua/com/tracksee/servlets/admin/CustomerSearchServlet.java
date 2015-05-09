package ua.com.tracksee.servlets.admin;

import org.apache.log4j.LogManager;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
* @author Katia Stetsiuk
*/
@WebServlet("/admin/customerbysearch")
public class CustomerSearchServlet extends HttpServlet {
    private static final  String users = "users";
    private static final String email= "email";

    @EJB
    private AdministratorBean administratorBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.getRequestDispatcher("/WEB-INF/admin/customerbysearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getParameter(email);
        req.setAttribute(email, userEmail);
        List<ServiceUserEntity> userList = administratorBean.getCustomersByEmail(userEmail);
        req.setAttribute(users, userList);
        resp.getWriter().write(getJsonFromList(userList));
    }

    /**
     *
     * @param userList list of drivers to convert into JSON
     * @return String of JSON
     */
    private String getJsonFromList(List<ServiceUserEntity> userList) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(userList);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }

}
