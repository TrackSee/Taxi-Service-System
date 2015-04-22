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
@WebServlet("/admin")
public class HomeAdminServlet extends HttpServlet {
    @EJB
    private AdministratorBean administratorBean;

    /**
     * Get admin page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ServiceUserEntity> list = administratorBean.getDrivers(1);
        for(int i = 0; i  < list.size(); i ++) {
            resp.getOutputStream().print(list.get(i).toString());
        }
    }
}
