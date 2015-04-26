package ua.com.tracksee.servlets.customer;

import ua.com.tracksee.logic.RegistrationBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ruslan Gunavardana
 */
@WebServlet("/activation")
public class ActivationServlet extends HttpServlet {
    @EJB
    private RegistrationBean registrationBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/customer/activationSuccess.jsp").forward(req,resp);
    }
}
