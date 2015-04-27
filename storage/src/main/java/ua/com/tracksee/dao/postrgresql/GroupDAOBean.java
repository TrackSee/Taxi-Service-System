package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.GroupDAO;
import ua.com.tracksee.entities.ServiceUserEntity;

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
    private static final String ROLE_UNREGISTERED_CUSTOMER = "unregistered_customer";

    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public List<Object[]> getGroupByName(String groupName, int pageNumber, int pageSize) {
        Query query = entityManager.createNativeQuery("SELECT g.group_name, COUNT(*) " +
                "FROM service_user g GROUP BY g.group_name HAVING g.group_name LIKE ? AND g.group_name IS NOT NULL");
        query.setParameter(1,"%" + groupName + "%");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Object[]> aList = query.getResultList();

        return aList;
    }

    @Override
    public List<ServiceUserEntity> getGroupMembers(String groupName, String userEmail, int pageNumber, int pageSize) {
        Query query;
        String queryString = "";
        if ((!userEmail.equals("")) && (!groupName.equals(""))) {
            queryString = "SELECT * FROM service_user u WHERE u.group_name = ?1 AND u.email LIKE ?2 " +
                    "UNION ALL SELECT * FROM service_user  u WHERE u.group_name IS NULL  AND u.email LIKE ?2 " +
                    "UNION ALL SELECT * FROM service_user u " +
                    "WHERE u.group_name <> ?1 AND u.email LIKE ?2";
            query = entityManager.createNativeQuery(queryString, ServiceUserEntity.class);
            query.setParameter(1, groupName);
            query.setParameter(2, "%" + userEmail + "%");
        } else if (!groupName.equals("")) {
            queryString = "SELECT * FROM service_user u WHERE u.group_name = ?";
            query = entityManager.createNativeQuery(queryString, ServiceUserEntity.class);
            query.setParameter(1, groupName);
        } else {
            queryString = "SELECT * FROM service_user u WHERE u.email LIKE ?1";
            query = entityManager.createNativeQuery(queryString, ServiceUserEntity.class);
            query.setParameter(1, "%" + userEmail + "%");
        }
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Integer[] getGroupMemberIds(String groupName) {
        Integer[] res = null;
        Query query = entityManager.createNativeQuery("SELECT u.user_id FROM service_user u WHERE u.group_name = ?");
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
    public void setRoleToUser(String role, Integer userId) {
        Query query = null;
        if (role.equals(ROLE_ADMIN)) {
            query = entityManager.createNativeQuery("UPDATE service_user SET admin = TRUE WHERE user_id = ?1");
        } else if (role.equals(ROLE_DRIVER)) {
            query = entityManager.createNativeQuery("UPDATE service_user SET driver = TRUE WHERE user_id = ?1");
        }
        if (query != null) {
            query.setParameter(1, userId);
            query.executeUpdate();
        }
    }

    @Override
    public void removeGroup(String groupName) {
        if (existsGroup(groupName)) {
            Query query = entityManager.createNativeQuery("UPDATE service_user " +
                    "SET group_name = NULL WHERE group_name = ?");
            query.setParameter(1, groupName);
            query.executeUpdate();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Integer getGroupsCountByName(String groupName) {
        Query query = entityManager.createNativeQuery("SELECT COUNT (DISTINCT g.group_name) " +
                "FROM service_user g WHERE g.group_name LIKE ?");
        query.setParameter(1, "%" + groupName + "%");
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public Integer getUsersCountByEmail(String userEmail) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM service_user g WHERE g.email LIKE ?1 ");
        query.setParameter(1, "%" + userEmail + "%");
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public Integer getUsersAllCount() {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM service_user");
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public Integer getUsersInGroupCount(String groupName) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM service_user g WHERE g.group_name = ?1");
        query.setParameter(1, groupName);
        return ((BigInteger)query.getSingleResult()).intValue();
    }

    @Override
    public void addUserToGroup(String groupName, Integer userId) {
        Query query = entityManager.createNativeQuery("UPDATE service_user SET group_name = ?1 WHERE user_id = ?2");
        query.setParameter(1, groupName);
        query.setParameter(2, userId);
        query.executeUpdate();
    }

    @Override
    public void removeUser(Integer userId) {
        Query query = entityManager.createNativeQuery("UPDATE service_user SET group_name = DEFAULT WHERE user_id = ?");
        query.setParameter(1, userId);
        query.executeUpdate();
    }

    @Override
    public boolean existsGroup(String groupName) {
        Query query = entityManager.createNativeQuery("SELECT DISTINCT COUNT(*) FROM " +
                "service_user u WHERE u.group_name = ?");
        query.setParameter(1, groupName);
        Integer count = ((BigInteger) query.getSingleResult()).intValue();

        return !count.equals(new Integer(0));
    }

    public String getGroupRole(String groupName) {
        Query query = entityManager.createNativeQuery("SELECT COUNT * FROM service_user u " +
                "WHERE u.group_name = ? AND  u.admin = TRUE");
        query.setParameter(1, groupName);
        int countAdmins = (Integer) query.getSingleResult();

        query = entityManager.createNativeQuery("SELECT COUNT * FROM service_user u " +
                "WHERE u.group_name = ? AND  u.driver = TRUE");
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
    public void updateUserRoles(Integer userId, boolean isDriver, boolean isAdmin) {
        Query query = entityManager.createNativeQuery("UPDATE service_user SET admin = ?1, driver = ?2 WHERE user_id = ?3");
        query.setParameter(1, isAdmin);
        query.setParameter(2, isDriver);
        query.setParameter(3, userId);
        query.executeUpdate();
    }

}
