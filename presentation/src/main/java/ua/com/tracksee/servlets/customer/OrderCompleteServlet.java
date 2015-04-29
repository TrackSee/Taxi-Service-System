package ua.com.tracksee.servlets.customer;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.ordermanager.TaxiOrderBean;
import ua.com.tracksee.logic.exception.OrderException;

import javax.ejb.EJB;
import javax.mail.MessagingException;
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
@WebServlet("/orderComplete")
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String,String> inputData=new HashMap<String, String>();
        try {
            inputData.put("phoneNumber", req.getParameter("phoneNumber"));
            inputData.put("email",req.getParameter("email"));
            inputData.put("addressOrigin", req.getParameter("addressOrigin"));
            inputData.put("addressDestination", req.getParameter("addressDestination"));
            inputData.put("price",req.getParameter("price"));
            inputData.put("orderStatus", ORDER_STATUS);
            if(req.getParameter("carCategory")!=null) {
                inputData.put("carCategory", req.getParameter("carCategory"));
            }else{
                inputData.put("carCategory","userCar");
            }
            inputData.put("wayOfPayment",req.getParameter("wayOfPayment"));
            inputData.put("driverSex",req.getParameter("driverSex"));
            inputData.put("service",req.getParameter("service"));
            if(req.getParameter("musicStyle")!=null) {
                inputData.put("musicStyle", req.getParameter("musicStyle"));
            }else{
                inputData.put("musicStyle", "default");
            }
            if(inputData.put("animalTransportation",req.getParameter("animalTransportation"))!=null){
                inputData.put("animalTransportation",req.getParameter("animalTransportation"));
            }else {
                inputData.put("animalTransportation","false");
            }

            if(inputData.put("freeWifi",req.getParameter("freeWifi"))!=null){
                inputData.put("freeWifi",req.getParameter("freeWifi"));
            }else{
                inputData.put("freeWifi","false");
            }
            if(inputData.put("smokingDriver",req.getParameter("smokingDriver"))!=null){
                inputData.put("smokingDriver",req.getParameter("smokingDriver"));
            }else{
                inputData.put("smokingDriver","false");
            }
            if(inputData.put("airConditioner",req.getParameter("airConditioner"))!=null){
                inputData.put("airConditioner",req.getParameter("airConditioner"));
            }else{
                inputData.put("airConditioner","false");
            }
            inputData.put("description",req.getParameter("description"));


            Integer trackingNumber=controller.makeOrder(inputData);

            req.setAttribute("successAlert","<div class=\"alert alert-success\" role=\"alert\"  >" +
                    "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "  <span aria-hidden=\"true\">&times;</span></button><h3>Your order accepted for further " +
                    "processing successfully and you was assigned to such tracking number:"
                    + trackingNumber + "</h3></div>");
            req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Database unavailable.");
        } catch (OrderException | MessagingException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }
    }
}
