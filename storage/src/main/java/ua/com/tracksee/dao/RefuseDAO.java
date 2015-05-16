package ua.com.tracksee.dao;

import ua.com.tracksee.entities.CarEntity;

import javax.ejb.Local;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Avlasov Sasha
 */
@Local
public interface RefuseDAO {
    public int getUserRefusedTimes(long trackingNumber);
    public boolean refuseOrder(long trackingNumber);
}