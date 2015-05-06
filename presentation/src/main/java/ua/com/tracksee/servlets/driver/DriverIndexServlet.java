package servlets.driver;

import org.codehaus.jackson.map.ObjectMapper;
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
        List<TaxiOrderEntity> orders = driverOrderBean.getAvailableOrders(driver, 1);
        req.setAttribute("orders", orders);
        req.setAttribute("pagesCount", driverOrderBean.getOrdersPagesCount(id));
        req.getRequestDispatcher("/WEB-INF/driver/driverIndex.jsp").forward(req,resp);
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
