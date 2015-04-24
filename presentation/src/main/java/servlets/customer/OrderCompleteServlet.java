package servlets.customer;


import com.netcracker.bootcamp.tracksee.entity.TaxiPrice;
import com.netcracker.bootcamp.tracksee.logic.TaxiOrderBean;
import com.netcracker.tracksee.entities.Address;
import com.netcracker.tracksee.entities.TaxiOrder;
import com.netcracker.tracksee.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/orderComplete")
public class OrderCompleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req,resp);
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
        HashMap<String,Object> inputData=new HashMap<String, Object>();
        try {
            inputData.put("phoneNumber", req.getParameter("phoneNumber"));
            inputData.put("email",req.getParameter("email"));
            inputData.put("addressFrom", req.getParameter("addressFrom"));
            inputData.put("addressTo", req.getParameter("addressTo"));
            inputData.put("carCategory",req.getParameter("carCategory"));
            inputData.put("wayOfPayment",req.getParameter("wayOfPayment"));
            inputData.put("driverSex",req.getParameter("driverSex"));
            inputData.put("service",req.getParameter("service"));
            inputData.put("musicStyle",req.getParameter("musicStyle"));
            inputData.put("animalTransportation",req.getParameter("animalTransportation"));
            inputData.put("freeWifi",req.getParameter("freeWifi"));
            inputData.put("smokingDriver",req.getParameter("smokingDriver"));
            inputData.put("airConditioner",req.getParameter("airConditioner"));
            inputData.put("airConditioner",req.getParameter("orderPrice"));


            controller.makeOrder(inputData);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Database unavailable.");
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            resp.sendRedirect("/order?error=true");
        }
    }
}
