package ua.com.tracksee.servlets.customer;

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
 * Created by Vadym_Akymov on 26.04.15.
 */
@WebServlet("/customer/profile")
public class CustomerProfileServlet extends HttpServlet {
    @EJB
    private TaxiOrderBean taxiOrderBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TaxiOrderEntity> orders = taxiOrderBean.getOrdersPerPage(1);
        request.setAttribute("pagesCount", taxiOrderBean.getTaxiOrderPagesCount());
        request.setAttribute("pageNumber", 1);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/customer/customerProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
