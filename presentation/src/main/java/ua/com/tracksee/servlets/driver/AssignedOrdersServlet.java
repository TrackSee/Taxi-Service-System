package ua.com.tracksee.servlets.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.driver.DriverOrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maria Komar on 30.04.2015.
 */
@WebServlet("driver/assigned-order")
public class AssignedOrdersServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @EJB
    private DriverOrderBean driverOrderBean;
    String timeCarArrive = "2015-06-25 00:00:00.000000";
    String trackingNumber;
    int id = 6;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 6;
        trackingNumber = req.getParameter("trackingNumber");
        //timeCarArrive = req.getParameter("carArriveTime");
        if(trackingNumber != null) {
            driverOrderBean.setAssignOrder(id, trackingNumber, timeCarArrive);
        }
        req.setAttribute("orders", driverOrderBean.getAssignedOrders(id));
        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
    }
}
