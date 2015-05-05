package ua.com.tracksee.servlets.orders;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.enumartion.OrderStatus;
import ua.com.tracksee.enumartion.Sex;
import ua.com.tracksee.logic.TaxiOrderBean;
import ua.com.tracksee.logic.exception.OrderException;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * @author Igor Gula
 * @author Sharaban Sasha
 */
@WebServlet("/updateOrder")
public class UpdateOrderServlet extends HttpServlet {

    @EJB
    private TaxiOrderBean taxiOrderBean;

    private static final Logger logger = LogManager.getLogger();
    /**
     * Order status is QUEUED  because
     * the orders received from the page will
     * always have the status QUEUED
     */
    private static final String ORDER_STATUS = "QUEUED";
    private static final String STATUS = "status";
    private static final String DRIVER_SEX = "driverSex";
    private static final String MUSIC_STYLE = "musicStyle";
    private static final String ANIMAL_TRANSPORTATION = "animalTransportation";
    private static final String WIFI = "wifi";
    private static final String SMOKING_DRIVER = "smokingDriver";
    private static final String CONDITIONER = "airConditioner";
    private static final String TRACKING_NUMBER = "trackingNumber";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> inputData = new HashMap<String, String>();
        try {
            inputData.put("trackingNumber", req.getParameter("trackingNumber"));
            inputData.put("phoneNumber", req.getParameter("phoneNumber"));
            inputData.put("email", req.getParameter("email"));
            inputData.put("addressOrigin", req.getParameter("addressOrigin"));
            inputData.put("addressDestination", req.getParameter("addressDestination"));
            inputData.put("orderStatus", ORDER_STATUS);
            //TODO calculationg distance, now for test it's 10
            inputData.put("distance", "10");

            if(req.getParameter("arriveDate")==null){
            inputData.put("arriveDate", "");
            }else{ inputData.put("arriveDate", req.getParameter("arriveDate"));}

            if(req.getParameter("endDate")==null) {
                inputData.put("endDate", "");
            }else{    inputData.put("endDate", req.getParameter("endDate"));}


            if (req.getParameter("service").equals("soberDriver")) {
                inputData.put("service", "soberDriver");
                inputData.put("carCategory", "userCar");
                inputData.put("musicStyle", "default");
                inputData.put("animalTransportation", "false");
                inputData.put("freeWifi", "false");
                inputData.put("smokingDriver", "false");
                inputData.put("airConditioner", "false");
            } else {
                inputData.put("service", req.getParameter("service"));
                inputData.put("carCategory", req.getParameter("carCategory"));
                inputData.put("musicStyle", req.getParameter("musicStyle"));
                inputData.put("animalTransportation", req.getParameter("animalTransportation"));
                inputData.put("freeWifi", req.getParameter("freeWifi"));
                inputData.put("smokingDriver", req.getParameter("smokingDriver"));
                inputData.put("airConditioner", req.getParameter("airConditioner"));
            }
            inputData.put("wayOfPayment", req.getParameter("wayOfPayment"));
            inputData.put("driverSex", req.getParameter("driverSex"));
            inputData.put("service", req.getParameter("service"));

            inputData.put("description", req.getParameter("description"));

            taxiOrderBean.updateOrder(inputData);
            req.setAttribute("successUpdate","Show");
            req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
        } catch (OrderException  e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }
    }
}