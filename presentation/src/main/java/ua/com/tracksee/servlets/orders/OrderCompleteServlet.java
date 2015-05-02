package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

/**
* This class return orderComplete.jsp,
* get data from this page and send it to TaxiOrderBean.
*
* @author Sharaban Sasha
*/
@WebServlet("/orderComplete")
public class OrderCompleteServlet extends HttpServlet {
    /**
     * Order status is QUEUED  because
     * the orders received from the page will
     * always have the status QUEUED
    */
    private static final String ORDER_STATUS = "QUEUED";
    private static final Logger logger = LogManager.getLogger();

   private @EJB TaxiOrderBean taxiOrderBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "orderComplete");
        req.getRequestDispatcher("/WEB-INF/customer/orderComplete.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "orderInformation");
        HashMap<String,String> inputData=new HashMap<String, String>();
        try {
            inputData.put("phoneNumber", req.getParameter("phoneNumber"));
            inputData.put("email",req.getParameter("email"));
            inputData.put("addressOrigin", req.getParameter("addressOrigin"));
            inputData.put("addressDestination", req.getParameter("addressDestination"));
            inputData.put("orderStatus", ORDER_STATUS);
            //TODO calculationg distance, now for test it's 10
            inputData.put("distance","10");

            inputData.put("arriveDate", req.getParameter("arriveDate"));
            inputData.put("endDate", req.getParameter("endDate"));

            if(req.getParameter("service").equals("soberDriver")) {
                inputData.put("service","soberDriver");
                inputData.put("carCategory","userCar");
                inputData.put("musicStyle", "default");
                inputData.put("animalTransportation","false");
                inputData.put("freeWifi","false");
                inputData.put("smokingDriver","false");
                inputData.put("airConditioner","false");
            }else{
                inputData.put("service",req.getParameter("service"));
                inputData.put("carCategory",req.getParameter("carCategory"));
                inputData.put("musicStyle", req.getParameter("musicStyle"));
                inputData.put("animalTransportation",req.getParameter("animalTransportation"));
                inputData.put("freeWifi",req.getParameter("freeWifi"));
                inputData.put("smokingDriver",req.getParameter("smokingDriver"));
                inputData.put("airConditioner",req.getParameter("airConditioner"));
            }
                inputData.put("wayOfPayment",req.getParameter("wayOfPayment"));
                inputData.put("driverSex",req.getParameter("driverSex"));
                inputData.put("service",req.getParameter("service"));

            inputData.put("description",req.getParameter("description"));

            Long trackingNumber = taxiOrderBean.makeOrder(inputData);

            req.setAttribute("successAlert", "<div class=\"alert alert-success\" role=\"alert\"  >" +
                    "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "  <span aria-hidden=\"true\">&times;</span></button><h3>Your order accepted for further " +
                    "processing successfully and you was assigned to such tracking number:"
                    + trackingNumber + "</h3></div>");
            req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
        } catch (OrderException | MessagingException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }
    }
}
