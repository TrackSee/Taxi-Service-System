package ua.com.tracksee.servlets.admin;

import ua.com.tracksee.dao.UserDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vadym_Akymov on 25.04.15.
 */
@WebServlet("/admin/driver")
public class AdminDriverProfile extends HttpServlet{
    @EJB
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("driver", userDAO.getDriverByID(id));
        req.getRequestDispatcher("/WEB-INF/admin/adminDriverProfile.jsp").forward(req, resp);
    }
}
