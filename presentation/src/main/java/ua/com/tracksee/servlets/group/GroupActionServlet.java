package ua.com.tracksee.servlets.group;

import com.google.gson.Gson;
import ua.com.tracksee.entity.Group;
import ua.com.tracksee.entity.Role;
import ua.com.tracksee.logic.GroupBean;
import ua.com.tracksee.logic.group.GroupAction;

import javax.ejb.EJB;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Igor Gula on 19.04.2015.
 */
@WebServlet(name = "GroupActionServlet", urlPatterns = {"/GroupActionServlet"})
public class GroupActionServlet extends HttpServlet {

    private static final String ACTION_ALIAS = "action";
    private static final String GROUP_NAME_ALIAS = "groupName";
    private static final String GROUP_ROLE_ALIAS = "groupRole";
    private static final String ERROR_MESSAGE = "errorMessage";

    @EJB
    private GroupBean groupBean;

    private static final String PAGE_NUMBER_ALIAS = "pageNumber";
    private static final String PAGE_SIZE_ALIAS = "pageSize";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("====================GET");
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String actionString = request.getParameter(ACTION_ALIAS);
        GroupAction action = GroupAction.fromString(actionString);

        String name = request.getParameter(GROUP_NAME_ALIAS);
        String role = request.getParameter(GROUP_ROLE_ALIAS);

        try {
            switch (action) {
                case REMOVE_GROUP:
                    groupBean.removeGroup(name);
                    break;
                case UPDATE_GROUP:
                    groupBean.updateGroup(name, Role.fromString(role));
            }
        } catch (EntityNotFoundException e) {
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
        }

        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER_ALIAS));
        int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE_ALIAS));

        List<Group> groups = groupBean.getGroups(pageNumber, pageSize);
        groups.add(new Group("", groupBean.getGroupsCount()));

        String json = new Gson().toJson(groups);

        System.out.println(json);
        response.setContentType("application/json");
        response.getWriter().print(json);
    }

}
