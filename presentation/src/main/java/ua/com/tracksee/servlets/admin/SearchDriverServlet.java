package ua.com.tracksee.servlets.admin;

/**
 * @author Katia Stetsiuk
 */

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


@WebServlet("/admin/searchdriver")
public class SearchDriverServlet extends HttpServlet {
    private String drivers = "drivers";
    private String email= "email";

    @EJB
    private AdminFacade adminFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/admin/searchDriver.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String driverEmail = req.getParameter(email);
        req.setAttribute(email, driverEmail);
        List<UserEntity> driverList = adminFacade.getDriversByEmail(driverEmail);
        req.setAttribute(drivers, driverList);
        resp.getWriter().write(getJsonFromList(driverList));
    }

    /**
     *
     * @param driverList list of drivers to convert into JSON
     * @return String of JSON
     */
    private String getJsonFromList(List<UserEntity> driverList) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(driverList);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }


}

