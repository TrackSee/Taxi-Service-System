package ua.com.tracksee.servlets.customer;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.ordermanager.TaxiOrderBean;
import ua.com.tracksee.logic.exception.OrderException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Sharaban Sasha
 *
 * This class return orderComplete.jsp,
 * get data from this page and send it to TaxiOrderBean
 */
@WebServlet("/order/complete")
public class OrderCompleteServlet extends HttpServlet {
    /* order status is QUEUED  because
    * the orders received from the page will
    * always have the status QUEUED
    */
    private static final String ORDER_STATUS = "QUEUED";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/customer/orderComplete.jsp").forward(req,resp);
    }
    private Logger logger;
    @EJB
    private TaxiOrderBean controller;

    @Override
    public void init() throws ServletException {
        super.init();
        logger = LogManager.getLogger();
    }

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


            controller.makeOrder(inputData);
            req.getRequestDispatcher("/WEB-INF/customer/success.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Database unavailable.");
        } catch (OrderException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }
    }
}