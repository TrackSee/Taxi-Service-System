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
import java.util.List;

@Local
@Stateless
public class GroupBean {

    @EJB
    private GroupDAO groupDAO;

    private void addGroup(String groupName, Role role, BigInteger[] userIds) throws SQLException{
        if (!groupDAO.existsGroup(groupName)) {
            for (BigInteger id : userIds) {
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

    private BigInteger[] getGroupMemberIds(String groupName) {
        return groupDAO.getGroupMemberIds(groupName);
    }

    private void updateGroup(String groupName, Role role) {
        for (BigInteger userId : getGroupMemberIds(groupName)) {
            groupDAO.setRoleToUser(role.getName(), userId);
        }
    }

    public Role getGroupRole(String groupName) {
        return Role.fromString(groupDAO.getGroupRole(groupName));
    }

    private void removeUsersFromGroup(BigInteger[] usersId) {
        for (BigInteger id : usersId) {
            groupDAO.removeUser(id);
        }
    }

    private void addUsersToGroup(String groupName, BigInteger[] userIds) {
        for (BigInteger id : userIds) {
            groupDAO.addUserToGroup(groupName, id);
        }
    }

//    private List<Group> getGroups(int pageNumber, int pageSize){
//        List<Group> res = new ArrayList<>();
//        List<Object[]> aList = groupDAO.getGroups(pageNumber, pageSize);
//        for (Object[] s : aList) {
//            String name = (String) s[0];
//            BigInteger count = (BigInteger) s[1];
//            res.add(new Group(name, count));
//        }
//        return res;
//    }

    private List<Group> getGroupsByName(String name, int pageNumber, int pageSize) {
        List<Group> res = new ArrayList<>();
        List<Object[]> aList = groupDAO.getGroupByName(name, pageNumber, pageSize);
        for (Object[] s : aList) {
            String nameGroup = (String) s[0];
            BigInteger count = (BigInteger) s[1];
            res.add(new Group(nameGroup, count));
        }
        return res;
    }

    private BigInteger getGroupsCountByName(String groupName) {
        return groupDAO.getGroupsCountByName(groupName);
    }

    private BigInteger getUsersCountByEmail(String userEmail) {
        return groupDAO.getUsersCountByEmail(userEmail);
    }

    public void executeUpdate(GroupUpdateAction action, String groupName, BigInteger[] ids, Role role) throws SQLException {
        if (action == GroupUpdateAction.ADD_GROUP) {
            addGroup(groupName, role, ids);
        } else if (action == GroupUpdateAction.ADD_USERS_TO_GROUP) {
            addUsersToGroup(groupName, ids);
        } else if (action == GroupUpdateAction.REMOVE_GROUP) {
            removeGroup(groupName);
        } else if (action == GroupUpdateAction.REMOVE_USERS_FROM_GROUP) {
            removeUsersFromGroup(ids);
        } else if (action == GroupUpdateAction.UPDATE_GROUP) {
            updateGroup(groupName, role);
        }
    }

    public List executeSelect(GroupSelectAction action, String groupName, String userEmail, Integer pageNumber, Integer pageSize) {
        String s="";
        if (action == GroupSelectAction.SELECT_GROUPS) {
            return getGroupsByName(groupName, pageNumber, pageSize);
        } else if (action == GroupSelectAction.SELECT_USERS) {
            return getGroupMembers(groupName, userEmail, pageNumber, pageSize);
        }
        return null;
    }

    public BigInteger executeSelectCount(GroupSelectCountAction action, String name) {
        if (action == GroupSelectCountAction.SELECT_GROUPS_COUNT) {
            return getGroupsCountByName(name);
        } else if (action == GroupSelectCountAction.SELECT_USERS_COUNT) {
            return getUsersCountByEmail(name);
        }
        return null;
    }

}
