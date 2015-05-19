package ua.com.tracksee.servlets.admin;

import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.facade.AdminFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Igor on 18.05.2015.
 */
@WebServlet("/admin/userdash")
public class UserDashboardServlet extends HttpServlet {
    @EJB
    private AdminFacade adminFacade;

    private static final String USER_ID_ADMIN_ALIAS = "userIdAdmin";
    private static final String USER_ID_ALIAS = "userId";
    private static final String IS_ADMIN = "isAdmin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(USER_ID_ADMIN_ALIAS));
        req.getSession().setAttribute(IS_ADMIN, req.getSession().getAttribute(USER_ID_ALIAS));
        req.getSession().setAttribute(USER_ID_ALIAS, id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = (Integer)req.getSession().getAttribute(IS_ADMIN);
        if (!(id == null)) {
            req.getSession().setAttribute(USER_ID_ALIAS, id);
            req.getSession().setAttribute(IS_ADMIN, null);
        }
    }
}