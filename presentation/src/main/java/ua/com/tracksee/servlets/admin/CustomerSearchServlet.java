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
* Created by kstes_000 on 07-May-15.
*/
@WebServlet("/admin/customerbysearch")
public class CustomerSearchServlet extends HttpServlet {


    @EJB
    private AdministratorBean administratorBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dr = req.getParameter("users");
        req.getRequestDispatcher("/WEB-INF/admin/customerbysearch.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println("EMAIL2" + email);
        req.setAttribute("email", email);
        List<ServiceUserEntity> users = administratorBean.getCustomersByEmail(email);
        req.setAttribute("users", users);
        resp.getWriter().write(getJsonFromList(users));
    }

    private String getJsonFromList(List<ServiceUserEntity> users) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(users);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }

}
