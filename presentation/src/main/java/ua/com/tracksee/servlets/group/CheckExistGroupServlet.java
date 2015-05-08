package ua.com.tracksee.servlets.group;

import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import ua.com.tracksee.logic.GroupBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Igor on 27.04.2015.
 */
@WebServlet("admin/groupExist")
public class CheckExistGroupServlet extends HttpServlet implements GroupConstants{
    @EJB
    private GroupBean groupBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String groupName = req.getParameter(GROUP_NAME_ALIAS);

        boolean exist = groupBean.existsGroup(groupName);

        String json = "[{\"exist\":" + exist + "}]";
        System.out.println(json);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
    }
}
