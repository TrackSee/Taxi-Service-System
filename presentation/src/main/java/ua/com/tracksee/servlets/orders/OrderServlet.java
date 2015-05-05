package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.json.TaxiOrderDTO;
import ua.com.tracksee.logic.PriceCalculatorBean;
import ua.com.tracksee.logic.TaxiOrderBean;
import ua.com.tracksee.logic.exception.OrderException;

import javax.ejb.EJB;
import javax.mail.MessagingException;
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
 * @author Sharaban Sasha
 * @author Ruslan Gunavardana
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    /**
     * Order status is QUEUED  because
     * the orders received from the page will
     * always have the status QUEUED
     */
    private static final String ORDER_STATUS = "QUEUED";
    private static final Logger logger = LogManager.getLogger();

    private @EJB TaxiOrderBean taxiOrderBean;
    private @EJB PriceCalculatorBean priceCalculatorBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "order");
        req.getRequestDispatcher("/WEB-INF/customer/order.jsp").forward(req,resp);
    }

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

            /**
            * This data is set by default in fast order
            */
            inputData.put("arriveDate", req.getParameter("arriveDate"));
            inputData.put("endDate", req.getParameter("endDate"));
            //TODO calculationg distance, now for test it's 10
            inputData.put("distance","10");

            inputData.put("arriveDate","");
            inputData.put("endDate","");
            inputData.put("carCategory","economyClass");
            inputData.put("wayOfPayment","cash");
            inputData.put("driverSex","male");
            inputData.put("service","default");
            inputData.put("musicStyle", "default");
            inputData.put("animalTransportation","false");
            inputData.put("freeWifi","false");
            inputData.put("smokingDriver","false");
            inputData.put("airConditioner","false");
            inputData.put("description","");

            Long trackingNumber = taxiOrderBean.makeOrder(inputData);

            req.setAttribute("successAlert", "<div class=\"alert alert-success\" role=\"alert\"  >" +
                    "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "  <span aria-hidden=\"true\">&times;</span></button><h3>Your order accepted for further " +
                    "processing successfully and you was assigned to such tracking number:"
                    + trackingNumber + "</h3></div>");
            req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
        } catch (OrderException | MessagingException| NullPointerException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
        }
    }
}

