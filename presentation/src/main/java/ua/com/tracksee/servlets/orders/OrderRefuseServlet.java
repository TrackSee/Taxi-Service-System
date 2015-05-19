package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.facade.OrderFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/orderRefuse")
public class OrderRefuseServlet extends HttpServlet implements OrderAttributes {
    private @EJB OrderFacade orderFacade;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            long trackingNumber=Long.parseLong(req.getParameter(TRACKING_NUMBER_ALIAS));
           //orderFacade.refuseOrder(trackingNumber);
                req.setAttribute(REFUSE_SUCCESS,orderFacade.getSuccessAlert(REFUSE_SUCCESS_MESSAGE));

              //  req.setAttribute(REFUSE_WARNING,orderFacade.getWarningAlert(REFUSE_WARNING_MESSAGE));
             //   req.setAttribute(TRACKING_NUMBER_ALIAS,trackingNumber);

            req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

    }
}
