package ua.com.tracksee.dao.implementation;

import ua.com.tracksee.dao.GroupDAO;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Igor Gula on 22.04.2015.
 */
@Stateless
public class GroupDAOBean implements GroupDAO {

    private static final String ROLE_ADMIN = "administrator";
    private static final String ROLE_DRIVER = "driver";
    private static final String ROLE_REGISTERED_CUSTOMER = "registered_customer";

    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    private static final String getGroupsByNameQuery = "SELECT g.group_name, COUNT(*) " +
            "FROM service_user g GROUP BY g.group_name HAVING g.group_name" +
            " LIKE ? AND g.group_name IS NOT NULL ORDER BY g.group_name";

    private static final String getGroupMembersQuery =
            "SELECT * FROM service_user u WHERE u.group_name = ? ORDER BY email";

    private static final String getUsersByEmailQuery =
            "SELECT * FROM service_user u WHERE u.email LIKE ?1  ORDER BY email";

    private static final String getUsersByEmailFromGroupFirstQuery = "SELECT * FROM service_user u WHERE" +
            " u.group_name = ?1 AND u.email LIKE ?2 UNION ALL SELECT * FROM service_user  u WHERE " +
            "u.group_name IS NULL  AND u.email LIKE ?2 UNION ALL SELECT * FROM service_user u " +
            "WHERE u.group_name <> ?1 AND u.email LIKE ?2";

    private static final String getGroupMemberIdsQuery =
            "SELECT u.user_id FROM service_user u WHERE u.group_name = ?  ORDER BY email";

    private static final String setRoleAdminToUserQuery =
            "UPDATE service_user SET admin = TRUE, driver = FALSE WHERE user_id = ?1";

    private static final String setRoleDriverToUserQuery =
            "UPDATE service_user SET driver = TRUE, admin = FALSE WHERE user_id = ?1";

    private static final String getGroupsCountByNameQuery = "SELECT COUNT (DISTINCT g.group_name) " +
            "FROM service_user g WHERE g.group_name LIKE ?";

    private static final String removeGroupQuery =  "UPDATE service_user " +
            "SET group_name = NULL WHERE group_name = ?";

    private static final String getUsersCountByEmailQuery =
            "SELECT COUNT(*) FROM service_user g WHERE g.email LIKE ?1 ";

    private static final String getUsersInGroupCountQuery =
            "SELECT COUNT(*) FROM service_user g WHERE g.group_name = ?1";

    private static final String addUserToGroupQuery =
            "UPDATE service_user SET group_name = ?1 WHERE user_id = ?2";

    private static final String removeUserQuery =
            "UPDATE service_user SET group_name = DEFAULT WHERE user_id = ?";

    private static final String existsGroupQuery = "SELECT DISTINCT COUNT(*) FROM " +
            "service_user u WHERE u.group_name = ?";

    private static final String getGroupRoleAdminQuery = "SELECT COUNT * FROM service_user u " +
            "WHERE u.group_name = ? AND  u.admin = TRUE";

    private static final String getGroupRoleDriverQuery = "SELECT COUNT * FROM service_user u " +
            "WHERE u.group_name = ? AND  u.driver = TRUE";

    private static final String updateUserRolesQuery =
            "UPDATE service_user SET admin = ?1, driver = ?2 WHERE user_id = ?3";

