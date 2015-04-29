package ua.com.tracksee.servlets.accounts;

import ua.com.tracksee.entities.ServiceUserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Ruslan Gunavardana
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private static final int SESSION_MAX_INACTIVE_INTERVAL = 60 * 60;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "signIn");
        req.getRequestDispatcher("/WEB-INF/customer/login.jsp").forward(req, resp);
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
            //TODO uncomment with JAAS: resp.getWriter().append(ERROR);
            //TODO uncomment with JAAS: return;
        }
        session = req.getSession(true);
        session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL);
        session.setAttribute("email", email);
    }
}
