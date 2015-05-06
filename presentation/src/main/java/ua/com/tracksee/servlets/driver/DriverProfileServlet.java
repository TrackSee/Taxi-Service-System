package ua.com.tracksee.servlets.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vadym Akymov on 15.04.15.
 */
@WebServlet("driver/driver-profile")
//hello kitty
public class DriverProfileServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @EJB
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 6;
        req.setAttribute("driver", userDAO.getDriverByID(id));
        req.getRequestDispatcher("/WEB-INF/driver/driverProfile.jsp").forward(req,resp);
    }
}
