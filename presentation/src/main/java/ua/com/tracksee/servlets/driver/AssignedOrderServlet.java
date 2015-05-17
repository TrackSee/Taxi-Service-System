package ua.com.tracksee.servlets.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.facade.DriverFacade;
import ua.com.tracksee.logic.facade.OrderFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Maria Komar on 30.04.2015.
 */
@WebServlet("driver/assigned-order")
public class AssignedOrderServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @EJB
    private OrderFacade orderFacade;
    @EJB
    private DriverFacade driverFacade;
    String timeCarArrive;
    String trackingNumber;
    String orderStatus;
    int id;
    int inProgressStatus;
    boolean statusBoolean = false;
    boolean alert = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = (int) req.getSession().getAttribute("userId");
        trackingNumber = req.getParameter("trackingNumber");
        req.setAttribute("status", statusBoolean);
        req.setAttribute("orders", orderFacade.getAssignedOrders(id, 1));
        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = (int) req.getSession().getAttribute("userId");
        trackingNumber = req.getParameter("trackingNumber");
        orderStatus = req.getParameter("orderStatus");
        statusBoolean = Boolean.valueOf(req.getParameter("status"));

          String  timeCarArriveCustomerDate = req.getParameter("arriveDateCustomer");
          String  timeCarArriveDate = req.getParameter("arriveDate");

        if(timeCarArriveCustomerDate!=null){
            Timestamp carArriveTimeTimestamp=null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date parsedDate = dateFormat.parse(timeCarArriveCustomerDate);
                carArriveTimeTimestamp= new java.sql.Timestamp(parsedDate.getTime());
               // if(carArriveTimeTimestamp.after(testTime)) {
                    timeCarArrive = carArriveTimeTimestamp.toString();
                //}
            } catch (ParseException e) {
                System.out.println("Invalid or missing date, cannot be parsed 1");
               // carArriveTimeTimestamp = null;

                if(timeCarArriveDate!=null){
                    Timestamp carArriveTimeTimestamp2=null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date parsedDate = dateFormat.parse(timeCarArriveDate);
                        carArriveTimeTimestamp2= new java.sql.Timestamp(parsedDate.getTime());
                        //if(carArriveTimeTimestamp2.after(testTime)) {
                            //timeCarArrive = carArriveTimeTimestamp2.toString();
                        //}
                        timeCarArrive=carArriveTimeTimestamp2.toString();
                    } catch (ParseException e1) {
                        System.out.println("Invalid or missing date, cannot be parsed 2");
                       // carArriveTimeTimestamp2 = null;
                    } catch (javax.ejb.EJBTransactionRolledbackException e2){
                        req.setAttribute("alert", alert = true);
                        req.setAttribute("orders", orderFacade.getAssignedOrders(id, 1));
                        req.setAttribute("status", statusBoolean);
                        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
                    }
                    catch (NullPointerException e3){
                        req.setAttribute("alert", alert = true);
                        req.setAttribute("orders", orderFacade.getAssignedOrders(id, 1));
                        req.setAttribute("status", statusBoolean);
                        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
                    }
                }

            }
            System.out.println("First"+timeCarArrive);
        }
//        else if(timeCarArriveDate!=null){
//            Timestamp carArriveTimeTimestamp2=null;
//            try {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                Date parsedDate = dateFormat.parse(timeCarArriveDate);
//                carArriveTimeTimestamp2= new java.sql.Timestamp(parsedDate.getTime());
//                //if(carArriveTimeTimestamp2.after(testTime)) {
//                    timeCarArrive = carArriveTimeTimestamp2.toString();
//                //}
//                timeCarArrive=carArriveTimeTimestamp2.toString();
//            } catch (ParseException e) {
//                System.out.println("Invalid or missing date, cannot be parsed 2");
//                carArriveTimeTimestamp2 = null;
//
//            }
//            catch (Exception e1){
//                req.setAttribute("alert", alert = true);
//                req.setAttribute("orders", orderFacade.getAssignedOrders(id, 1));
//                req.setAttribute("status", statusBoolean);
//                req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
//            }
//            //System.out.println("Second"+timeCarArrive);
//        }
//        System.out.println("RESSUPERFINAL"+timeCarArrive);



        if(orderStatus != null) {
            if(orderStatus.equals("Refused")){
                orderFacade.setRefusedOrder(trackingNumber);
            } else if(orderStatus.equals("In progress")){
                inProgressStatus = orderFacade.setInProgressOrder(trackingNumber);
                if(inProgressStatus ==0){
                    statusBoolean = true;
                }
            }
            else if(orderStatus.equals("Completed")){
                orderFacade.setCompletedOrder(trackingNumber);
            }
            else if(orderStatus.equals("Toqueue")){
                orderFacade.setToQueueOrder(trackingNumber);
            }
            else if(orderStatus.equals("Assign")) {
                try {
                    orderFacade.setAssignOrder(id, trackingNumber, timeCarArrive);
                }
                catch (javax.ejb.EJBTransactionRolledbackException e2){
                    UserEntity driver = driverFacade.getUserById(id);
                    List<TaxiOrderEntity> orders = orderFacade.getAvailableOrders(driver, 1);
                    req.setAttribute("alert", alert = true);
                    req.setAttribute("orders", orders);
                    req.setAttribute("pagesCount", orderFacade.getOrdersPagesCount(id));
                    req.getRequestDispatcher("/WEB-INF/driver/driverIndex.jsp").forward(req,resp);
                }
            }
        }

        timeCarArrive = null;

        //timeCarArrive = req.getParameter("carArriveTime");
        req.setAttribute("orders", orderFacade.getAssignedOrders(id, 1));
        req.setAttribute("status", statusBoolean);
        //req.setAttribute("firsCust", req.getParameter("arriveDateCustomer"));
        //req.setAttribute("firsDrive", req.getParameter("arriveDate"));
        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
    }
}
