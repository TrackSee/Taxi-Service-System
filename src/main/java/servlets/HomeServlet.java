package servlets;

import com.netcracker.bootcamp.tracksee.logic.EmailBean;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vadym Akymov  on 14.04.15.
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {
    @EJB
    private EmailBean bean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            bean.sendRegistrationEmail(null, "123");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}