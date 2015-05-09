package ua.com.tracksee.dao.postrgresql;

import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.AddressEntityPK;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class AddressDAOBean implements AddressDAO {

    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public AddressEntity getAddress(AddressEntityPK pk) {
        String sql = "SELECT * FROM address " +
                "WHERE user_id = ?1 AND name = ?2";
        Query query = entityManager.createNativeQuery(sql , AddressEntity.class);
        query.setParameter(1, pk.getUserId());
        query.setParameter(2, pk.getName());
        return (AddressEntity) query.getSingleResult();
    }

    @Override
    public List<AddressEntity> getAddressesByUserId(int id){
        String sql = "SELECT * FROM address WHERE user_id = ?";
        Query query = entityManager.createNativeQuery(sql, AddressEntity.class);
        query.setParameter(1, id);
        return (List<AddressEntity>) query.getResultList();
    }

    @Override
    public boolean addAddress(AddressEntity address) {
        String sql = "INSERT INTO Address " +
                "(name, user_id, location) " +
                "VALUES (?1, ?2, ?3)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, address.getName());
        query.setParameter(2, address.getUserId());
        query.setParameter(3, address.getLocation());
        try {
            query.executeUpdate();
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }

    @Override
    public boolean deleteAddress(AddressEntityPK addressPK) {
        String sql = "DELETE FROM address " +
                "WHERE user_id = ?1 AND name = ?2";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, addressPK.getUserId());
        query.setParameter(2, addressPK.getName());
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean updateAddress(AddressEntityPK pk, AddressEntity newValue) {
        String sql = "UPDATE address SET " +
                "user_id = ?1, name = ?2, location = ?3 " +
                "WHERE user_id = ?4 AND name = ?5";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, newValue.getLocation());
        query.setParameter(2, newValue.getName());
        query.setParameter(3, newValue.getLocation());
        query.setParameter(4, pk.getUserId());
        query.setParameter(5, pk.getName());
        try {
            return query.executeUpdate() == 1;
        } catch (PersistenceException e) {
            return false;
        }
    }
}
