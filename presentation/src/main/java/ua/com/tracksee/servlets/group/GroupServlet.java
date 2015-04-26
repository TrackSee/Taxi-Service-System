package ua.com.tracksee.servlets.group;

/**
 * Created by Igor on 23.04.2015.
 */

import com.google.gson.Gson;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entity.Group;
import ua.com.tracksee.entity.Role;
import ua.com.tracksee.logic.GroupBean;
import ua.com.tracksee.logic.group.GroupSelectAction;
import ua.com.tracksee.logic.group.GroupSelectCountAction;
import ua.com.tracksee.logic.group.GroupUpdateAction;

import javax.ejb.EJB;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Igor Gula on 19.04.2015.
 */
@WebServlet(name = "GroupServlet", urlPatterns = {"/GroupServlet"})
public class GroupServlet extends HttpServlet {

    @EJB
    private GroupBean groupBean;

    private static final String SELECT_ACTION_ALIAS = "selectAction";
    private static final String UPDATE_ACTION_ALIAS = "updateAction";
    private static final String SELECT_COUNT_ACTION_ALIAS = "selectCountAction";
    private static final String GROUP_NAME_ALIAS = "groupName";
    private static final String USER_EMAIL_ALIAS = "userEmail";
    private static final String GROUP_ROLE_ALIAS = "groupRole";
    private static final String PAGE_NUMBER_ALIAS = "pageNumber";
    private static final String PAGE_SIZE_ALIAS = "pageSize";
    private static final String IDS_ALIAS = "ids";
    private static final String ERROR_MESSAGE_ALIAS = "-1";

    private List resList;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GroupSelectAction selectAction = GroupSelectAction.fromString(request.getParameter(SELECT_ACTION_ALIAS));
        GroupSelectCountAction selectCountAction = GroupSelectCountAction.fromString(request.getParameter(SELECT_COUNT_ACTION_ALIAS));

        String groupName = request.getParameter(GROUP_NAME_ALIAS);
        String userEmail = request.getParameter(USER_EMAIL_ALIAS);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER_ALIAS));
        int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE_ALIAS));

        resList = groupBean.executeSelect(selectAction, groupName, userEmail, pageNumber, pageSize);

        Integer count = groupBean.executeSelectCount(selectCountAction, groupName, userEmail);
        if (request.getParameter(SELECT_ACTION_ALIAS).equals(GroupSelectAction.SELECT_GROUPS.getName())) {
            resList.add(new Group("", count));
        } else if (request.getParameter(SELECT_ACTION_ALIAS).equals(GroupSelectAction.SELECT_USERS.getName())) {
            ServiceUserEntity entity = new ServiceUserEntity();
            entity.setUserId(count.intValue());
            entity.setEmail("");
            resList.add(entity);
        }

        String json = new Gson().toJson(resList);
        System.out.println(json);
        response.setContentType("application/json");
        response.getWriter().print(json);

        resList.clear();
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

        GroupUpdateAction updateAction = GroupUpdateAction.fromString(request.getParameter(UPDATE_ACTION_ALIAS));

        String groupName = request.getParameter(GROUP_NAME_ALIAS);
        Role role = Role.fromString(request.getParameter(GROUP_ROLE_ALIAS));
        String[] ids = request.getParameter(IDS_ALIAS).split(",");

        for (String s : ids) {
            System.out.println(s);
        }

        try {
            groupBean.executeUpdate(updateAction, groupName, stringsToIntegers(ids), role);
        } catch (EntityExistsException e) {
            if (request.getParameter(SELECT_ACTION_ALIAS).equals(GroupSelectAction.SELECT_GROUPS)) {
                resList.add(new Group("", new Integer(ERROR_MESSAGE_ALIAS)));
            } else if (request.getParameter(SELECT_ACTION_ALIAS).equals(GroupSelectAction.SELECT_USERS)) {
                ServiceUserEntity entity = new ServiceUserEntity();
                entity.setEmail("");
                entity.setUserId(Integer.parseInt(ERROR_MESSAGE_ALIAS));
                resList.add(new ServiceUserEntity());
            }
        } catch (SQLException e1) {

        }

        doGet(request, response);
    }

    private Integer[] stringsToIntegers(String[] strings) {
        Integer[] res = new Integer[strings.length];
        int i = 0;
        for (String s : strings) {
            res[i++] = new Integer(s);
        }
        return res;
    }

}