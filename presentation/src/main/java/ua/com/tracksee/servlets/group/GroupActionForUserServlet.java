package ua.com.tracksee.servlets.group;

import ua.com.tracksee.entity.Role;
import ua.com.tracksee.logic.GroupBean;
import ua.com.tracksee.logic.group.GroupAction;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

/**
 * Created by Igor Gula on 19.04.2015.
 */
@WebServlet(name = "GroupActionForUserServlet", urlPatterns = {"/GroupActionForUserServlet"})
public class GroupActionForUserServlet extends HttpServlet {

    private static final String ACTION_ALIAS = "action";
    private static final String GROUP_NAME_ALIAS = "groupName";
    private static final String GROUP_ROLE_ALIAS = "groupRole";
    private static final String USER_IDS_ALIAS = "userIds";
    private static final String PAGE_ALIAS = "groupEdit.jsp";
    private static final String ERROR_MESSAGE = "errorMessage";

    @EJB
    private GroupBean groupBean;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String actionString = request.getParameter(ACTION_ALIAS);
        GroupAction action = GroupAction.fromString(actionString);

        String name = request.getParameter(GROUP_NAME_ALIAS);
        String roleString = request.getParameter(GROUP_ROLE_ALIAS);
        Role role = Role.fromString(roleString);
        String[] userIds = request.getParameterValues(USER_IDS_ALIAS);

        try {
            switch (action) {
                case ADD_USERS_TO_GROUP:
                    groupBean.addUsersToGroup(name, stringToInteger(userIds));
                    break;
                case REMOVE_USERS_FROM_GROUP:
                    groupBean.removeUsersFromGroup(stringToInteger(userIds));
                    break;
                case ADD_GROUP:
                    groupBean.addGroup(name, role, stringToInteger(userIds));
            }
        } catch (SQLException e) {
            //todo
        }

        request.getRequestDispatcher(PAGE_ALIAS).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private BigInteger[] stringToInteger(String[] strings) {
        BigInteger[] res = new BigInteger[strings.length];
        int i = 0;
        for (String s : strings) {
            res[i++] = new BigInteger(s.getBytes());
        }
        return res;
    }

}