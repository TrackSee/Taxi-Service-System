package ua.com.tracksee.servlets.admin;

import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vadym_Akymov on 21.04.15.
 */
@WebServlet("/admin/drivers")
public class AdminDriverModifyServlet extends HttpServlet {
    @EJB
    private AdministratorBean administratorBean;

    /**
     * Get admin page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/adminDriverModify.jsp").forward(req, resp);
    }
}
