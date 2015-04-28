package ua.com.tracksee.servlets.customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.json.TaxiOrderDTO;
import ua.com.tracksee.logic.OrderBean;
import ua.com.tracksee.logic.ordermanager.TaxiOrderBean;

import javax.ejb.EJB;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 * @author Sharaban Sasha
 * @author Ruslan Gunavardana
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private @EJB TaxiOrderBean taxiOrderBean;
    private @EJB OrderBean orderBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "order");
        req.getRequestDispatcher("/WEB-INF/customer/order.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            String line;
            do {
                line = reader.readLine();
                sb.append(line).append("\n");
            } while (line != null);
        } catch (IOException e){
            logger.warn("Cannot get JSON from POST /order");
        }
        TaxiOrderDTO orderDTO = mapper.readValue(sb.toString(), TaxiOrderDTO.class);

        HttpSession session = req.getSession(false);
        Integer userId = session != null? (Integer) session.getAttribute("userId") : null;
        if (userId != null) {
            orderBean.createAuthorisedOrder(userId, orderDTO);
        } else {
            //TODO create order without signup merge
        }
    }
//    try {
//        String addressOrigin=req.getParameter("addressOrigin");
//        String addressDestination=req.getParameter("addressDestination");
//        long price = controller.calculatePrice(addressOrigin, addressDestination);
//        if (price>0) {
//            req.setAttribute("price", price);
//            req.setAttribute("addressOrigin", req.getParameter("addressOrigin"));
//            req.setAttribute("addressDestination", req.getParameter("addressDestination"));
//            req.getRequestDispatcher("/WEB-INF/customer/orderComplete.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
//        }
//    } catch (NumberFormatException e) {
//        logger.error(e.getMessage());
//        req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
//    }
}
