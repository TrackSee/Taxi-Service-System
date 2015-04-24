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

/**
 * @author Sharaban Sasha
 */
@WebServlet("/orderComplete")
public class OrderCompleteServlet extends HttpServlet {
    private static final String ORDER_STATUS = "QUEUED";

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
        try {
            System.out.println("Servlet");
            TaxiOrder taxiOrder=new TaxiOrder();
            User user=new User();
            TaxiPrice taxPrice=new TaxiPrice();

            long phoneNumber =Long.parseLong(req.getParameter("phoneNumber"));
            user.setPhone(phoneNumber);

            String email = req.getParameter("email");
            user.setEmail(email);

            Address addressFrom =new Address();
            addressFrom.setAddress(req.getParameter("addressFrom"));

            Address addressTo = new Address();
            addressTo.setAddress(req.getParameter("addressTo"));

            taxiOrder.setStatus(ORDER_STATUS);
            taxiOrder.setCarCategory(req.getParameter("carCategory"));
            taxiOrder.setWayOfPayment(req.getParameter("wayOfPayment"));
            taxiOrder.setDriverSex(req.getParameter("driverSex"));
            taxiOrder.setService(req.getParameter("service"));
            taxiOrder.setMusicStyle(req.getParameter("musicStyle"));
            taxiOrder.setAnimalTransportation(Boolean.parseBoolean(req.getParameter("animalTransportation")));
            taxiOrder.setFreeWifi(Boolean.parseBoolean(req.getParameter("freeWifi")));
            taxiOrder.setSmokingDriver(Boolean.parseBoolean(req.getParameter("smokingDriver")));
            taxiOrder.setAirConditioner(Boolean.parseBoolean(req.getParameter("airConditioner")));
            taxiOrder.setComment(req.getParameter("comments"));


            //TODO calculating taxi price
            taxiOrder.setPrice(1.0);


            controller.makeOrder(taxiOrder,user,addressFrom,addressTo);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Database unavailable.");
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            resp.sendRedirect("/order?error=true");
        }
    }
}
