package servlets.customer;


import com.netcracker.bootcamp.tracksee.entity.Address;
import com.netcracker.bootcamp.tracksee.logic.OrderBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/orderInfo")
public class OrderInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/orderInfo.jsp").forward(req,resp);
    }

}
