package ua.com.tracksee.servlets.accounts;

import ua.com.tracksee.exception.RegistrationException;
import ua.com.tracksee.logic.facade.CustomerFacade;

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
    private @EJB CustomerFacade customerFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            customerFacade.activateUser(req.getParameter("code"));
        } catch (RegistrationException e) {
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
            return;
        }
        req.getRequestDispatcher("/WEB-INF/accounts/activationSuccess.jsp").forward(req,resp);
    }
}
