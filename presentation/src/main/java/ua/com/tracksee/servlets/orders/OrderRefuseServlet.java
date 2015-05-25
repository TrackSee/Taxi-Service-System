package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.facade.OrderFacade;
import ua.com.tracksee.servlets.AlertMessages;
import ua.com.tracksee.servlets.PageAddresses;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet makes refusing order
 * and returns order info page with
 * success message.
 *
 * @author Sharaban Sasha
 */
@WebServlet("/orderRefuse")
public class OrderRefuseServlet extends HttpServlet implements OrderAttributeNames,AlertMessages,PageAddresses {
    private
    @EJB
    OrderFacade orderFacade;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long trackingNumber = Long.parseLong(req.getParameter(TRACKING_NUMBER_ALIAS));
            orderFacade.refuseOrder(trackingNumber);
            req.setAttribute(REFUSE_SUCCESS, orderFacade.getSuccessAlert(REFUSE_SUCCESS_MESSAGE));
            req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

    }
}
