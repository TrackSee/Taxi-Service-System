package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.GenericDao;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.error.PersistError;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by byte on 4/21/15.
 */
@Stateless(name = "ServiceUserDaoEJB")
public class ServiceUserDaoBeen extends GenericDao<ServiceUserEntity, Integer> {


    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager em;

    public ServiceUserDaoBeen() {
    }

    @Override
    protected EntityManager getEM() {
        return em;
    }

    @Override
    public Class<ServiceUserEntity> getType() {
        return ServiceUserEntity.class;
    }

    public List<ServiceUserEntity> getAll(){
        TypedQuery<ServiceUserEntity> fd = em.createNamedQuery("getAll", getType());
        return fd.getResultList();
    }

    public List<ServiceUserEntity> getAll(int start, int count){
        TypedQuery<ServiceUserEntity> fd = em.createNamedQuery("getAll", getType());
        fd.setFirstResult(start);
        fd.setMaxResults(count);
        return fd.getResultList();
    }

    public List<ServiceUserEntity> getAllById(List<Integer> integers){
        TypedQuery<ServiceUserEntity> typedQuery = em.createNamedQuery("getAllById", getType());
        typedQuery.setParameter("usersId", integers);
        return typedQuery.getResultList();
    }

    public void blockAllById(List<Integer> integers, int blockValue) throws PersistError {
        Query query = em.createNamedQuery("blockAll");
        query.setParameter("userIds", integers);
        query.setParameter("ignoredTimes", blockValue);
        int updateUsersCount = query.executeUpdate();
        if (updateUsersCount != integers.size()) {
            throw new PersistError("some users do not update");
        }
    }

    public long getUsersCount() {
        return em.createNamedQuery("usersSize", Long.class).getSingleResult();
    }
}
