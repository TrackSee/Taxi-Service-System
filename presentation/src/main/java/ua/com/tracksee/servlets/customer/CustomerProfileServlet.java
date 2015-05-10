package ua.com.tracksee.servlets.customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Created by Vadym_Akymov on 26.04.15.
 */
@WebServlet("/customer")
public class CustomerProfileServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private @EJB CustomerFacade customerFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataType = request.getParameter("type");
        //completed TO are on page by default
        if(dataType == null || dataType.isEmpty()){
            dataType = "completed";
        }
        dataType = dataType.toUpperCase();
        Integer userID = (Integer) request.getSession().getAttribute("userId");
        request.setAttribute("type", dataType.toLowerCase());
        OrderStatusBO orderStatusBO = Enum.valueOf(OrderStatusBO.class, dataType);
        List<TaxiOrderEntity> orders = customerFacade.getOrdersPerPage(orderStatusBO, userID, 1);
        request.setAttribute("orders", orders);
        request.setAttribute("pageNumber", 1);
        request.setAttribute("pagesCount", customerFacade.getOrdersPagesCount(userID, orderStatusBO));
        request.getRequestDispatcher("/WEB-INF/customer/customerProfile.jsp").forward(request, response);
    }
}
