package ua.com.tracksee.servlets.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.exception.RegistrationException;
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
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private @EJB CustomerFacade customerFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "signUp");
        req.getRequestDispatcher("/WEB-INF/accounts/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phone-number");
        phoneNumber = phoneNumber.equals("") ? null : phoneNumber;

        try {
            customerFacade.registerUser(email, password, phoneNumber);
            logger.info("Successful sign up. User: {}", email);
            req.getRequestDispatcher("/WEB-INF/accounts/checkEmail.jsp").forward(req, resp);
        } catch (RegistrationException e) {
            logger.warn(e.getMessage());
            resp.getWriter().append(e.getErrorType());
        }
    }
}
