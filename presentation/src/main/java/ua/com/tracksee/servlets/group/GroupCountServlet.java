package ua.com.tracksee.servlets.group;

import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import ua.com.tracksee.logic.GroupBean;
import ua.com.tracksee.logic.group.GroupSelectCountAction;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Igor on 08.05.2015.
 */
@WebServlet("groupCount")
public class GroupCountServlet extends HttpServlet implements GroupConstants{
    @EJB
    private GroupBean groupBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupName = req.getParameter(GROUP_NAME_ALIAS);
        String userEmail = req.getParameter(USER_EMAIL_ALIAS);

        GroupSelectCountAction selectCountAction =
                GroupSelectCountAction.fromString(req.getParameter(SELECT_COUNT_ACTION_ALIAS));

        Integer count = groupBean.executeSelectCount(selectCountAction, groupName, userEmail);

        String json = "[{\"count\":" + count + "}]";
        System.out.println(json);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
    }
}
