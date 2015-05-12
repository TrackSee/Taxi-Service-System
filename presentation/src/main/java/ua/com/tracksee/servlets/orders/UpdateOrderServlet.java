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
import java.util.HashMap;

/**
 * @author Igor Gula
 * @author Sharaban Sasha
 */
@WebServlet("/updateOrder")
public class UpdateOrderServlet extends HttpServlet implements OrderAttributes {

    private @EJB OrderFacade orderFacade;

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> inputData = new HashMap<String, String>();
        try {
            inputData.put(TRACKING_NUMBER, req.getParameter(TRACKING_NUMBER));
            inputData.put(ADDRESS_ORIGIN, req.getParameter(ADDRESS_ORIGIN));
            inputData.put(ADDRESS_DESTINATION, req.getParameter(ADDRESS_DESTINATION));
            //TODO calculationg distance, now for test it's 10
            inputData.put(DISTANCE, "10");

                inputData.put(ARRIVE_DATE, req.getParameter(ARRIVE_DATE));
                inputData.put(END_DATE, req.getParameter(END_DATE));


            if (req.getParameter(SERVICE).equals("soberDriver")) {
                inputData.put(SERVICE, "soberDriver");
                inputData.put(CAR_CATEGORY, "userCar");
                inputData.put(MUSIC_STYLE, "default");
                inputData.put(ANIMAL_TRANSPORTATION, "false");
                inputData.put(FREE_WIFI, "false");
                inputData.put(SMOKING_DRIVER, "false");
                inputData.put(AIR_CONDITIONER, "false");
            } else {
                inputData.put(SERVICE, req.getParameter(SERVICE));
                inputData.put(CAR_CATEGORY, req.getParameter(CAR_CATEGORY));
                inputData.put(MUSIC_STYLE, req.getParameter(MUSIC_STYLE));
                inputData.put(ANIMAL_TRANSPORTATION, req.getParameter(ANIMAL_TRANSPORTATION));
                inputData.put(FREE_WIFI, req.getParameter(FREE_WIFI));
                inputData.put(SMOKING_DRIVER, req.getParameter(SMOKING_DRIVER));
                inputData.put(AIR_CONDITIONER, req.getParameter(AIR_CONDITIONER));
            }
            inputData.put(WAY_OF_PAYMENT, req.getParameter(WAY_OF_PAYMENT));
            inputData.put(DRIVER_SEX, req.getParameter(DRIVER_SEX));
            inputData.put(SERVICE, req.getParameter(SERVICE));
            inputData.put(DESCRIPTION, req.getParameter(DESCRIPTION));

            orderFacade.updateOrder(inputData);
            req.setAttribute(UPDATE_SUCCESS,orderFacade.getSuccessAlert(UPDATE_SUCCESS_MESSAGE));
            req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
        } catch (Exception  e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}