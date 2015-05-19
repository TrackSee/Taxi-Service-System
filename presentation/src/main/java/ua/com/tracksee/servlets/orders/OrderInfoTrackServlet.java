package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.OrderStatus;
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
@WebServlet("/orderTracking")
public class OrderInfoTrackServlet extends HttpServlet implements OrderAttributes {

    private
    @EJB
    OrderFacade orderFacade;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "orderInfo");
        req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userID=null;
        try {
            userID = (Integer) req.getSession().getAttribute(USER_ID_ALIAS);
        } catch (NullPointerException e) {
            logger.info("don't authorised user");
        }
        try {
            long trackingNumber = Long.parseLong(req.getParameter(TRACKING_NUMBER_ALIAS));
            if (userID == null) {
                if (orderFacade.checkOrderPresentNonActiveUser(trackingNumber)) {
                    TaxiOrderEntity taxiOrderEntity = setParametersToPage(req, resp, trackingNumber);
                    redirectSwitch(taxiOrderEntity, req, resp);
                }else{ redirectToInfoPageWithAlert(req,resp);}
            }else
            if (userID>0) {
                System.out.println("userId "+userID);
                if (orderFacade.checkOrderPresentForActiveUser(trackingNumber, userID)) {
                    TaxiOrderEntity taxiOrderEntity = setParametersToPage(req, resp, trackingNumber);
                    redirectSwitch(taxiOrderEntity,req,resp);
                }else{ redirectToInfoPageWithAlert(req,resp);}
            }else {
             redirectToInfoPageWithAlert(req,resp);
            }
        } catch (NumberFormatException e) {
            logger.error("invalid tracking number " + e);
          redirectToInfoPageWithAlert(req,resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

    }

    private TaxiOrderEntity setParametersToPage(HttpServletRequest req, HttpServletResponse resp, long trackingNumber) {

        TaxiOrderEntity taxiOrderEntity = orderFacade.getOrderInfo(trackingNumber);
        UserEntity userEntity = orderFacade.getUserInfo(taxiOrderEntity.getUserId());

        req.setAttribute(TRACKING_NUMBER_ALIAS, trackingNumber);
        System.out.println("Phone "+userEntity.getPhone());
        req.setAttribute(PHONE_NUMBER_ALIAS, userEntity.getPhone());
        req.setAttribute(EMAIL_ALIAS, userEntity.getEmail());
        // req.setAttribute(ADDRESSES_PATH,);
        // TODO decide req.setAttribute(PRICE_ALIAS, taxiOrderEntity.getPrice());

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
    private void redirectToInfoPageWithAlert(HttpServletRequest req,
                                             HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(NON_EXIST_TRACKING_NUMBER_WARNING,
                orderFacade.getSuccessAlert(NON_EXIST_TRACKING_NUMBER_WARNING_MESSAGE));
        req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
    }
    private void redirectSwitch(TaxiOrderEntity taxiOrderEntity, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (taxiOrderEntity.getStatus() == OrderStatus.REFUSED ||
                taxiOrderEntity.getStatus() == OrderStatus.COMPLETED) {

            req.getRequestDispatcher(ORDER_TRACK_COMPLETE_PAGE).forward(req, resp);

        } else if (taxiOrderEntity.getStatus() == OrderStatus.ASSIGNED ||
                taxiOrderEntity.getStatus() == OrderStatus.IN_PROGRESS) {

                req.getRequestDispatcher(ORDER_IN_PROGRESS_PAGE).forward(req, resp);

            }else if(taxiOrderEntity.getStatus() == OrderStatus.QUEUED ||
                taxiOrderEntity.getStatus() == OrderStatus.UPDATED){

            req.getRequestDispatcher(ORDER_TRACK_PAGE).forward(req, resp);

        }
        }
    }
