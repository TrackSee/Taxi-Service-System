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
import java.util.HashMap;

/**
 * @author Igor Gula
 * @author Sharaban Sasha
 */
@WebServlet("/updateOrder")
public class UpdateOrderServlet extends HttpServlet implements OrderAttributeNames,AlertMessages,PageAddresses {

    private
    @EJB
    OrderFacade orderFacade;

    private static final Logger logger = LogManager.getLogger();
    private static final String UPDATED_STATUS = "UPDATED";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HashMap<String, String> inputData = new HashMap<String, String>();
        try {
            inputData.put(TRACKING_NUMBER_ALIAS, req.getParameter(TRACKING_NUMBER_ALIAS));
            inputData.put(ADDRESS_ORIGIN_ALIAS, req.getParameter(ADDRESS_ORIGIN_ALIAS));
            inputData.put(ADDRESS_DESTINATION_ALIAS, req.getParameter(ADDRESS_DESTINATION_ALIAS));


            inputData.put(ARRIVE_DATE_ALIAS, req.getParameter(ARRIVE_DATE_ALIAS));
            inputData.put(END_DATE_ALIAS, req.getParameter(END_DATE_ALIAS));
            inputData.put(ORDER_STATUS_ALIAS,UPDATED_STATUS);
            inputData.put(SERVICE_ALIAS, req.getParameter(SERVICE_ALIAS));
            inputData.put(CAR_CATEGORY_ALIAS, req.getParameter(CAR_CATEGORY_ALIAS));
            inputData.put(MUSIC_STYLE_ALIAS, req.getParameter(MUSIC_STYLE_ALIAS));
            inputData.put(ANIMAL_TRANSPORTATION_ALIAS, req.getParameter(ANIMAL_TRANSPORTATION_ALIAS));
            inputData.put(FREE_WIFI_ALIAS, req.getParameter(FREE_WIFI_ALIAS));
            inputData.put(NON_SMOKING_DRIVER_ALIAS, req.getParameter(NON_SMOKING_DRIVER_ALIAS));
            inputData.put(AIR_CONDITIONER_ALIAS, req.getParameter(AIR_CONDITIONER_ALIAS));

            inputData.put(WAY_OF_PAYMENT_ALIAS, req.getParameter(WAY_OF_PAYMENT_ALIAS));
            inputData.put(DRIVER_SEX_ALIAS, req.getParameter(DRIVER_SEX_ALIAS));
            inputData.put(SERVICE_ALIAS, req.getParameter(SERVICE_ALIAS));
            inputData.put(DESCRIPTION_ALIAS, req.getParameter(DESCRIPTION_ALIAS));

            orderFacade.updateOrder(inputData);
            req.setAttribute(UPDATE_SUCCESS, orderFacade.getSuccessAlert(UPDATE_SUCCESS_MESSAGE));
            req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
        } catch (Exception e) {
            logger.error("error");
            e.printStackTrace();
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}