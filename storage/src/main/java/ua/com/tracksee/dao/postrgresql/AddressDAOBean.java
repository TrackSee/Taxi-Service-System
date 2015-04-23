package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.AddressDAO;
import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kstes_000 on 23-Apr-15.
 */
public class AddressDAOBean implements AddressDAO {
    public static final int ADDRESSES_LIMIT = 5;
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public void addAddress(AddressEntity address) {
        entityManager.persist(address);
    }

    @Override
    public void deleteAddress(AddressEntity address) {
        String sql = "DELETE from address where name = " + address.getName();
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void updateAddress(AddressEntity addressEntity) {
        entityManager.refresh(addressEntity);
    }


    @Override
    public List<AddressEntity> getAddresses(int partNumber) {
        if (partNumber <= 0) {
            logger.error("partNumber can't be <= 0");
            throw new IllegalArgumentException("partNumber can't be <= 0");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM service_user " +
                "WHERE driver = TRUE LIMIT ?1 OFFSET ?2", ServiceUserEntity.class);
        query.setParameter(1, ADDRESSES_LIMIT);
        query.setParameter(2, (partNumber - 1) * ADDRESSES_LIMIT);
        return query.getResultList();
    }
}
