package ua.com.tracksee.servlets.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.facade.CustomerFacade;
import ua.com.tracksee.logic.UserRoleBean;

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
import static ua.com.tracksee.servlets.AttributeNames.USER_NAME;
import static ua.com.tracksee.servlets.AttributeNames.USER_ROLE;

/**
 * @author Ruslan Gunavardana
 * @author Vitalii Diravka
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private static final int SESSION_MAX_INACTIVE_INTERVAL = 60 * 60;
    private static final Logger logger = LogManager.getLogger(SignInServlet.class);

    private @EJB CustomerFacade customerFacade;
    private @EJB UserRoleBean role;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/customer/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        req.logout();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        logger.debug("User attempts to authorise {}", email);

        // used to get user's salt and calculate password hash
        UserEntity user = customerFacade.getUserByLoginCredentials(email, password);
        UserRoleBean role = new UserRoleBean();

        // using JAAS to login
        try {
            if (user == null) {
                throw new ServletException("No user found with such email: " + email);
            }
            req.login(email, user.getPassword());
        } catch (ServletException e) {
            logger.warn(e.getMessage());
            resp.sendError(422, e.getMessage());
            return;
        }

        session = req.getSession(true);
        session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL);
        session.setAttribute(USER_ID, user.getUserId());
        session.setAttribute(USER_EMAIL, email);
        session.setAttribute(USER_NAME, user.getFirstName());
        session.setAttribute(USER_ROLE, role.getRole(user.getAdmin(), user.getDriver()));
    }
}
