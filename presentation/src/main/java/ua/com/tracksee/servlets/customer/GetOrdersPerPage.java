package ua.com.tracksee.servlets.customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.logic.facade.CustomerFacade;
import ua.com.tracksee.logic.facade.OrderStatusBO;

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
    private static final Logger logger = LogManager.getLogger();
    private @EJB CustomerFacade customerFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String dataType = req.getParameter("type");
        OrderStatusBO orderStatusBO = Enum.valueOf(OrderStatusBO.class, dataType.toUpperCase());
        Integer pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        Integer userID = (Integer) req.getSession().getAttribute("userId");
        List<TaxiOrderEntity> orders = customerFacade.getOrdersPerPage(orderStatusBO, userID, pageNumber);
        ObjectMapper mapper = new ObjectMapper();
        String ordersInJson = mapper.writeValueAsString(orders);
        resp.getWriter().write(ordersInJson);
    }
}
