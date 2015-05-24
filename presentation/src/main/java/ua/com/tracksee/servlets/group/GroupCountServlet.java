package ua.com.tracksee.servlets.group;

import ua.com.tracksee.logic.facade.AdminFacade;
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
@WebServlet("admin/groupCount")
public class GroupCountServlet extends HttpServlet implements GroupConstants{
    @EJB
    private AdminFacade adminFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String groupName = req.getParameter(GROUP_NAME_ALIAS);
        String userEmail = req.getParameter(USER_EMAIL_ALIAS);

        GroupSelectCountAction selectCountAction =
                GroupSelectCountAction.fromString(req.getParameter(SELECT_COUNT_ACTION_ALIAS));

        Integer count = adminFacade.groupExecuteSelectCount(selectCountAction, groupName, userEmail);

        String json = "[{\"count\":" + count + "}]";
        resp.setContentType("application/json");
        resp.getWriter().print(json);
    }
}
