package ua.com.tracksee.servlets.group;

/**
 * Created by Igor on 23.04.2015.
 */

import com.google.gson.Gson;
import ua.com.tracksee.entity.Group;
import ua.com.tracksee.enumartion.Role;
import ua.com.tracksee.logic.GroupBean;
import ua.com.tracksee.logic.facade.AdminFacade;
import ua.com.tracksee.logic.group.GroupSelectAction;
import ua.com.tracksee.logic.group.GroupUpdateAction;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Igor Gula on 19.04.2015.
 */
@WebServlet("admin/group")
public class GroupEditServlet extends HttpServlet implements GroupConstants {

    @EJB
    private AdminFacade adminFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        GroupSelectAction selectAction = GroupSelectAction.fromString(request.getParameter(SELECT_ACTION_ALIAS));

        String groupName = request.getParameter(GROUP_NAME_ALIAS);
        String userEmail = request.getParameter(USER_EMAIL_ALIAS);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER_ALIAS));
        int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE_ALIAS));

        List resList = adminFacade.groupExecuteSelect(selectAction, groupName, userEmail, pageNumber, pageSize);

        String json = new Gson().toJson(resList);
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
        Role role = null;
        try {
            role = Role.fromString(request.getParameter(GROUP_ROLE_ALIAS));
        } catch(NoSuchElementException e) {
            role = Role.NOT_REGISTER_USER;
        }
        Integer admin = Integer.parseInt(request.getParameter(ID_ADMIN));

        String idsString = request.getParameter(IDS_ALIAS);
        String[] ids;

        if (!idsString.equals("")) {
            ids = idsString.split(SEPARATOR);
        } else {
            ids = new String[0];
        }

        String updateIdsString = request.getParameter(UPDATE_ROLE_IDS_ALIAS);

        Gson gson = new Gson();
        UserRoles[] userRoles = gson.fromJson(updateIdsString, UserRoles[].class);
        String[] updateIds = new String[userRoles.length];
        boolean[] isDrivers = new boolean[userRoles.length];
        boolean[] isAdmins = new boolean[userRoles.length];
        int i = 0;
        for (UserRoles user : userRoles) {
            updateIds[i] = new String(user.getId().toString());
            isDrivers[i] = user.isDriver;
            isAdmins[i++] = user.isAdmin;
        }

        adminFacade.groupExecuteUpdate(updateAction, groupName, ids, role, updateIds, isAdmins, isDrivers, admin);

        doGet(request, response);
    }

    private class UserRoles {
        private Integer id;
        private boolean isAdmin;
        private boolean isDriver;

        public UserRoles(){}

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public boolean isAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
        }

        public boolean isDriver() {
            return isDriver;
        }

        public void setIsDriver(boolean isDriver) {
            this.isDriver = isDriver;
        }

        @Override
        public String toString() {
            return "UserRoles{" +
                    "id=" + id +
                    ", isAsmin=" + isAdmin +
                    ", isDriver=" + isDriver +
                    '}';
        }
    }

}