package com.netcracker.tracksee.dao.postrgresql;

import com.netcracker.tracksee.dao.AddressDAO;
import com.netcracker.tracksee.dao.TaxiOrderDAO;
import com.netcracker.tracksee.entities.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
* @author Sharaban Sasha
*/
@Stateless
public class AddressDAOBean implements AddressDAO {
    private static final Logger logger = LogManager.getLogger();
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;

    @Override
    public void addAddress(Address address) {
        try {


            String sql = "INSERT INTO address (name) " +
                    "VALUES(?)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, address.getAddress());
            query.executeUpdate();
        }catch(Exception ex){
        //    ex.printStackTrace();
            System.out.println("AddressDAO");
        }
    }
}
