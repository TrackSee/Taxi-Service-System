package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.OrderStatus;
import ua.com.tracksee.logic.TaxiOrderBean;

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
public class OrderTrackingServlet extends HttpServlet {
    private
    @EJB
    TaxiOrderBean taxiOrderBean;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int trackingNumber=Integer.parseInt(req.getParameter("orderTrackingNumber"));

            TaxiOrderEntity taxiOrderEntity = taxiOrderBean.getOrderInfo(trackingNumber);

            req.setAttribute("trackingNumber", trackingNumber);
            req.setAttribute("ArriveDate", "<p>Arrive date: " + taxiOrderEntity.getArriveDate() + "</p>");
            req.setAttribute("EndDate", "<p>End date: " + taxiOrderEntity.getEndDate() + "</p>");
            req.setAttribute("service", "<p>Service: " + taxiOrderEntity.getService() + "</p>");
            req.setAttribute("musicStyle", "<p>Music style: " + taxiOrderEntity.getEndDate() + "</p>");
            req.setAttribute("driverSex", "<p>Driver sex: " + taxiOrderEntity.getDriverSex() + "</p>");
            req.setAttribute("carCategory", "<p>Car category: " + taxiOrderEntity.getCarCategory() + "</p>");
            req.setAttribute("animalTransportation", "<p>Animal transportation: " +
                    taxiOrderEntity.getAnimalTransportation() + "</p>");
            req.setAttribute("FreeWifi", "<p>Free wi-fi: " + taxiOrderEntity.getFreeWifi()+"</p>");
            req.setAttribute("smokingDriver", "<p>Smoking driver: " + taxiOrderEntity.getNonSmokingDriver()+"</p>");
            req.setAttribute("airConditioner", "<p>Air conditioner: " + taxiOrderEntity.getAirConditioner()+"</p>");
            if(taxiOrderEntity.getStatus()==OrderStatus.REFUSED||taxiOrderEntity.getStatus()== OrderStatus.COMPLETED){
            req.getRequestDispatcher("/WEB-INF/customer/orderTrackComplete.jsp").forward(req, resp);
            }else{
                req.getRequestDispatcher("/WEB-INF/customer/orderTrack.jsp").forward(req, resp);
            }
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }

    }
    }
