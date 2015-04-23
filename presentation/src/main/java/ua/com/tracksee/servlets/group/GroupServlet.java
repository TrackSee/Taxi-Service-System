package ua.com.tracksee.servlets.group;

/**
 * Created by Igor on 23.04.2015.
 */

import com.google.gson.Gson;
import ua.com.tracksee.entity.Group;
import ua.com.tracksee.entity.Role;
import ua.com.tracksee.logic.GroupBean;
import ua.com.tracksee.logic.group.GroupSelectAction;
import ua.com.tracksee.logic.group.GroupSelectCountAction;
import ua.com.tracksee.logic.group.GroupUpdateAction;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
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
    private static final String NAME_ALIAS = "name";
    private static final String GROUP_ROLE_ALIAS = "groupRole";
    private static final String PAGE_NUMBER_ALIAS = "pageNumber";
    private static final String PAGE_SIZE_ALIAS = "pageSize";
    private static final String IDS_ALIAS = "ids";
    private static final String ERROR_MESSAGE_ALIAS = "-1";

    private String name;

    private String json = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GroupSelectAction selectAction = GroupSelectAction.fromString(request.getParameter(SELECT_ACTION_ALIAS));
        GroupSelectCountAction selectCountAction = GroupSelectCountAction.fromString(request.getParameter(SELECT_COUNT_ACTION_ALIAS));

        String name = request.getParameter(NAME_ALIAS);

        String groupName = request.getParameter(NAME_ALIAS);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER_ALIAS));
        int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE_ALIAS));

        List res = groupBean.executeSelect(selectAction, groupName, pageNumber, pageSize);

        res.add(new Group("", groupBean.executeSelectCount(selectCountAction, name)));

        json += new Gson().toJson(res);
        response.setContentType("application/json");
        response.getWriter().print(json);
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

        String name = request.getParameter(NAME_ALIAS);
        Role role = Role.fromString(request.getParameter(GROUP_ROLE_ALIAS));
        String[] ids = request.getParameterValues(IDS_ALIAS);

        try {
            groupBean.executeUpdate(updateAction, name, stringsToBigIntegers(ids), role);
        } catch (SQLException e) {
            json += new Gson().toJson(new Group("", new BigInteger(ERROR_MESSAGE_ALIAS.getBytes())));
        }

        doGet(request, response);
    }

    private BigInteger[] stringsToBigIntegers(String[] strings) {
        BigInteger[] res = new BigInteger[strings.length];
        int i = 0;
        for (String s : strings) {
            res[i++] = new BigInteger(s.getBytes());
        }
        return res;
    }

}