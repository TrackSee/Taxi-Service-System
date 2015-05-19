package ua.com.tracksee.servlets.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.facade.DriverFacade;

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
    int id;
    @EJB
    private DriverFacade driverFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        id = (int) req.getSession().getAttribute("userId");
        Integer userID = (Integer) req.getSession().getAttribute("userId");
        System.out.println("us id1" + userID);
        req.setAttribute("driver", driverFacade.getDriverByID(id));
        req.getRequestDispatcher("/WEB-INF/driver/driverProfile.jsp").forward(req,resp);
    }
}
