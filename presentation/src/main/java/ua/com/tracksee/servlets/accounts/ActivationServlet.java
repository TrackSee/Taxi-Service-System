package ua.com.tracksee.servlets.accounts;

import ua.com.tracksee.logic.RegistrationBean;
import ua.com.tracksee.logic.exception.RegistrationException;

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
        try {
            registrationBean.activateCustomerUserAccount(req.getParameter("code"));
        } catch (RegistrationException e) {
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("/WEB-INF/customer/activationSuccess.jsp").forward(req,resp);
    }
}
