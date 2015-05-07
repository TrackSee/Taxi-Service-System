package ua.com.tracksee.servlets.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Boolean.TRUE;

/**
 * @author Ruslan Gunavardana
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private static final int SESSION_MAX_INACTIVE_INTERVAL = 60 * 60;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final Logger logger = LogManager.getLogger();

    private @EJB UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "signIn");
        req.getRequestDispatcher("/WEB-INF/accounts/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        logger.debug("User attempts to authorise {}", email);
        try {
            req.login(email, password);
        } catch (ServletException e) {
            logger.warn(e.getMessage());
            resp.getWriter().append(ERROR);
            return;
        }

        ServiceUserEntity user = userDAO.getUserByEmail(email);
        if (user == null || !Objects.equals(user.getPassword(), password) || user.getActivated() != TRUE) {
            resp.getWriter().append(ERROR);
            return;
        }
        session = req.getSession(true);
        session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("email", email);
    }
}
