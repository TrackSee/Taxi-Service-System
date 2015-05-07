package ua.com.tracksee.servlets.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.logic.driver.DriverOrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Maria Komar on 20.04.2015.
 */

@WebServlet("driver/history-of-orders")
public class HistoryDriverServlet extends HttpServlet{
    private static Logger logger = LogManager.getLogger();

    int id = 6;

    @EJB
    private DriverOrderBean driverOrderBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaxiOrderEntity> orders = driverOrderBean.getHistoryOfOrders(id, 1);
        Map<Integer, String> addresses = driverOrderBean.getStringAddressForList(orders);
        req.setAttribute("orders", orders);
        req.setAttribute("pagesCount", driverOrderBean.getOrdersPagesCount(id));
        //req.setAttribute("addresses", addresses);
        //req.setAttribute("pagesCount", driverOrderBean.getOrdersPagesCount(id));
        req.getRequestDispatcher("/WEB-INF/driver/historyDriverTo.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("pageNumber");
        Integer pageNumber = null;
        //check pageNumber
        try {
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
            if(pageNumber > driverOrderBean.getOrdersPagesCount(id)){
                pageNumber = 1;
                logger.warn("wrong page was request");
            }
        } catch (NumberFormatException e){
            pageNumber = 1;
            logger.warn("wrong page was request");
        }
        List<TaxiOrderEntity> orders = driverOrderBean.getHistoryOfOrders(id, pageNumber);
        req.setAttribute("orders", orders);
        req.setAttribute("pagesCount", driverOrderBean.getOrdersPagesCount(id));
        resp.getWriter().write(getJsonFromList(orders));

//        List<TaxiOrderEntity> orders = driverOrderBean.getHistoryOfOrders(id, 1);
//        req.setAttribute("orders", orders);
//        req.setAttribute("pagesCount", driverOrderBean.getOrdersPagesCount(id));
//        req.getRequestDispatcher("/WEB-INF/driver/historyDriverTo.jsp").forward(req,resp);
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
