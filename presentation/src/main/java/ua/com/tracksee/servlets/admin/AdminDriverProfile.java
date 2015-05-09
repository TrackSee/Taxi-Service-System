package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vadym_Akymov on 25.04.15.
 */
@WebServlet("/admin/driver")
public class AdminDriverProfile extends HttpServlet{
    private static Logger logger = LogManager.getLogger();
    @EJB
    private AdministratorBean administratorBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        //get all free cars
        List<CarEntity> freeCars = administratorBean.getAllFreeCars();
        req.setAttribute("driver", administratorBean.getDriverByID(id));
        req.setAttribute("cars", freeCars);
        req.getRequestDispatcher("/WEB-INF/admin/adminDriverProfile.jsp").forward(req, resp);
    }

    /**
     * @author Vadym Akymov
     * Delete driver by id
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        administratorBean.deleteUser(id);
    }
}