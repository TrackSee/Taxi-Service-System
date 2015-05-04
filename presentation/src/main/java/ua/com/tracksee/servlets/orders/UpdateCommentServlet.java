package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.TaxiOrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Igor Gula
 */
@WebServlet("/addComment")
public class UpdateCommentServlet extends HttpServlet {
    @EJB
    private TaxiOrderBean taxiOrderBean;

    private static final Logger logger = LogManager.getLogger();

    private static final String TRACK_NUMBER_ALIAS = "trackingNumber";
    private static final String COMMENT_ALIAS = "comment";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackNumberString = req.getParameter(TRACK_NUMBER_ALIAS);
        try {
            Integer trackNumber = Integer.parseInt(trackNumberString);
            String comment = req.getParameter(COMMENT_ALIAS);
            taxiOrderBean.updateComment(trackNumber, comment);
        } catch (Exception e) {
            logger.error("Imposible to convert String to Integer " + e.getMessage());
        }
    }
}
