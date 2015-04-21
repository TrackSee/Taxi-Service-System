package servlets.customer;


import com.netcracker.bootcamp.tracksee.entity.Address;
import com.netcracker.bootcamp.tracksee.logic.OrderBean;
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
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req,resp);
    }
    private Logger logger;
    @EJB
    private OrderBean controller;

    @Override
    public void init() throws ServletException {
        super.init();
        logger = LogManager.getLogger();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long phoneNumber =Long.parseLong(req.getParameter("phoneNumber"));
            String email = req.getParameter("email");
            Address addressFrom =new Address();
            addressFrom.setAddress(req.getParameter("addressFrom"));
            Address addressTo = new Address();
            addressTo.setAddress(req.getParameter("addressTo"));
            System.out.println("addressTo");

            boolean orderState = controller.makeOrder(phoneNumber, email, addressFrom, addressTo);
            if (orderState) {
                resp.sendRedirect("/order?error=true");
            } else {
                req.getRequestDispatcher("/order?error=false").forward(req, resp);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Database unavailable.");
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            resp.sendRedirect("/order?error=true");
        }
    }
}
