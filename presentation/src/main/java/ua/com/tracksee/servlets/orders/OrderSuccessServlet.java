package ua.com.tracksee.servlets.orders;

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
@WebServlet("/orderSuccess")
public class OrderSuccessServlet extends HttpServlet implements OrderAttributes {
    private @EJB
    OrderFacade orderFacade;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "orderInfo");
        req.setAttribute(ORDER_SUCCESS, orderFacade.getSuccessAlert(ORDER_SUCCESS_MESSAGE_WITHOUT_TRACK_NUMB+
                ORDER_SUCCESS_TRACK_BUTTON));
        req.getRequestDispatcher(ORDER_SUCCESS_PAGE).forward(req,resp);
    }
}
