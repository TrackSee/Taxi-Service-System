package ua.com.tracksee.servlets.customer;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;
import ua.com.tracksee.logic.OrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Igor on 03.05.2015.
 */
@WebServlet("OrderEntitiesServlet")
public class OrderEntitiesServlet extends HttpServlet {
    @EJB
    private OrderBean orderBean;

    private static final Logger logger = LogManager.getLogger();

    private static final String TRACK_NUMBER_ALIAS = "trackNumber";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackNumberString = req.getParameter(TRACK_NUMBER_ALIAS);

        List<TaxiOrderItemEntity> items = null;
        try {
            Integer trackNumber = Integer.parseInt(trackNumberString);
            items = orderBean.getItemEntities(trackNumber);
        } catch (Exception e) {
            logger.error("Imposible to convert String to Integer " + e.getMessage());
        }
        if ((items != null) && (items.size() > 0))  {
            Gson gson = new Gson();
            String taxiOrder = gson.toJson(items);
            resp.getWriter().write(taxiOrder);
        }
    }
}