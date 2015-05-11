package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.facade.OrderFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/orderRefuse")
public class OrderRefuseServlet extends HttpServlet {
    private @EJB OrderFacade orderFacade;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int trackingNumber=Integer.parseInt(req.getParameter("trackingNumber"));
            if(orderFacade.refuseOrder(trackingNumber)){
                req.setAttribute("showRefuseSuccess","Show");
            }else{
                req.setAttribute("showRefuseError","Show");
                req.setAttribute("trackingNumber",trackingNumber);
            }
            req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req, resp);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
        }

    }
}
