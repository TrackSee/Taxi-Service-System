package ua.com.tracksee.servlets.driver;

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
@WebServlet("/driver/driver-profile")
public class DriverProfileServlet extends HttpServlet {
    private @EJB DriverFacade driverFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("userId");
        req.setAttribute("driver", driverFacade.getDriverByID(id));
        req.getRequestDispatcher("/WEB-INF/driver/driverProfile.jsp").forward(req,resp);
    }
}
