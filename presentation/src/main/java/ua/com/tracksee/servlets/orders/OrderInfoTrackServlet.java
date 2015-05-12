package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.OrderStatus;
import ua.com.tracksee.logic.EnumValidationBean;
import ua.com.tracksee.logic.TaxiOrderBean;
import ua.com.tracksee.logic.ValidationBean;
import ua.com.tracksee.logic.facade.OrderFacade;

import javax.ejb.EJB;
import javax.faces.view.facelets.FaceletException;
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
    private @EJB OrderFacade orderFacade;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            long trackingNumber = Long.parseLong(req.getParameter(ORDER_TRACKING_NUMBER));

            if (orderFacade.checkOrderPresent(trackingNumber)) {
                TaxiOrderEntity taxiOrderEntity = orderFacade.getOrderInfo(trackingNumber);
                UserEntity userEntity = orderFacade.getUserInfo(taxiOrderEntity.getUserId());

                req.setAttribute(TRACKING_NUMBER, trackingNumber);
                req.setAttribute(PHONE_NUMBER, userEntity.getPhone());
                req.setAttribute(EMAIL, userEntity.getEmail());

               //TODO addresses and price

                req.setAttribute(ARRIVE_DATE, orderFacade.convertDateForShow(taxiOrderEntity.getArriveDate()));
                req.setAttribute(END_DATE, orderFacade.convertDateForShow(taxiOrderEntity.getEndDate()));

                req.setAttribute(DESCRIPTION, taxiOrderEntity.getDescription());
                if (taxiOrderEntity.getComment() != null) {
                    req.setAttribute(COMMENTS, taxiOrderEntity.getComment());
                    req.setAttribute(COMMENTS_STATE, DISABLE);
                    req.setAttribute(BUTTON_COMMENTS_HIDE, HIDE);
                }

                req.setAttribute(orderFacade.getFromEnumWayOfPayment(taxiOrderEntity.getWayOfPayment()),OPTION_SELECTED);
                req.setAttribute(orderFacade.getFromEnumService(taxiOrderEntity.getService()),OPTION_SELECTED);
                req.setAttribute(orderFacade.getFromEnumMusicStyle(taxiOrderEntity.getMusicStyle()),OPTION_SELECTED);
                req.setAttribute(orderFacade.getFromEnumDriverSex(taxiOrderEntity.getDriverSex()),OPTION_SELECTED);
                req.setAttribute(orderFacade.getFromEnumCarCategory(taxiOrderEntity.getCarCategory()),OPTION_SELECTED);

                if (taxiOrderEntity.getAnimalTransportation()) {
                    req.setAttribute(ANIMAL_TRANSPORTATION, CHECKBOX_CHECKED);
                }
                if (taxiOrderEntity.getFreeWifi()) {
                    req.setAttribute(FREE_WIFI, CHECKBOX_CHECKED);
                }
                if (taxiOrderEntity.getNonSmokingDriver()) {
                    req.setAttribute(SMOKING_DRIVER,CHECKBOX_CHECKED);
                }
                if (taxiOrderEntity.getAirConditioner()) {
                    req.setAttribute(AIR_CONDITIONER, CHECKBOX_CHECKED);
                }
                if (taxiOrderEntity.getStatus() == OrderStatus.REFUSED ||
                        taxiOrderEntity.getStatus() == OrderStatus.COMPLETED) {

                    req.getRequestDispatcher(ORDER_TRACK_COMPLETE_PAGE).forward(req, resp);
                } else {
                    req.getRequestDispatcher(ORDER_TRACK_PAGE).forward(req, resp);
                }
            } else {
                req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
            }
        }catch (NumberFormatException e){
            logger.error("invalid tracking number "+e);
            req.setAttribute(NON_EXIST_TRACKING_NUMBER_WARNING,
                    orderFacade.getSuccessAlert(NON_EXIST_TRACKING_NUMBER_WARNING_MESSAGE));
            req.getRequestDispatcher(ORDER_INFO_PAGE).forward(req, resp);
        }catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

    }
}
