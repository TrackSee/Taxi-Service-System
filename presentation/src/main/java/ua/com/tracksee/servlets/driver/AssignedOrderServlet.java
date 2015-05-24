package ua.com.tracksee.servlets.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
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
@WebServlet("/driver/assigned-order")
public class AssignedOrderServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    private @EJB OrderFacade orderFacade;
    private @EJB DriverFacade driverFacade;
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
        req.setAttribute("pagesCount", orderFacade.getOrdersPagesCountAssigned(id));
        req.setAttribute("pagenumber", 1);
        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = (int) req.getSession().getAttribute("userId");
        trackingNumber = req.getParameter("trackingNumber");
        orderStatus = req.getParameter("orderStatus");
        statusBoolean = Boolean.valueOf(req.getParameter("status"));
        String pageParam = req.getParameter("pagenumber");
        Integer pagenumber;

        //check pageNumber
        try {
            pagenumber = Integer.parseInt(req.getParameter("pagenumber"));
            if(pagenumber > orderFacade.getOrdersPagesCountAssigned(id).intValue()){
                pagenumber = 1;
                logger.warn("wrong page was request");
            }
        } catch (NumberFormatException e){
            pagenumber = 1;
            logger.warn("wrong page was request");
        }

          String  timeCarArriveCustomerDate = req.getParameter("arriveDateCustomer");
          String  timeCarArriveDate = req.getParameter("arriveDate");

        if(timeCarArriveCustomerDate!=null){
            Timestamp carArriveTimeTimestamp;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date parsedDate = dateFormat.parse(timeCarArriveCustomerDate);
                carArriveTimeTimestamp= new java.sql.Timestamp(parsedDate.getTime());
                    timeCarArrive = carArriveTimeTimestamp.toString();
            } catch (ParseException e) {
                System.out.println("Invalid or missing date, cannot be parsed 1");

                if(timeCarArriveDate!=null){
                    Timestamp carArriveTimeTimestamp2=null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date parsedDate = dateFormat.parse(timeCarArriveDate);
                        carArriveTimeTimestamp2= new java.sql.Timestamp(parsedDate.getTime());
                        timeCarArrive=carArriveTimeTimestamp2.toString();
                    } catch (ParseException e1) {
                        System.out.println("Invalid or missing date, cannot be parsed 2");
                    } catch (javax.ejb.EJBTransactionRolledbackException | NullPointerException e2){
                        req.setAttribute("alert", alert = true);
                        req.setAttribute("orders", orderFacade.getAssignedOrders(id, 1));
                        req.setAttribute("status", statusBoolean);
                        req.getRequestDispatcher("/WEB-INF/driver/assignedOrder.jsp").forward(req,resp);
                    }
                }

            }
            System.out.println("First"+timeCarArrive);
        }

        if (orderStatus != null) {
            switch (orderStatus) {
                case "Refused":
                    orderFacade.setRefusedOrder(trackingNumber);
                    break;
                case "In progress":
                    inProgressStatus = orderFacade.setInProgressOrder(trackingNumber);
                    if (inProgressStatus == 0) {
                        statusBoolean = true;
                    }
                    break;
                case "Completed":
                    orderFacade.setCompletedOrder(trackingNumber);
                    break;
                case "Toqueue":
                    orderFacade.setToQueueOrder(trackingNumber);
                    break;
                case "Assign":
                    try {
                        orderFacade.setAssignOrder(id, trackingNumber, timeCarArrive);
                    } catch (javax.ejb.EJBTransactionRolledbackException e2) {
                        UserEntity driver = driverFacade.getUserById(id);
                        List<TaxiOrderEntity> orders = orderFacade.getAvailableOrders(driver, 1);
                        req.setAttribute("alert", alert = true);
                        req.setAttribute("orders", orders);
                        req.setAttribute("pagesCount", orderFacade.getOrdersPagesCountCompleted(id));
                        req.getRequestDispatcher("/WEB-INF/driver/driverIndex.jsp").forward(req, resp);
                        resp.getWriter().write(getJsonFromList(orders));
                    }
                    break;
            }
        }

        timeCarArrive = null;
        resp.getWriter().append(Boolean.toString(statusBoolean));
    }

    private String getJsonFromList(List<TaxiOrderEntity> orders){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(orders);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }
}
