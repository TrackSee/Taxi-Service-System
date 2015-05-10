package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.json.TaxiOrderDTO;
import ua.com.tracksee.logic.OrderCancellationBean;
import ua.com.tracksee.logic.TaxiOrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class return order.jsp,
 * get data from this page and send it to TaxiOrderBean.
 *
 * @author Sharaban Sasha
 * @author Ruslan Gunavardana
 */
@WebServlet("/orderComplete")
public class OrderServlet extends HttpServlet {
    /**
     * Order status is QUEUED  because
     * the orders received from the page will
     * always have the status QUEUED
     */
    private static final String ORDER_STATUS = "QUEUED";
    private static final Logger logger = LogManager.getLogger();

    private
    @EJB
    TaxiOrderBean taxiOrderBean;
    private
    @EJB
    OrderCancellationBean orderCancellationBean;


    private String alertBeginAndType="<div class=\"alert alert-success\"";
    private String alertBody = " role=\"alert\">\n" +
            "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
            "                    <span aria-hidden=\"true\">&times;</span></button><h3>";
    private String alertTrackButton="</h3><a class=\"btn btn-large btn-success\" href=\"orderInfo\"><h4>" +
            "Track your taxi order</h4></a>";
    private String alertSuccessMessage=" Your order accepted for further processing successfully and you was assigned" +
            " to such tracking number: ";
    private String alertEnd = "</div>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/customer/order.jsp").forward(req, resp);
    }

    /**
    *@author Ruslan Gunavardana
    */
    private void ruslanMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            String line;
            do {
                line = reader.readLine();
                sb.append(line).append("\n");
            } while (line != null);
        } catch (IOException e){
            logger.warn("Cannot get JSON from POST /order");
        }
        TaxiOrderDTO orderDTO = null;

        orderDTO = mapper.readValue(sb.toString(), TaxiOrderDTO.class);

        HttpSession session = req.getSession(false);
        Integer userId = session != null? (Integer) session.getAttribute("userId") : null;
        if (userId != null) {
            taxiOrderBean.createAuthorisedOrder(userId, orderDTO);
        } else {
            //TODO create order without signup merge
        }
    }
    /**
     *@author Sharaban Sasha
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "orderInformation");
        HashMap<String, String> inputData = new HashMap<String, String>();
        try {
            inputData.put("phoneNumber", req.getParameter("phoneNumber"));
            inputData.put("email", req.getParameter("email"));
            inputData.put("addressOrigin", req.getParameter("addressOrigin"));
            inputData.put("addressDestination", req.getParameter("addressDestination"));
            inputData.put("orderStatus", ORDER_STATUS);
            //TODO calculationg distance, now for test it's 10
            inputData.put("distance", "10");

            inputData.put("arriveDate", req.getParameter("arriveDate"));
            inputData.put("endDate", req.getParameter("endDate"));

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


            if (orderCancellationBean.checkBlackListByUserEmail(inputData.get("email"))) {
                req.setAttribute("showError", "Show");
            } else {
                Long trackingNumber = taxiOrderBean.makeOrder(inputData);
                req.setAttribute("trackingNumber", trackingNumber);
                req.setAttribute("showSuccess",alertBeginAndType+alertBody+alertSuccessMessage+trackingNumber+
                        alertTrackButton+alertEnd);
                req.setAttribute("hideOrderTrack", "hidden=\"hidden\"");

            }

            req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }

    }
}
