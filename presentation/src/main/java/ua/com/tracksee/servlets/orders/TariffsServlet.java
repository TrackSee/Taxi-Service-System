package ua.com.tracksee.servlets.orders;

import ua.com.tracksee.servlets.PageAddresses;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/tariffs")
public class TariffsServlet extends HttpServlet implements PageAddresses {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "tariffs");
        req.getRequestDispatcher(TARIFFS_PAGE).forward(req,resp);
    }
}
