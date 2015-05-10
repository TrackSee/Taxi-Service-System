package ua.com.tracksee.logic;

/**
 * Created by Igor on 22.04.2015.
 */
/**
 * Created by Igor Gula on 19.04.2015.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.GroupDAO;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.entity.Group;
import ua.com.tracksee.enumartion.Role;
import ua.com.tracksee.logic.group.GroupSelectAction;
import ua.com.tracksee.logic.group.GroupSelectCountAction;
import ua.com.tracksee.logic.group.GroupUpdateAction;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Local
@Stateless
public class GroupBean {

    private static final Logger logger = LogManager.getLogger();

    @EJB
    private GroupDAO groupDAO;

    private void addGroup(String groupName, Role role, String[] userIds, Integer[] userIdsUpdateRole,
                          boolean[] idAdmins, boolean[] isDrivers) throws SQLException{
        if (!existsGroup(groupName)) {
            for (Integer id : stringsToIntegers(userIds)) {
                groupDAO.addUserToGroup(groupName, id);
                groupDAO.setRoleToUser(role.getName(), id);
            }
        } else {
            throw new EntityExistsException();
        }
        try {
            setRolesToUsers(userIdsUpdateRole, isDrivers, idAdmins);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        }
    }

    private void removeGroups(String[] groupNames) {
        for (String group: groupNames) {
            if (groupDAO.existsGroup(group)) {
                groupDAO.removeGroup(group);
            } else {
                throw new EntityNotFoundException();
            }
        }
    }

    private List<UserEntity> getGroupMembers(String groupName, String userEmail,
                                                    Integer pageNumber, Integer pageSize) {
        return groupDAO.getGroupMembers(groupName, userEmail, pageNumber, pageSize);
    }

    private Integer[] getGroupMemberIds(String groupName) {
        return groupDAO.getGroupMemberIds(groupName);
    }

    private void updateGroup(String groupName, Role role, String[] ids,
                             Integer[] userIds, boolean[] idAdmins, boolean[] isDrivers) {

        Integer[] groupIds = getGroupMemberIds(groupName);
        List<Integer> groupIdsList = Arrays.asList(groupIds);
        for (Integer userId : groupIds) {
            groupDAO.setRoleToUser(role.getName(), userId);
        }

        Integer[] idsInt = stringsToIntegers(ids);
        for (Integer id : idsInt) {
            if (groupIdsList.contains(id)) {
                groupDAO.removeUser(id);
            } else {
                groupDAO.addUserToGroup(groupName, id);
            }
        }

        try {
            setRolesToUsers(userIds, isDrivers, idAdmins);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        }
    }

    public Role getGroupRole(String groupName) {
        return Role.fromString(groupDAO.getGroupRole(groupName));
    }

    private List<Group> getGroupsByName(String name, int pageNumber, int pageSize) {
        List<Group> res = new ArrayList<>();
        List<Object[]> aList = groupDAO.getGroupByName(name, pageNumber, pageSize);
        for (Object[] s : aList) {
            String nameGroup = (String) s[0];
            Integer count = ((BigInteger) s[1]).intValue();
            res.add(new Group(nameGroup, count));
        }
        return res;
    }

    private Integer getGroupsCountByName(String groupName) {
        return groupDAO.getGroupsCountByName(groupName);
    }

    private Integer getUsersCountByEmail(String userEmail) {
        return groupDAO.getUsersCountByEmail(userEmail);
    }

    private Integer getUsersInGroupCount(String groupName) {
        return groupDAO.getUsersInGroupCount(groupName);
    }

    public void executeUpdate(GroupUpdateAction action, String groupName, String[] ids, Role role,
                                String[] userIdsStrings, boolean[] idAdmins, boolean[] isDrivers) {
        Integer[] userIds = null;
        if (userIdsStrings != null) {
            userIds = stringsToIntegers(userIdsStrings);
        }
        if (action == GroupUpdateAction.ADD_GROUP) {
            try {
                try {
                    addGroup(groupName, role, ids, userIds, idAdmins, isDrivers);
                } catch (EntityExistsException|EJBException e) {
                    logger.error("This group exist! " + e);
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        } else if (action == GroupUpdateAction.REMOVE_GROUPS) {
            try {
                removeGroups(ids);
            } catch ( EntityNotFoundException e) {
                logger.error("This group does not exist! " + e);
            }
        } else if (action == GroupUpdateAction.UPDATE_GROUP) {
            updateGroup(groupName, role, ids, userIds, idAdmins, isDrivers);
        }
    }

    public List executeSelect(GroupSelectAction action, String groupName, String userEmail,
                              Integer pageNumber, Integer pageSize) {
        if (action == GroupSelectAction.SELECT_GROUPS) {
            return getGroupsByName(groupName, pageNumber, pageSize);
        } else if (action == GroupSelectAction.SELECT_USERS) {
            return getGroupMembers(groupName, userEmail, pageNumber, pageSize);
        }
        return null;
    }

    public Integer executeSelectCount(GroupSelectCountAction action, String groupName, String userEmail) {
        if (action == GroupSelectCountAction.SELECT_GROUPS_COUNT) {
            return getGroupsCountByName(groupName);
        } else if (action == GroupSelectCountAction.SELECT_USERS_COUNT) {
            if (!userEmail.equals("")) {
                return this.getUsersCountByEmail(userEmail);
            } else if (!groupName.equals("")) {
                return this.getUsersInGroupCount(groupName);
            } else {
                //return this.getUsersAllCount();
                return this.getUsersCountByEmail("");
            }
        }
        return null;
    }

    public void setRolesToUsers(Integer[] userIds, boolean[] isDrivers, boolean[] isAdmins) {
        if ((userIds.length != isAdmins.length) || (userIds.length != isAdmins.length)) {
            logger.error("Length of userIds has to be equils to isDrivers length, and to isAdmins arrays!");
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < userIds.length; i++) {
            groupDAO.updateUserRoles(userIds[i], isDrivers[i], isAdmins[i]);
        }
    }

    public boolean existsGroup(String groupName) {
        return groupDAO.existsGroup(groupName);
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
