package ua.com.tracksee.dao.implementation;

import com.vividsolutions.jts.geom.Point;
import ua.com.tracksee.dao.FavoritePlaceDAO;
import ua.com.tracksee.entities.FavoritePlaceEntity;
import ua.com.tracksee.entities.FavoritePlaceEntityPK;

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
public class FavoritePlaceDAOBean implements FavoritePlaceDAO {

    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public FavoritePlaceEntity getAddress(FavoritePlaceEntityPK pk) {
        String sql = "SELECT * FROM Favorite_Place " +
                "WHERE user_id = ?1 AND name = ?2";
        Query query = entityManager.createNativeQuery(sql , FavoritePlaceEntity.class);
        query.setParameter(1, pk.getUserId());
        query.setParameter(2, pk.getName());
        return (FavoritePlaceEntity) query.getSingleResult();
    }

    @Override
    public List<FavoritePlaceEntity> getAddressesByUserId(int id){
        String sql = "SELECT * FROM Favorite_Place WHERE user_id = ?";
        Query query = entityManager.createNativeQuery(sql, FavoritePlaceEntity.class);
        query.setParameter(1, id);
        return (List<FavoritePlaceEntity>) query.getResultList();
    }

    @Override
    public boolean addAddress(FavoritePlaceEntity address) {
        String sql = "INSERT INTO Favorite_Place " +
                "(name, user_id, location) " +
                "VALUES (?1, ?2, CAST(?3 AS GEOMETRY))";
        Query query = entityManager.createNativeQuery(sql);
        query.<String>setParameter(1, address.getName());
        query.<Integer>setParameter(2, address.getUserId());
        query.<String>setParameter(3, address.getLocation().toText());
        try {
            query.executeUpdate();
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }

    @Override
    public boolean deleteAddress(FavoritePlaceEntityPK addressPK) {
        String sql = "DELETE FROM Favorite_Place " +
                "WHERE user_id = ?1 AND name = ?2";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, addressPK.getUserId());
        query.setParameter(2, addressPK.getName());
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean updateAddress(FavoritePlaceEntityPK pk, FavoritePlaceEntity newValue) {
        String sql = "UPDATE Favorite_Place SET " +
                "user_id = ?1, name = ?2, location = ?3 " +
                "WHERE user_id = ?4 AND name = ?5";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, newValue.getLocation());
        query.setParameter(2, newValue.getName());
        query.setParameter(3, newValue.getLocation().toText());
        query.setParameter(4, pk.getUserId());
        query.setParameter(5, pk.getName());
        try {
            return query.executeUpdate() == 1;
        } catch (PersistenceException e) {
            return false;
        }
    }
}
