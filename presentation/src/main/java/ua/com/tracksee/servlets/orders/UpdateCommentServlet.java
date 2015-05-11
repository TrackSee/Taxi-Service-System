package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.TaxiOrderBean;
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
public class UpdateCommentServlet extends HttpServlet {
    private @EJB OrderFacade orderFacade;

    private static final Logger logger = LogManager.getLogger();

    private static final String TRACK_NUMBER_ALIAS = "trackingNumber";
    private static final String COMMENT_ALIAS = "comments";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackNumberString = req.getParameter(TRACK_NUMBER_ALIAS);
        try {
            long trackNumber = Long.parseLong(trackNumberString);
            String comment = req.getParameter(COMMENT_ALIAS);
            orderFacade.addComment(trackNumber, comment);
            req.setAttribute("successAddComments", "Show");
            req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
        }catch (NumberFormatException e){
            logger.error("Impossible to convert String to long " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }
    }
}
