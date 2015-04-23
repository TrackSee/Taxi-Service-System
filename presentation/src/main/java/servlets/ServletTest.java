package servlets;

import com.netcracker.bootcamp.tracksee.entity.Address;
import com.netcracker.bootcamp.tracksee.logic.OrderBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Sasha on 4/23/15.
 */
@WebServlet(urlPatterns = "ServletTest")
public class ServletTest extends HttpServlet {
    @EJB
    OrderBean been;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            been.makeOrder(123,"sasha@kkk",new Address(),new Address());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
