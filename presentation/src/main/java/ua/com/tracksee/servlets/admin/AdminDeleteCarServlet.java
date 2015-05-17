package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.admin.AdministratorBean;

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
    private AdministratorBean administratorBean;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try{
            String carNumber = req.getParameter("carNumber");
            administratorBean.deleteCar(carNumber);
        }
        catch (Exception e){
            req.getRequestDispatcher("errorDelete").forward(req, resp);
//            resp.setStatus(700);
//            logger.warn("can't delelte this car");
        }

    }


}
