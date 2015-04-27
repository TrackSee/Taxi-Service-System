package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Local;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Igor Gula on 22.04.2015.
 */
@Local
public interface GroupDAO {
    public void removeGroup(String groupName);
    public void removeUser(Integer userId);
    public boolean existsGroup(String groupName);
    public void addUserToGroup(String groupName, Integer userId);
    public void setRoleToUser(String role, Integer userId);
    public List<Object[]> getGroupByName(String name, int pageNumber, int pageSize);
    public List<ServiceUserEntity> getGroupMembers(String groupName, String userEmail, int pageNumber, int pageSize);
    public Integer[] getGroupMemberIds(String groupName);
    public String getGroupRole(String groupName);
    public Integer getGroupsCountByName(String groupName);
    public Integer getUsersCountByEmail(String userEmail);
    public Integer getUsersAllCount();
    public Integer getUsersInGroupCount(String groupName);
    public void updateUserRoles(Integer userId, boolean isDriver, boolean isAdmin);
}

