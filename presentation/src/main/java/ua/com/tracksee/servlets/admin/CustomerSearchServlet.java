package ua.com.tracksee.servlets.admin;

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.facade.AdminFacade;

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
@WebServlet("/admin/customerbysearch")
public class CustomerSearchServlet extends HttpServlet {
    private static final  String users = "users";
    private static final String email= "email";

    @EJB
    private AdminFacade adminFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.getRequestDispatcher("/WEB-INF/admin/customerbysearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getParameter(email);
        req.setAttribute(email, userEmail);
        List<UserEntity> userList = adminFacade.getCustomersByEmail(userEmail);
        req.setAttribute(users, userList);
        resp.getWriter().write(getJsonFromList(userList));
    }

    /**
     *
     * @param userList list of drivers to convert into JSON
     * @return String of JSON
     */
    private String getJsonFromList(List<UserEntity> userList) {
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
