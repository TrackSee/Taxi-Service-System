package ua.com.tracksee.servlets.admin;

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

/**
 * Created by Vadym_Akymov on 21.04.15.
 */
@WebServlet("/admin/drivers")
public class AdminDriverModifyServlet extends HttpServlet {
    @EJB
    private AdministratorBean administratorBean;

    /*c
     * Get admin page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ServiceUserEntity> drivers = administratorBean.getDrivers(1);
        drivers.addAll(administratorBean.getDrivers(2));
        drivers.addAll(administratorBean.getDrivers(3));
        req.setAttribute("drivers", drivers);
        req.getRequestDispatcher("/WEB-INF/admin/adminDriverModify.jsp").forward(req, resp);
    }
}
