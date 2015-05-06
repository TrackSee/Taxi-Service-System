package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
@Stateless
public class AddressDAOBean implements AddressDAO {
    public static final int ADDRESSES_LIMIT = 5;
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public void addAddress(AddressEntity address) {
        String sql = "INSERT INTO address name, user_id, string representation, location" +
                " VALUES (?,?,?,?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, address.getName());
        query.setParameter(2, address.getUserId());
        query.setParameter(3, address.getStringRepresentation());
        query.setParameter(4, address.getLocation());
        query.executeUpdate();
    }

    @Override
    public void deleteAddress(AddressEntity address) {
        String sql = "DELETE from address where name = " + address.getName();
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void updateAddress(AddressEntity addressEntity) {
        String sql = "UPDATE address Set string_representation = ?, location = ? " +
                "where name = "+ addressEntity.getName();
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, addressEntity.getStringRepresentation());
        query.setParameter(2, addressEntity.getLocation());
        query.executeUpdate();
    }

    @Override
    public List<AddressEntity> getAddresses() {
//        if (partNumber <= 0) {
//            logger.error("partNumber can't be <= 0");
//            throw new IllegalArgumentException("partNumber can't be <= 0");
//        }
        Query query = entityManager.createNativeQuery("SELECT * FROM address " , AddressEntity.class);
//        query.setParameter(1, ADDRESSES_LIMIT);
//        query.setParameter(2, (partNumber - 1) * ADDRESSES_LIMIT);
        return query.getResultList();
    }
    @Override
    public AddressEntity getAddressByUserId(int id){
        String sql = "SELECT * FROM address WHERE user_id=(?)";
        Query query = entityManager.createNativeQuery(sql, AddressEntity.class);
        query.setParameter(1, id);
        return (AddressEntity)query.getSingleResult();
    }
}
