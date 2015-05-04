package ua.com.tracksee.servlets.customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@WebServlet("/customer/dashboard")
public class CustomerProfileServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @EJB
    private TaxiOrderBean taxiOrderBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataType = request.getParameter("type");
        //old TO are on page by default
        if(dataType == null || dataType.isEmpty()){
            dataType = "old";
            System.out.println("default!");
        }
        System.out.println("dataType: "  + dataType);
        request.setAttribute("type", dataType);
        if ("old".equals(dataType)) {
            List<TaxiOrderEntity> orders = taxiOrderBean.getOldOrdersPerPage(1);
            request.setAttribute("pagesCount", taxiOrderBean.getOldTaxiOrderPagesCount());
            request.setAttribute("orders", orders);
        } else if("active".equals(dataType)){
            List<TaxiOrderEntity> orders = taxiOrderBean.getActiveOrdersPerPage(1);
            request.setAttribute("pagesCount", taxiOrderBean.getActiveTaxiOrderPagesCount());
            request.setAttribute("orders", orders);
        } else {
            logger.warn("wrong servlet params!");
            throw new IllegalArgumentException("wrong servlet params!");
        }
        request.setAttribute("pageNumber", 1);
        request.getRequestDispatcher("/WEB-INF/customer/customerProfile.jsp").forward(request, response);
    }
}
