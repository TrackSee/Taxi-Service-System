package ua.com.tracksee.servlets.admin;

/**
 * Created by kstes_000 on 03-May-15.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.dao.postrgresql.UserDAOBean;
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

    /*
     * Get admin page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IN GET");

        String dr =req.getParameter("drivers");
        System.out.println("DR:  " + dr);
//        System.out.println("json in get: " + getJsonFromList(drivers));
        req.getRequestDispatcher("/WEB-INF/admin/driverbysearch.jsp").forward(req, resp);

//        String email = (String) req.getAttribute("email");
//        System.out.println("enaaaaail in 2 "  + email);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IN post");
        String email = req.getParameter("email");
        req.setAttribute("email", email);
        System.out.println("enaaaaail "  + email);
        List<ServiceUserEntity> drivers = administratorBean.getDriverByEmail(email);
        for(ServiceUserEntity e : drivers){
            System.out.println("em: " + e.getEmail());
        }
        req.setAttribute("drivers", drivers);
//        System.out.println("json in post: " + getJsonFromList(drivers));

        resp.getWriter().write(getJsonFromList(drivers));
    }
    private String getJsonFromList(List<ServiceUserEntity> drivers){
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

