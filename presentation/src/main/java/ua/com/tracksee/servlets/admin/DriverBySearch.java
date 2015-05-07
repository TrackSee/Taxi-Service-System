package ua.com.tracksee.servlets.admin;

/**
 * Created by kstes_000 on 03-May-15.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


@WebServlet("/admin/driverbysearch")
public class DriverBySearch extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @EJB
    private AdministratorBean administratorBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dr = req.getParameter("drivers");
        req.getRequestDispatcher("/WEB-INF/admin/driverbysearch.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        req.setAttribute("email", email);
        List<ServiceUserEntity> drivers = administratorBean.getDriverByEmail(email);
        req.setAttribute("drivers", drivers);
        resp.getWriter().write(getJsonFromList(drivers));
    }

    private String getJsonFromList(List<ServiceUserEntity> drivers) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(drivers);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }


}

