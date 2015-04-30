package ua.com.tracksee.servlets.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

/**
 * Created by Maria Komar on 20.04.2015.
 */

@WebServlet("driver/history-of-orders")
public class HistoryDriverServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    int id = 6;

    @EJB
    private DriverOrderBean driverOrderBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaxiOrderEntity> orders = driverOrderBean.getHistoryOfOrders(id);
        req.setAttribute("orders", orders);
        //req.setAttribute("pagesCount", driverOrderBean.getOrdersPagesCount(id));
        req.getRequestDispatcher("/WEB-INF/driver/historyDriverTo.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaxiOrderEntity> orders = driverOrderBean.getHistoryOfOrders(id);
        req.setAttribute("orders", orders);
        req.setAttribute("pagesCount", driverOrderBean.getOrdersPagesCount(id));
        req.getRequestDispatcher("/WEB-INF/driver/historyDriverTo.jsp").forward(req,resp);
    }
}
