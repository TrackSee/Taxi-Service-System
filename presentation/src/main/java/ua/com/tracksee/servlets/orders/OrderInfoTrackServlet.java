package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.dto.RouteDTO;
import ua.com.tracksee.dto.TaxiOrderDTO;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.OrderStatus;
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

import static java.lang.Long.parseLong;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/orderTracking")
public class OrderInfoTrackServlet extends HttpServlet implements OrderAttributeNames, OrderAttributesValues,
        AlertMessages, PageAddresses {

    private @EJB OrderFacade orderFacade;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userID;
        userID = (Integer) req.getSession().getAttribute(USER_ID_ALIAS);
        String email=req.getParameter("email");
        try {
            long trackingNumber = parseLong(req.getParameter(TRACKING_NUMBER_ALIAS));
            if (userID == null) {
                if (orderFacade.checkOrderPresentNonActiveUserEmail(trackingNumber, email)) {
                    TaxiOrderEntity taxiOrderEntity = setParametersToPage(req, resp, trackingNumber);
                    redirectByOrderStatus(taxiOrderEntity, req, resp);
                }
                if (orderFacade.checkOrderPresentNonActiveUser(trackingNumber)) {
                    req.setAttribute(EMAIL_FOR_NON_ACTIVE_USER_ALIAS, EMAIL_FOR_NON_ACTIVE_USER);
                    nonActiveUserAlert(req, resp);
                } else if (orderFacade.checkOrderPresentActiveUser(trackingNumber)) {
                    req.setAttribute(EMAIL_FOR_NON_ACTIVE_USER_ALIAS,EMAIL_FOR_NON_ACTIVE_USER);
                    activeUserAlert(req, resp);
                } else {
                    req.setAttribute(EMAIL_FOR_NON_ACTIVE_USER_ALIAS,EMAIL_FOR_NON_ACTIVE_USER);
                    nonExistTrackNumberAlert(req, resp);
                }
            } else {
                if (orderFacade.checkOrderPresentForActiveUser(trackingNumber, userID)) {
                    TaxiOrderEntity taxiOrderEntity = setParametersToPage(req, resp, trackingNumber);
                    redirectByOrderStatus(taxiOrderEntity, req, resp);
                }else if (orderFacade.checkOrderPresentActiveUser(trackingNumber)) {
                    activeUserAlert(req, resp);
                }else if (orderFacade.checkOrderPresentNonActiveUser(trackingNumber)) {
                    nonActiveUserAlert(req, resp);
                } else {
                    nonExistTrackNumberAlert(req, resp);
                }
            }
        } catch (NumberFormatException e) {
            logger.error("invalid tracking number " + e);
            nonExistTrackNumberAlert(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

    }

    private TaxiOrderEntity setParametersToPage(HttpServletRequest req, HttpServletResponse resp, long trackingNumber) throws IOException {

        TaxiOrderEntity taxiOrderEntity = orderFacade.getOrderInfo(trackingNumber);
        UserEntity userEntity = orderFacade.getUserInfo(taxiOrderEntity.getUser().getUserId());

        req.setAttribute(TRACKING_NUMBER_ALIAS, trackingNumber);
        req.setAttribute(ORDER_STATUS_ALIAS, taxiOrderEntity.getStatus());
        req.setAttribute(PHONE_NUMBER_ALIAS, userEntity.getPhone());
        req.setAttribute(EMAIL_ALIAS, userEntity.getEmail());

        ObjectMapper mapper = new ObjectMapper();
        TaxiOrderDTO orderDto = new TaxiOrderDTO(taxiOrderEntity.getItemList());
        String orderJson = mapper.writeValueAsString(orderDto);

        req.setAttribute(ORDER_ALIAS, orderJson);

        req.setAttribute(PRICE_ALIAS, taxiOrderEntity.getPrice());

        req.setAttribute(ARRIVE_DATE_ALIAS, orderFacade.convertDateForShow(taxiOrderEntity.getArriveDate()));

        req.setAttribute(AMOUNT_OF_CARS_ALIAS, taxiOrderEntity.getAmountOfCars());
        req.setAttribute(AMOUNT_OF_HOURS_ALIAS, taxiOrderEntity.getAmountOfHours());
        req.setAttribute(AMOUNT_OF_MINUTES_ALIAS, taxiOrderEntity.getAmountOfMinutes());

        req.setAttribute(orderFacade.getFromEnumWayOfPayment(taxiOrderEntity.getWayOfPayment()), OPTION_SELECTED);
        req.setAttribute(orderFacade.getFromEnumService(taxiOrderEntity.getService()), OPTION_SELECTED);
        req.setAttribute(orderFacade.getFromEnumMusicStyle(taxiOrderEntity.getMusicStyle()), OPTION_SELECTED);
        req.setAttribute(orderFacade.getFromEnumDriverSex(taxiOrderEntity.getDriverSex()), OPTION_SELECTED);
        req.setAttribute(orderFacade.getFromEnumCarCategory(taxiOrderEntity.getCarCategory()), OPTION_SELECTED);

        if (taxiOrderEntity.getAnimalTransportation()) {
            req.setAttribute(ANIMAL_TRANSPORTATION_ALIAS, CHECKBOX_CHECKED);
        }
        if (taxiOrderEntity.getFreeWifi()) {
            req.setAttribute(FREE_WIFI_ALIAS, CHECKBOX_CHECKED);
        }
        if (taxiOrderEntity.getNonSmokingDriver()) {
            req.setAttribute(NON_SMOKING_DRIVER_ALIAS, CHECKBOX_CHECKED);
        }
        if (taxiOrderEntity.getAirConditioner()) {
            req.setAttribute(AIR_CONDITIONER_ALIAS, CHECKBOX_CHECKED);
        }
        req.setAttribute(DESCRIPTION_ALIAS, taxiOrderEntity.getDescription());

        if (taxiOrderEntity.getComment() != null) {
            req.setAttribute(COMMENTS_ALIAS, taxiOrderEntity.getComment());
            req.setAttribute(COMMENTS_STATE_ALIAS, DISABLE);
            req.setAttribute(BUTTON_COMMENTS_HIDE_ALIAS, HIDE);
        }
        return taxiOrderEntity;
    }

    private void nonExistTrackNumberAlert(HttpServletRequest req,
                                          HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(NON_EXIST_TRACKING_DANGER,
                orderFacade.getDangerAlert(NON_EXIST_TRACKING_NUMBER_DANGER_MESSAGE));
        req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
    }

    private void activeUserAlert(HttpServletRequest req,
                                 HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ACTIVE_USER_ORDER_WARNING,
                orderFacade.getWarningAlert(ACTIVE_USER_ORDER_MESSAGE));
        req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
    }

    private void nonActiveUserAlert(HttpServletRequest req,
                               HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(NON_ACTIVE_USER_ORDER_WARNING,
                orderFacade.getWarningAlert(NON_ACTIVE_USER_ORDER_MESSAGE_UNREGISTERED));
        req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
    }


    private void redirectByOrderStatus(TaxiOrderEntity taxiOrderEntity, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (taxiOrderEntity.getStatus() == OrderStatus.REFUSED ||
                taxiOrderEntity.getStatus() == OrderStatus.COMPLETED) {
            req.getRequestDispatcher(ORDER_TRACK_COMPLETE_PAGE).forward(req, resp);
        } else {
            req.getRequestDispatcher(ORDER_TRACK_PAGE).forward(req, resp);

        }
    }
}
