package ua.com.tracksee.dao;

import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Igor Gula on 22.04.2015.
 */
@Local
public interface GroupDAO {
    /**
     * @param groupName - name of group to remove
     */
    public int removeGroup(String groupName);
    /**
     * @param userId - id of user to remove
     */
    public int removeUser(Integer userId);
    /**
     * @param groupName - name of group
     * @return true if group with name groupName exists, else - false
     */
    public boolean existsGroup(String groupName);
    /**
     * This method adds user to group.
     *
     * @param groupName - name of group
     * @param userId - id of user to add to group
     */
    public int addUserToGroup(String groupName, Integer userId);
    /**
     * This method set role to user.
     *
     * @param role - role of user {Administrator, Driver, Customer}
     * @param userId - id of user to add to group
     */
    public int setRoleToUser(String role, Integer userId);
    /**
     * This method returns all groups with name like 'name'.
     *
     * @param name - name of group
     * @param pageNumber - number which to start selecting records from
     * @param pageSize - count of groups to return
     * @return list of array of objects (Object[0] : {String groupName, Integer count of users in group}, Object[1]...)
     */
    public List<Object[]> getGroupByName(String name, int pageNumber, int pageSize);
    /**
     * This method returns all users with name like 'userEmail'.
     * At first it returns users that in group 'groupName', than others.
     *
     * @param groupName - name of group
     * @param userEmail - email of user
     * @param pageSize - count of groups to return
     * @return list users
     */
    public List<ServiceUserEntity> getGroupMembers(String groupName, String userEmail, int pageNumber, int pageSize);
    /**
     * This method returns all users with name 'groupName'.
     *
     * @param groupName - name of group
     * @return array of user ids indexes
     */
    public Integer[] getGroupMemberIds(String groupName);
    /**
     * This method returns role of group 'groupName'.
     *
     * @param groupName - name of group
     * @return  role of group 'groupName'
     */
    public String getGroupRole(String groupName);
    /**
     * @param groupName - name of group
     * @return  count of group in group
     */
    public Integer getGroupsCountByName(String groupName);
    /**
     * @param userEmail - email of user
     * @return  count of users where email is like 'userEmail'
     */
    public Integer getUsersCountByEmail(String userEmail);
    /**
     * @param groupName - name of group
     * @return  count of users in group 'groupName'
     */
    public Integer getUsersInGroupCount(String groupName);
    /**
     * This method updates account of user: set roles to user with id - 'userId'.
     *
     * @param userId - id of user
     * @param isDriver - is user driver, or not
     * @param isAdmin - is user admin, or not
     */
    public int updateUserRoles(Integer userId, boolean isDriver, boolean isAdmin);

    public EntityManager getEntityManager();
}

