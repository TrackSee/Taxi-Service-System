package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.exceptions.ServiceUserNotFoundException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vadym_Akymov on 25.04.15.
 */
@WebServlet("/admin/driver")
public class AdminDriverProfile extends HttpServlet{
    private static Logger logger = LogManager.getLogger();
    @EJB
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("driver", userDAO.getDriverByID(id));
        req.getRequestDispatcher("/WEB-INF/admin/adminDriverProfile.jsp").forward(req, resp);
    }

    /**
     * @author Vadym Akymov
     * Delete driver by id
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);
    }
}
