package ua.com.tracksee.servlets.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.facade.CustomerFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.tracksee.servlets.AttributeNames.USER_EMAIL;
import static ua.com.tracksee.servlets.AttributeNames.USER_ID;

/**
 * @author Ruslan Gunavardana
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private static final int SESSION_MAX_INACTIVE_INTERVAL = 60 * 60;
    private static final String ERROR = "error";
    private static final Logger logger = LogManager.getLogger();

    private @EJB CustomerFacade customerFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/accounts/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        req.logout();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        logger.debug("User attempts to authorise {}", email);

        // for getting salt and calculating hash
        UserEntity user = customerFacade.getUserByLoginCredentials(email, password);
        if (user == null) {
            resp.getWriter().append(ERROR);
            return;
        }

        // using JAAS to login
        try {
            req.login(email, user.getPassword());
        } catch (ServletException e) {
            logger.warn(e.getMessage());
            resp.getWriter().append(ERROR);
        }

        session = req.getSession(true);
        session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL);
        session.setAttribute(USER_ID, user.getUserId());
        session.setAttribute(USER_EMAIL, email);
    }
}
