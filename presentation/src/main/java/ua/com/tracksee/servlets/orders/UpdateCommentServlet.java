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
 * @author Igor Gula
 * @author Sharaban Sasha
 */
@WebServlet("/addComment")
public class UpdateCommentServlet extends HttpServlet implements OrderAttributes {
    private @EJB OrderFacade orderFacade;

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long trackingNumber = Long.parseLong(req.getParameter(TRACKING_NUMBER_ALIAS));
            String comments = req.getParameter(COMMENTS_ALIAS);
            orderFacade.addComment(trackingNumber, comments);
            req.setAttribute(ADD_COMMENTS_SUCCESS, orderFacade.getSuccessAlert(ADD_COMMENTS_SUCCESS_MESSAGE));
            req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
        }catch (NumberFormatException e){
            logger.error("Impossible to convert String to long " + e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
