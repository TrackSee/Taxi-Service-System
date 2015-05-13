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
 * @author Katia Stetsiuk
 */
@WebServlet("/admin/deletecar")
public class AdminDeleteCarServlet extends HttpServlet {
    private static String carNumber = "carNumber";
    @EJB
    private AdministratorBean administratorBean;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumb = req.getParameter(carNumber);
        administratorBean.deleteCar(carNumb);
        resp.sendRedirect("cars");
    }
}
