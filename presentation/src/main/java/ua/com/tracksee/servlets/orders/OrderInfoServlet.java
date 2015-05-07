package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.logic.OrderCancellationBean;

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
@WebServlet("/orderInfo")
public class OrderInfoServlet extends HttpServlet {
    private
    @EJB
    OrderCancellationBean orderCancellationBean;
    private static final Logger logger = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "orderInformation");
        req.getRequestDispatcher("/WEB-INF/customer/orderInfo.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int trackingNumber=Integer.parseInt(req.getParameter("trackingNumber"));
            boolean state=orderCancellationBean.cancelOrder(trackingNumber);
            if(state){
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
