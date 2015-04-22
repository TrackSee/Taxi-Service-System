package ua.com.tracksee.servlets.admin;

import ua.com.tracksee.config.ConfigManagerBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by byte on 4/18/15.
 */

@WebServlet(urlPatterns = "/blockAccount")
public class BlockAccountServlet extends HttpServlet{

    @EJB
    private ConfigManagerBean configManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String page = configManager.getString("path.page.block_account");

        //RequestDispatcher dispatcher = req.getRequestDispatcher(page);

        //dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
