package  ua.com.tracksee.servlets.driver;

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
import java.util.List;

/**
 * Created by Maria Komar on 19.04.2015.
 */

@WebServlet({"/driver/free-orders", "/driver/"})
public class DriverIndexServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    int id;

    @EJB
    private OrderFacade orderFacade;
    @EJB
    private DriverFacade driverFacade;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = (int) req.getSession().getAttribute("userId");
        UserEntity driver = driverFacade.getUserById(id);
        List<TaxiOrderEntity> orders = orderFacade.getAvailableOrders(driver, 1);
        req.setAttribute("orders", orders);
        req.setAttribute("pagesCount", orderFacade.getOrdersPagesCount(id));
        req.getRequestDispatcher("/WEB-INF/driver/driverIndex.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = (int) req.getSession().getAttribute("userId");
        Integer pageNumber = null;
        try {
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
            if(pageNumber > orderFacade.getOrdersPagesCount(id)){
                pageNumber = 1;
                logger.warn("wrong page was request on /driver/free-orders");
            }
        } catch (NumberFormatException e){
            pageNumber = 1;
            logger.warn("wrong page was request on /admin/drivers");
        }
        UserEntity driver = driverFacade.getUserById(id);
        List<TaxiOrderEntity> orders = orderFacade.getAvailableOrders(driver, pageNumber);
        req.setAttribute("orders", orders);
        req.setAttribute("pagesCount", orderFacade.getOrdersPagesCount(id));
        resp.getWriter().write(getJsonFromList(orders));
    }

    /**
     *
     * @param orders list of orders to convert into JSON
     * @return String of JSON
     */
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
