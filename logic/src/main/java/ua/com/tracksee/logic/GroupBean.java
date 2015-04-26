package ua.com.tracksee.logic;

/**
 * Created by Igor on 22.04.2015.
 */
/**
 * Created by Igor Gula on 19.04.2015.
 */

import ua.com.tracksee.dao.GroupDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entity.Group;
import ua.com.tracksee.entity.Role;
import ua.com.tracksee.logic.group.GroupSelectAction;
import ua.com.tracksee.logic.group.GroupSelectCountAction;
import ua.com.tracksee.logic.group.GroupUpdateAction;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.persistence.*;
import javax.ejb.Stateless;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Local
@Stateless
public class GroupBean {

    @EJB
    private GroupDAO groupDAO;

    private void addGroup(String groupName, Role role, Integer[] userIds) throws SQLException{
        if (!groupDAO.existsGroup(groupName)) {
            for (Integer id : userIds) {
                groupDAO.addUserToGroup(groupName, id);
                groupDAO.setRoleToUser(role.getName(), id);
            }
        } else {
            throw new EntityExistsException();
        }
    }

    private void removeGroup(String groupName) {
        if (groupDAO.existsGroup(groupName)) {
            groupDAO.removeGroup(groupName);
        } else {
            throw new EntityNotFoundException();
        }
    }

    private List<ServiceUserEntity> getGroupMembers(String groupName, String userEmail, Integer pageNumber, Integer pageSize) {
        return groupDAO.getGroupMembers(groupName, userEmail, pageNumber, pageSize);
    }

    private Integer[] getGroupMemberIds(String groupName) {
        return groupDAO.getGroupMemberIds(groupName);
    }

    private void updateGroup(String groupName, Role role, Integer[] ids) {
        Integer[] groupIds = getGroupMemberIds(groupName);
        List<Integer> groupIdsList = Arrays.asList(groupIds);
        for (Integer userId : groupIds) {
            groupDAO.setRoleToUser(role.getName(), userId);
        }
        for (Integer id : ids) {
            if (groupIdsList.contains(id)) {
                groupDAO.removeUser(id);
            } else {
                groupDAO.addUserToGroup(groupName, id);
            }
        }
    }

    public Role getGroupRole(String groupName) {
        return Role.fromString(groupDAO.getGroupRole(groupName));
    }

    private void removeUsersFromGroup(Integer[] usersId) {
        for (Integer id : usersId) {
            groupDAO.removeUser(id);
        }
    }

    private void addUsersToGroup(String groupName, Integer[] userIds) {
        for (Integer id : userIds) {
            groupDAO.addUserToGroup(groupName, id);
        }
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

    private Integer getUsersAllCount() {
        return groupDAO.getUsersAllCount();
    }

    private Integer getUsersInGroupCount(String groupName) {
        return groupDAO.getUsersInGroupCount(groupName);
    }

    public void executeUpdate(GroupUpdateAction action, String groupName, Integer[] ids, Role role) throws SQLException {
        if (action == GroupUpdateAction.ADD_GROUP) {
            addGroup(groupName, role, ids);
        } else if (action == GroupUpdateAction.REMOVE_GROUP) {
            removeGroup(groupName);
        } else if (action == GroupUpdateAction.UPDATE_GROUP) {
            updateGroup(groupName, role, ids);
        }
    }

    public List executeSelect(GroupSelectAction action, String groupName, String userEmail, Integer pageNumber, Integer pageSize) {
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
                return this.getUsersAllCount();
            }
        }
        return null;
    }

}
