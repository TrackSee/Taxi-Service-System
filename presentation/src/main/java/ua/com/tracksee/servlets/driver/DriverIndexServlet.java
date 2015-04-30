package servlets.driver;

import ua.com.tracksee.dao.postrgresql.UserDAOBean;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.logic.driver.DriverBean;
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
 * Created by Maria Komar on 19.04.2015.
 */

@WebServlet("/driver/free-orders")
public class DriverIndexServlet extends HttpServlet {
    int id = 6;

    @EJB
    private DriverOrderBean driverOrderBean;
    @EJB
    private DriverBean driverBean;
//    @EJB
//    UserDAOBean userDAOBean;
//    @EJB
//    ServiceUserEntity serviceUserEntity;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceUserEntity driver = driverBean.getUserById(id);
        List<TaxiOrderEntity> orders = driverOrderBean.getAvailableOrders(driver);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/driver/driverIndex.jsp").forward(req,resp);
    }
}
