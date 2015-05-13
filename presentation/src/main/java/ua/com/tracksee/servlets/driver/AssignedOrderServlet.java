package ua.com.tracksee.servlets.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.facade.OrderFacade;

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
public class AssignedOrderServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @EJB
    private OrderFacade orderFacade;
    String timeCarArrive = "2015-06-25 00:00:00.000000";
    String trackingNumber;
    String orderStatus;
    int id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = (int) req.getSession().getAttribute("userId");
        trackingNumber = req.getParameter("trackingNumber");
//        orderStatus = req.getParameter("orderStatus");
//        if(orderStatus != null) {
//            if(orderStatus.equals("Refused")) {
//                driverOrderBean.setToQueueOrder(trackingNumber);
//            }
//            else if(orderStatus.equals("In progress")){
//                driverOrderBean.setInProgressOrder(trackingNumber);
//            }
//            else if(orderStatus.equals("Completed")){
//                driverOrderBean.setRefusedOrder(trackingNumber);
//            }
//            else if(orderStatus.equals("Toqueue")){
//                driverOrderBean.setCompletedOrder(trackingNumber);
//            }
//        }
        //timeCarArrive = req.getParameter("carArriveTime");
        if(trackingNumber != null) {
            orderFacade.setAssignOrder(id, trackingNumber, timeCarArrive);
        }
        req.setAttribute("orders", orderFacade.getAssignedOrders(id, 1));
        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = (int) req.getSession().getAttribute("userId");
        trackingNumber = req.getParameter("trackingNumber");
        orderStatus = req.getParameter("orderStatus");

        if(orderStatus != null) {
            if(orderStatus.equals("Refused")){
                orderFacade.setRefusedOrder(trackingNumber);
            } else if(orderStatus.equals("In progress")){
                //TODO get int if 0 then show some warning to driver
                orderFacade.setInProgressOrder(trackingNumber);
            }
            else if(orderStatus.equals("Completed")){
                orderFacade.setCompletedOrder(trackingNumber);
            }
            else if(orderStatus.equals("Toqueue")){
                orderFacade.setToQueueOrder(trackingNumber);
            }
        }

        //timeCarArrive = req.getParameter("carArriveTime");
        req.setAttribute("orders", orderFacade.getAssignedOrders(id, 1));
        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
    }
}
