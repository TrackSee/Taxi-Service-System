package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.json.TaxiOrderDTO;
import ua.com.tracksee.logic.facade.OrderFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class return order.jsp,
 * get data from this page and send it
 * to TaxiOrderBean.
 * Order status is QUEUED  because
 * the orders received from the page will
 * always have the status QUEUED
 *
 * @author Sharaban Sasha
 * @author Ruslan Gunavardana
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet implements OrderAttributes {
    private static final Logger logger = LogManager.getLogger();
    private @EJB OrderFacade orderFacade;

    /**
     * @author Sharaban Sasha
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ORDER_PAGE).forward(req, resp);
    }

    /**
     * @author Ruslan Gunavardana
     */
    // TODO Ruslan workaround
    private void method(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            String line;
            do {
                line = reader.readLine();
                sb.append(line).append("\n");
            } while (line != null);
        } catch (IOException e) {
            logger.warn("Cannot get JSON from POST /order");
        }
        TaxiOrderDTO orderDTO = null;

        orderDTO = mapper.readValue(sb.toString(), TaxiOrderDTO.class);

        HttpSession session = req.getSession(false);
        Integer userId = session != null ? (Integer) session.getAttribute("userId") : null;
        if (userId != null) {
            // taxiOrderBean.createAuthorisedOrder(userId, orderDTO);
        } else {
            //TODO create order without signup merge
        }
    }

    /**
     * @author Sharaban Sasha
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "orderInformation");
        HashMap<String, String> inputData = new HashMap<String, String>();
        try {
            inputData.put(PHONE_NUMBER, req.getParameter(PHONE_NUMBER));
            inputData.put(EMAIL, req.getParameter("email"));
            inputData.put(ADDRESS_ORIGIN, req.getParameter(ADDRESS_ORIGIN));
            inputData.put(ADDRESS_DESTINATION, req.getParameter(ADDRESS_DESTINATION));
            inputData.put(ORDER_STATUS, ORDER_STATUS_VALUE_QUEUED);
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


            if (orderFacade.checkBlackListByUserEmail(inputData.get(EMAIL))) {
                req.setAttribute(ORDER_WARNING,orderFacade.getWarningAlert(ORDER_WARNING_MESSAGE));
            } else {
                Long trackingNumber = orderFacade.makeOrder(inputData);
                req.setAttribute(TRACKING_NUMBER, trackingNumber);
                req.setAttribute(ORDER_SUCCESS, orderFacade.getSuccessAlert(ORDER_SUCCESS_MESSAGE +trackingNumber
                        + ORDER_SUCCESS_TRACK_BUTTON));
                req.setAttribute(HIDE_ORDER_TRACK, HIDE);
            }
            req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

    }
}