    @Override
    public List<Object[]> getGroupByName(String groupName, int pageNumber, int pageSize) {
        Query query = entityManager.createNativeQuery(getGroupsByNameQuery);
        query.setParameter(1,"%" + groupName + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Object[]> aList = query.getResultList();

        return aList;
    }

    @Override
    public List<UserEntity> getGroupMembers(String groupName, String userEmail, int pageNumber, int pageSize) {
        Query query;
        if ((!userEmail.equals("")) && (!groupName.equals(""))) {
            query = entityManager.createNativeQuery(getUsersByEmailFromGroupFirstQuery, UserEntity.class);
            query.setParameter(1, groupName);
            query.setParameter(2, "%" + userEmail + "%");
        } else if (!groupName.equals("")) {
            query = entityManager.createNativeQuery(getGroupMembersQuery, UserEntity.class);
            query.setParameter(1, groupName);
        } else {
            query = entityManager.createNativeQuery(getUsersByEmailQuery, UserEntity.class);
            query.setParameter(1, "%" + userEmail + "%");
        }
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Integer[] getGroupMemberIds(String groupName) {
        Integer[] res = null;
        Query query = entityManager.createNativeQuery(getGroupMemberIdsQuery);
        query.setParameter(1, groupName);
        List<Integer> aList = query.getResultList();
        res = new Integer[aList.size()];
        int i = 0;
        for (Integer o : aList) {
            res[i++] = o;
        }
        Iterator it = aList.iterator();

        return res;
    }

    @Override
    public int setRoleToUser(String role, Integer userId) {
        Query query = null;
        if (role.equals(ROLE_ADMIN)) {
            query = entityManager.createNativeQuery(setRoleAdminToUserQuery);
        } else if (role.equals(ROLE_DRIVER)) {
            query = entityManager.createNativeQuery(setRoleDriverToUserQuery);
        }
        if (query != null) {
            query.setParameter(1, userId);
            return query.executeUpdate();
        }
        return 0;
    }

    @Override
    public int removeGroup(String groupName) {
        if (existsGroup(groupName)) {
            Query query = entityManager.createNativeQuery(removeGroupQuery);
            query.setParameter(1, groupName);
            return query.executeUpdate();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Integer getGroupsCountByName(String groupName) {
        Query query = entityManager.createNativeQuery(getGroupsCountByNameQuery);
        query.setParameter(1, "%" + groupName + "%");
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public Integer getUsersCountByEmail(String userEmail) {
        Query query = entityManager.createNativeQuery(getUsersCountByEmailQuery);
        query.setParameter(1, "%" + userEmail + "%");
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public Integer getUsersInGroupCount(String groupName) {
        Query query = entityManager.createNativeQuery(getUsersInGroupCountQuery);
        query.setParameter(1, groupName);
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public int addUserToGroup(String groupName, Integer userId) {
        Query query = entityManager.createNativeQuery(addUserToGroupQuery);
        query.setParameter(1, groupName);
        query.setParameter(2, userId);
        return query.executeUpdate();
    }

    @Override
    public int removeUser(Integer userId) {
        Query query = entityManager.createNativeQuery(removeUserQuery);
        query.setParameter(1, userId);
        return query.executeUpdate();
    }

    @Override
    public boolean existsGroup(String groupName) {
        Query query = entityManager.createNativeQuery(existsGroupQuery);
        query.setParameter(1, groupName);
        Integer count = ((BigInteger) query.getSingleResult()).intValue();

        return !count.equals(new Integer(0));
    }

    public String getGroupRole(String groupName) {
        Query query = entityManager.createNativeQuery(getGroupRoleAdminQuery);
        query.setParameter(1, groupName);
        int countAdmins = (Integer) query.getSingleResult();

        query = entityManager.createNativeQuery(getGroupRoleDriverQuery);
        query.setParameter(1, groupName);
        int countDrivers = (Integer) query.getSingleResult();

        if ((countAdmins == 0) && (countDrivers == 0)) {
            return ROLE_REGISTERED_CUSTOMER;
        } else if (countAdmins > countDrivers) {
            return ROLE_ADMIN;
        } else {
            return ROLE_DRIVER;
        }
    }

    @Override
    public int updateUserRoles(Integer userId, boolean isDriver, boolean isAdmin) {
        Query query = entityManager.createNativeQuery(updateUserRolesQuery);
        query.setParameter(1, isAdmin);
        query.setParameter(2, isDriver);
        query.setParameter(3, userId);
        return query.executeUpdate();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
