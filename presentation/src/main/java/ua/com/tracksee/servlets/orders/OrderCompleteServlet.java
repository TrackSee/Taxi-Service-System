package ua.com.tracksee.servlets.orders;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.TaxiOrderBean;
import ua.com.tracksee.logic.exception.OrderException;

import javax.ejb.EJB;
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
@WebServlet("/order/complete")
public class OrderCompleteServlet extends HttpServlet {
    /**
     * Order status is QUEUED  because
     * the orders received from the page will
     * always have the status QUEUED
    */
    private static final String ORDER_STATUS = "QUEUED";
    private static final Logger logger = LogManager.getLogger();

    private @EJB TaxiOrderBean controller;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String,String> inputData=new HashMap<String, String>();
        try {
            inputData.put("phoneNumber", req.getParameter("phoneNumber"));
            inputData.put("email",req.getParameter("email"));
            inputData.put("addressOrigin", req.getParameter("addressOrigin"));
            inputData.put("addressDestination", req.getParameter("addressDestination"));
            inputData.put("orderStatus", ORDER_STATUS);
            inputData.put("carCategory", req.getParameter("carCategory"));
            inputData.put("wayOfPayment",req.getParameter("wayOfPayment"));
            inputData.put("driverSex",req.getParameter("driverSex"));
            inputData.put("service",req.getParameter("service"));
            inputData.put("musicStyle",req.getParameter("musicStyle"));
            inputData.put("animalTransportation",req.getParameter("animalTransportation"));
            inputData.put("freeWifi",req.getParameter("freeWifi"));
            inputData.put("smokingDriver",req.getParameter("smokingDriver"));
            inputData.put("airConditioner",req.getParameter("airConditioner"));
            inputData.put("price",req.getParameter("price"));
            inputData.put("description",req.getParameter("description"));


            controller.createNonAuthorisedOrder(inputData);
            req.getRequestDispatcher("/WEB-INF/customer/success.jsp").forward(req, resp);
        } catch (OrderException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }
    }
}
