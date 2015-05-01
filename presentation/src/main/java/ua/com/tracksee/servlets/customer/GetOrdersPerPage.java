package ua.com.tracksee.servlets.customer;

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.logic.TaxiOrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vadym Akymov on 01.05.15.
 * getting orders in json
 */
@WebServlet("/customer/get-orders")
public class GetOrdersPerPage extends HttpServlet {
    @EJB
    private TaxiOrderBean taxiOrderBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        List<TaxiOrderEntity> orders = taxiOrderBean.getOrdersPerPage(pageNumber);
        ObjectMapper mapper = new ObjectMapper();
        String ordersInJson = mapper.writeValueAsString(orders);
        resp.getWriter().write(ordersInJson);
    }
}
