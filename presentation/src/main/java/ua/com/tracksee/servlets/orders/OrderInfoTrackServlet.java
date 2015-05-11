package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.OrderStatus;
import ua.com.tracksee.logic.EnumValidationBean;
import ua.com.tracksee.logic.TaxiOrderBean;
import ua.com.tracksee.logic.ValidationBean;

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
public class OrderInfoTrackServlet extends HttpServlet {
    private @EJB TaxiOrderBean taxiOrderBean;
    private @EJB EnumValidationBean enumValidationBean;
    private @EJB ValidationBean validationBean;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String service = null;
        String musicStyle = null;
        String driverSex = null;
        String carCategory = null;
        String wayOfPayment = null;
        try {

            long trackingNumber = Integer.parseInt(req.getParameter("orderTrackingNumber"));

            if (taxiOrderBean.checkOrderPresent(trackingNumber)) {
                TaxiOrderEntity taxiOrderEntity = taxiOrderBean.getOrderInfo(trackingNumber);
                UserEntity userEntity = taxiOrderBean.getUserInfo(taxiOrderEntity.getUserId());

                req.setAttribute("trackingNumber", trackingNumber);
                req.setAttribute("phoneNumber", userEntity.getPhone());
                req.setAttribute("email", userEntity.getEmail());

               //TODO addresses

                req.setAttribute("arriveDate", validationBean.convertDateForShow(taxiOrderEntity.getArriveDate()));
                req.setAttribute("endDate", validationBean.convertDateForShow(taxiOrderEntity.getEndDate()));

                req.setAttribute("description", taxiOrderEntity.getDescription());
                if (taxiOrderEntity.getComment() != null) {
                    req.setAttribute("comments", taxiOrderEntity.getComment());
                    req.setAttribute("commentsState", "disabled=\"disabled\"");
                    req.setAttribute("buttonCommentsHide", "hidden=\"hidden\"");

                }
                req.setAttribute(enumValidationBean.getFromEnumWayOfPayment(taxiOrderEntity.getWayOfPayment()), "selected=\"selected\"");

                req.setAttribute(enumValidationBean.getFromEnumService(taxiOrderEntity.getService()),
                        "selected=\"selected\"");

                req.setAttribute(enumValidationBean.getFromEnumMusicStyle(taxiOrderEntity.getMusicStyle()),
                        "selected=\"selected\"");

                req.setAttribute(enumValidationBean.getFromEnumDriverSex(taxiOrderEntity.getDriverSex()),
                        "selected=\"selected\"");

                req.setAttribute(enumValidationBean.getFromEnumCarCategory(taxiOrderEntity.getCarCategory()),
                        "selected=\"selected\"");

                if (taxiOrderEntity.getAnimalTransportation()) {
                    req.setAttribute("animalTransportation", "checked=\"checked\"");
                }
                if (taxiOrderEntity.getFreeWifi()) {
                    req.setAttribute("freeWifi", "checked=\"checked\"");
                }
                if (taxiOrderEntity.getNonSmokingDriver()) {
                    req.setAttribute("smokingDriver", "checked=\"checked\"");
                }
                if (taxiOrderEntity.getAirConditioner()) {
                    req.setAttribute("airConditioner", "checked=\"checked\"");
                }
                if (taxiOrderEntity.getStatus() == OrderStatus.REFUSED || taxiOrderEntity.getStatus() == OrderStatus.COMPLETED) {

                    req.getRequestDispatcher("/WEB-INF/customer/orderTrackComplete.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("/WEB-INF/customer/orderTrack.jsp").forward(req, resp);
                }
            } else {
                req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
            }


        }catch (NumberFormatException e){
            logger.error("invalid tracking number "+e);
            req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
        }catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }

    }
}
