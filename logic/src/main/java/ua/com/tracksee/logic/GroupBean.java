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
import ua.com.tracksee.logic.group.GroupAction;

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

    public void addGroup(String groupName, Role role, BigInteger[] userIds) throws SQLException{
        if (!groupDAO.existsGroup(groupName)) {
            for (BigInteger id : userIds) {
                groupDAO.addUserToGroup(groupName, id);
                groupDAO.setRoleToUser(role.getName(), id);
            }
        } else {
            throw new EntityExistsException();
        }
    }

    public void removeGroup(String groupName) {
        if (groupDAO.existsGroup(groupName)) {
            groupDAO.removeGroup(groupName);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<ServiceUserEntity> getGroupMembers(String groupName, Integer pageNumber, Integer pageSize) {
        return groupDAO.getGroupMembers(groupName, pageNumber, pageSize);
    }

    private BigInteger[] getGroupMemberIds(String groupName) {
        return groupDAO.getGroupMemberIds(groupName);
    }

    public void updateGroup(String groupName, Role role) {
        for (BigInteger userId : getGroupMemberIds(groupName)) {
            groupDAO.setRoleToUser(role.getName(), userId);
        }
    }

    public Role getGroupRole(String groupName) {
        return Role.fromString(groupDAO.getGroupRole(groupName));
    }

    public void removeUsersFromGroup(BigInteger[] usersId) {
        for (BigInteger id : usersId) {
            groupDAO.removeUser(id);
        }
    }

    public void addUsersToGroup(String groupName, BigInteger[] userIds) {
        for (BigInteger id : userIds) {
            groupDAO.addUserToGroup(groupName, id);
        }
    }

    public List<Group> getGroups(int pageNumber, int pageSize){
        List<Group> res = new ArrayList<>();
        List<Object[]> aList = groupDAO.getGroups(pageNumber, pageSize);
        for (Object[] s : aList) {
            String name = (String) s[0];
            BigInteger count = (BigInteger) s[1];
            res.add(new Group(name, count));
        }
        return res;
    }

    public List<Group> getByName(String name, int pageNumber, int pageSize) {
        List<Group> res = new ArrayList<>();
        List<Object[]> aList = groupDAO.getByName(name, pageNumber, pageSize);
        for (Object[] s : aList) {
            String nameGroup = (String) s[0];
            BigInteger count = (BigInteger) s[1];
            res.add(new Group(nameGroup, count));
        }
        return res;
    }

    public BigInteger getGroupsCount() {
        return groupDAO.getGroupsCount();
    }

    public BigInteger getGroupsCountByName(String groupName) {
        return groupDAO.getGroupsCountByName(groupName);
    }

    public BigInteger getUsersCount() {
        return groupDAO.getUsersCount();
    }

    public BigInteger getUsersCountByEmail(String userEmail) {
        return groupDAO.getUsersCountByEmail(userEmail);
    }

    public void execute(GroupAction action) {

    }

}
