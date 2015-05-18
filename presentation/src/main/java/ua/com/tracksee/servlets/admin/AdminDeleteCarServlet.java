package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.facade.AdminFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Katia Stetsiuk
 */
@WebServlet("/admin/deletecar")
public class AdminDeleteCarServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger();
    @EJB
    private AdminFacade adminFacade ;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("carNumber");
        if (carNumber == null) {
            logger.warn("carNumber can't be null");
            throw new IllegalArgumentException("CarNumber can't be null");
        }
        try {
            adminFacade.deleteCar(carNumber);
        } catch (Exception e) {
            System.out.println("SQL exception!!!");
            resp.setStatus(500);
        }
}

    }
