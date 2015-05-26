package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.logic.facade.OrderFacade;
import ua.com.tracksee.servlets.PageAddresses;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;

/**
 * This servlet returns
 * orderInfo page.
 *
 * @author Sharaban Sasha
 */
@WebServlet("/orderInfo")
public class OrderInfoServlet extends HttpServlet implements PageAddresses,OrderAttributeNames {
    private static final Logger logger = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userID;
        userID = (Integer) req.getSession().getAttribute(USER_ID_ALIAS);
        try {
            if (userID == null) {
                req.setAttribute(EMAIL_FOR_NON_ACTIVE_USER_ALIAS,EMAIL_FOR_NON_ACTIVE_USER);
            }
        req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req,resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
