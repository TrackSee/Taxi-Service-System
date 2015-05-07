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
    String orderStatus;
    int id = 6;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        trackingNumber = req.getParameter("trackingNumber");
        orderStatus = req.getParameter("orderStatus");
        if(orderStatus != null) {
            if(orderStatus.equals("Refused")){
                driverOrderBean.setRefusedOrder(trackingNumber);
            } else if(orderStatus.equals("In progress")){
                driverOrderBean.setInProgressOrder(trackingNumber);
            }
            else if(orderStatus.equals("Completed")){
                driverOrderBean.setCompletedOrder(trackingNumber);
            }
        }
        timeCarArrive = req.getParameter("carArriveTime");
        if(trackingNumber != null) {
            driverOrderBean.setAssignOrder(id, trackingNumber, timeCarArrive);
            }
        req.setAttribute("orders", driverOrderBean.getAssignedOrders(id));
        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        trackingNumber = req.getParameter("trackingNumber");
        orderStatus = req.getParameter("orderStatus");
        System.out.print(trackingNumber);
        System.out.print(orderStatus);
    }

}
