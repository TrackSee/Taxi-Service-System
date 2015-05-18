package ua.com.tracksee.dao.implementation;

import ua.com.tracksee.dao.GroupDAO;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.enumartion.Role;

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
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    private static final String GET_GROUPS_BY_NAME_QUERY = "SELECT g.group_name, COUNT(*) " +
            "FROM service_user g GROUP BY g.group_name HAVING g.group_name" +
            " ILIKE ? AND g.group_name IS NOT NULL ORDER BY g.group_name";

    private static final String GET_GROUP_MEMBERS_QUERY =
            "SELECT * FROM service_user u WHERE u.group_name = ? ORDER BY email";

    private static final String GET_USERS_BY_EMAIL_QUERY =
            "SELECT * FROM service_user u WHERE u.email ILIKE ?1  ORDER BY email";

    private static final String GET_USERS_BY_EMAIL_FROM_GROUP_FIRST_QUERY = "SELECT * FROM service_user u WHERE" +
            " u.group_name = ?1 AND u.email ILIKE ?2 UNION ALL SELECT * FROM service_user  u WHERE " +
            "u.group_name IS NULL  AND u.email ILIKE ?2 UNION ALL SELECT * FROM service_user u " +
            "WHERE u.group_name <> ?1 AND u.email ILIKE ?2";

    private static final String GET_GROUP_MEMBER_IDS_QUERY =
            "SELECT u.user_id FROM service_user u WHERE u.group_name = ?  ORDER BY email";

    private static final String SET_ROLE_TO_USER_QUERY =
            "UPDATE service_user SET admin = ?1, driver = ?2 WHERE user_id = ?3";

    private static final String GET_GROUPS_COUNT_BY_NAME_QUERY = "SELECT COUNT (DISTINCT g.group_name) " +
            "FROM service_user g WHERE g.group_name ILIKE ?";

    private static final String REMOVE_GROUP_QUERY =  "UPDATE service_user " +
            "SET group_name = NULL WHERE group_name = ?";

    private static final String GET_USERS_COUNT_BY_EMAIL_QUERY =
            "SELECT COUNT(*) FROM service_user g WHERE g.email ILIKE ?1 ";

    private static final String GET_USERS_IN_GROUP_COUNT_QUERY =
            "SELECT COUNT(*) FROM service_user g WHERE g.group_name = ?1";

    private static final String ADD_USER_TO_GROUP_QUERY =
            "UPDATE service_user SET group_name = ?1 WHERE user_id = ?2";

    private static final String REMOVE_USER_QUERY =
            "UPDATE service_user SET group_name = DEFAULT WHERE user_id = ?";

    private static final String EXISTS_GROUP_QUERY = "SELECT DISTINCT COUNT(*) FROM " +
            "service_user u WHERE u.group_name = ?";

    private static final String GET_GROUP_ROLE_ADMIN_QUERY = "SELECT COUNT * FROM service_user u " +
            "WHERE u.group_name = ? AND  u.admin = TRUE";

    private static final String GET_GROUP_ROLE_DRIVER_QUERY = "SELECT COUNT * FROM service_user u " +
            "WHERE u.group_name = ? AND  u.driver = TRUE";

    private static final String UPDATE_USER_ROLES_QUERY =
            "UPDATE service_user SET admin = ?1, driver = ?2 WHERE user_id = ?3";

    @Override
    public List<Object[]> getGroupByName(String groupName, int pageNumber, int pageSize) {
        Query query = entityManager.createNativeQuery(GET_GROUPS_BY_NAME_QUERY);
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
            query = entityManager.createNativeQuery(GET_USERS_BY_EMAIL_FROM_GROUP_FIRST_QUERY, UserEntity.class);
            query.setParameter(1, groupName);
            query.setParameter(2, "%" + userEmail + "%");
        } else if (!groupName.equals("")) {
            query = entityManager.createNativeQuery(GET_GROUP_MEMBERS_QUERY, UserEntity.class);
            query.setParameter(1, groupName);
        } else {
            query = entityManager.createNativeQuery(GET_USERS_BY_EMAIL_QUERY, UserEntity.class);
            query.setParameter(1, "%" + userEmail + "%");
        }
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Integer[] getGroupMemberIds(String groupName) {
        Integer[] res = null;
        Query query = entityManager.createNativeQuery(GET_GROUP_MEMBER_IDS_QUERY);
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
    public int setRoleToUser(Role role, Integer userId) {
        Query query = null;
        if (role.equals(Role.ADMINISTRATOR)) {
            query = entityManager.createNativeQuery(SET_ROLE_TO_USER_QUERY);
            query.setParameter(1, true);
            query.setParameter(2, false);
        } else if (role.equals(Role.DRIVER)) {
            query = entityManager.createNativeQuery(SET_ROLE_TO_USER_QUERY);
            query.setParameter(1, false);
            query.setParameter(2, true);
        } else if (role.equals(Role.CUSTOMER_USER)) {
            query = entityManager.createNativeQuery(SET_ROLE_TO_USER_QUERY);
            query.setParameter(1, false);
            query.setParameter(2, false);
        }
        if (query != null) {
            query.setParameter(3, userId);
            return query.executeUpdate();
        }
        return 0;
    }

    @Override
    public int removeGroup(String groupName) {
        if (existsGroup(groupName)) {
            Query query = entityManager.createNativeQuery(REMOVE_GROUP_QUERY);
            query.setParameter(1, groupName);
            return query.executeUpdate();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Integer getGroupsCountByName(String groupName) {
        Query query = entityManager.createNativeQuery(GET_GROUPS_COUNT_BY_NAME_QUERY);
        query.setParameter(1, "%" + groupName + "%");
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public Integer getUsersCountByEmail(String userEmail) {
        Query query = entityManager.createNativeQuery(GET_USERS_COUNT_BY_EMAIL_QUERY);
        query.setParameter(1, "%" + userEmail + "%");
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public Integer getUsersInGroupCount(String groupName) {
        Query query = entityManager.createNativeQuery(GET_USERS_IN_GROUP_COUNT_QUERY);
        query.setParameter(1, groupName);
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public int addUserToGroup(String groupName, Integer userId) {
        Query query = entityManager.createNativeQuery(ADD_USER_TO_GROUP_QUERY);
        query.setParameter(1, groupName);
        query.setParameter(2, userId);
        return query.executeUpdate();
    }

    @Override
    public int removeUser(Integer userId) {
        Query query = entityManager.createNativeQuery(REMOVE_USER_QUERY);
        query.setParameter(1, userId);
        return query.executeUpdate();
    }

    @Override
    public boolean existsGroup(String groupName) {
        Query query = entityManager.createNativeQuery(EXISTS_GROUP_QUERY);
        query.setParameter(1, groupName);
        Integer count = ((BigInteger) query.getSingleResult()).intValue();

        return !count.equals(new Integer(0));
    }

    public String getGroupRole(String groupName) {
        Query query = entityManager.createNativeQuery(GET_GROUP_ROLE_ADMIN_QUERY);
        query.setParameter(1, groupName);
        int countAdmins = (Integer) query.getSingleResult();

        query = entityManager.createNativeQuery(GET_GROUP_ROLE_DRIVER_QUERY);
        query.setParameter(1, groupName);
        int countDrivers = (Integer) query.getSingleResult();

        if ((countAdmins == 0) && (countDrivers == 0)) {
            return Role.CUSTOMER_USER.getName();
        } else if (countAdmins > countDrivers) {
            return Role.ADMINISTRATOR.getName();
        } else {
            return Role.DRIVER.getName();
        }
    }

    @Override
    public int updateUserRoles(Integer userId, boolean isDriver, boolean isAdmin) {
        Query query = entityManager.createNativeQuery(UPDATE_USER_ROLES_QUERY);
        query.setParameter(1, isAdmin);
        query.setParameter(2, isDriver);
        query.setParameter(3, userId);
        return query.executeUpdate();
    }
}
