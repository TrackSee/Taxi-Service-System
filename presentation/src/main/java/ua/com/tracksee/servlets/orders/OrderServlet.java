package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.dto.TaxiOrderDTO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
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
 * This servlet return order.jsp,
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
    private
    @EJB
    OrderFacade orderFacade;

    /**
     * @author Sharaban Sasha
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "order");
        Integer userID=0;
        try {
            userID = (Integer) req.getSession().getAttribute(USER_ID_ALIAS);

        if(userID>0){
            UserEntity userEntity = orderFacade.getUserInfo(userID);
            req.setAttribute(PHONE_NUMBER_ALIAS, userEntity.getPhone());
            req.setAttribute(EMAIL_ALIAS, userEntity.getEmail());
        }
        }catch (NullPointerException e){
            logger.info("don't authorised user");
            req.getRequestDispatcher(ORDER_PAGE).forward(req, resp);
        }
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
//TODO calculationg price via route
        HashMap<String, String> inputData = new HashMap<String, String>();
        try {

            inputData.put(PHONE_NUMBER_ALIAS, req.getParameter(PHONE_NUMBER_ALIAS));
            inputData.put(EMAIL_ALIAS, req.getParameter(EMAIL_ALIAS));

            inputData.put(ORDER_STATUS_ALIAS, ORDER_STATUS_VALUE_QUEUED);
            inputData.put(AMOUNT_OF_CARS_ALIAS,req.getParameter(AMOUNT_OF_CARS_ALIAS));
            inputData.put(AMOUNT_OF_HOURS_ALIAS,req.getParameter(AMOUNT_OF_HOURS_ALIAS));
            inputData.put(AMOUNT_OF_MINUTES_ALIAS,req.getParameter(AMOUNT_OF_MINUTES_ALIAS));
            inputData.put(ARRIVE_DATE_ALIAS, req.getParameter(ARRIVE_DATE_ALIAS));
            inputData.put(END_DATE_ALIAS, req.getParameter(END_DATE_ALIAS));
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


            if (orderFacade.checkBlackListByUserEmail(inputData.get(EMAIL_ALIAS))) {
                req.setAttribute(ORDER_WARNING, orderFacade.getWarningAlert(ORDER_WARNING_MESSAGE));
            } else {
                Long trackingNumber = orderFacade.makeOrder(inputData);
                req.setAttribute(TRACKING_NUMBER_ALIAS, trackingNumber);
                req.setAttribute(ORDER_SUCCESS, orderFacade.getSuccessAlert(ORDER_SUCCESS_MESSAGE + trackingNumber
                        +ORDER_SUCCESS_TRACK_BUTTON));
                req.setAttribute(HIDE_ORDER_TRACK, HIDE);
            }
            req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

    }
}
