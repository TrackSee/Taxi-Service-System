package ua.com.tracksee.servlets.admin.reports;

import ua.com.tracksee.entities.UserEntity;
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
 * Created on 18.05.2015
 *
 * @author Oleksandr Kozin
 */
@WebServlet(name = "AllReportsServlet", urlPatterns = "/admin/report/all")
public class AllReportsServlet extends HttpServlet {

    @EJB
    private AdministratorBean administratorBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<UserEntity> userList = administratorBean.getUsers();
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/WEB-INF/admin/allReports.jsp").forward(request, response);
    }
}