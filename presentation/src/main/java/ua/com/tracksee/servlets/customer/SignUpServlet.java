package ua.com.tracksee.servlets.customer;

import ua.com.tracksee.logic.RegistrationBean;
import ua.com.tracksee.logic.exception.RegistrationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Ruslan Gunavardana
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_REDIRECT_PATH = "/signup?error=true&code=";

    @EJB
    private RegistrationBean controller;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/customer/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phoneNumber = req.getParameter("phone-number");

            controller.registerCustomerUser(email, password, phoneNumber);
            logger.debug("Successful sign up. User: " + email);
            req.getRequestDispatcher("/WEB-INF/customer/checkEmail.jsp").forward(req, resp);
        } catch (RegistrationException e) {
            logger.warn(e.getMessage());
            resp.getWriter().append(e.getErrorType());
        }
    }
}
