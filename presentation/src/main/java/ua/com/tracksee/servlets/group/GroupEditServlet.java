package ua.com.tracksee.servlets.group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Igor Gula on 04.05.2015.
 */
@WebServlet(name = "GroupEditServlet", urlPatterns = {"/GroupEditServlet"})
public class GroupEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/group/groupEdit.jsp").forward(req,resp);
    }
}