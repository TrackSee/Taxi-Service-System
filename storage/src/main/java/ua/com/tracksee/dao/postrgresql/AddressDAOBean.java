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
                "(name, user_id, string_representation, location) " +
                "VALUES (?,?,?,?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, address.getName());
        query.setParameter(2, address.getUserId());
        query.setParameter(3, address.getStringRepresentation());
        query.setParameter(4, address.getLocation());
        try {
            query.executeUpdate();
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }

    @Override
    public boolean deleteAddress(AddressEntity address) {
        String sql = "DELETE FROM address " +
                "WHERE user_id = ?1 AND name = ?2";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, address.getUserId());
        query.setParameter(2, address.getName());
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean updateLocation(AddressEntity addressEntity) {
        String sql = "UPDATE address SET " +
                "string_representation = ?1, location = ?2 " +
                "WHERE user_id = ?3 AND name = ?4";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, addressEntity.getStringRepresentation());
        query.setParameter(2, addressEntity.getLocation());
        query.setParameter(3, addressEntity.getUserId());
        query.setParameter(4, addressEntity.getName());
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean updateName(AddressEntity addressEntity, String newName) {
        String sql = "UPDATE address SET " +
                "name = ?1" +
                "WHERE user_id = ?2 AND name = ?3";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, newName);
        query.setParameter(2, addressEntity.getUserId());
        query.setParameter(3, addressEntity.getName());
        return query.executeUpdate() == 1;
    }
}
