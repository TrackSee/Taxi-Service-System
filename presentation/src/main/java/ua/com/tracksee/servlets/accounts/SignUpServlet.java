package ua.com.tracksee.servlets.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.UserEntity;
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
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private @EJB CustomerFacade customerFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/customer/signUp.jsp").forward(req, resp);
    }

    private static String nullIfIsEmpty(String s) {
        return s == null ? null
                         : s.isEmpty() ? null
                                       : s;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        UserEntity user = new UserEntity();
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setPhone(nullIfIsEmpty(req.getParameter("phone-number")));
        user.setFirstName(nullIfIsEmpty(req.getParameter("first-name")));
        user.setLastName(nullIfIsEmpty(req.getParameter("last-name")));

        try {
            customerFacade.registerUser(user);
            logger.info("Successful sign up. User: {}", user.getEmail());
            req.getRequestDispatcher("/WEB-INF/customer/checkEmail.jsp").forward(req, resp);
        } catch (RegistrationException e) {
            logger.warn(e.getMessage());
            resp.sendError(422, e.getErrorType());
        }
    }
}
