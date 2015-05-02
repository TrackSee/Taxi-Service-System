package ua.com.tracksee.dao.postrgresql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.enumartion.OrderStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;
import java.sql.SQLException;

/**
 * Created by Sasha on 5/2/2015.
 */
@Stateless(name = "CanselDAOBeanEJB")
public class CanselDAOBean {
    private static final Logger logger = LogManager.getLogger();
    @EJB
    TaxiOrderDAO taxiOrderDAO;
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;
    private int incrimentUserIgnoredTimes(long orderId) throws SQLException{
        String sql = "UPDATE service_user \n" +
                "SET ignored_times=1+\n" +
                "(\n" +
                "  SELECT ignored_times\n" +
                "  FROM service_user\n" +
                "    INNER JOIN taxi_order\n" +
                "    ON service_user.user_id = taxi_order.user_id\n" +
                "    WHERE taxi_order.tracking_number=?1\n" +
                ")\n" +
                "WHERE user_id=\n" +
                "      (SELECT user_id \n" +
                "       FROM taxi_order \n" +
                "       WHERE tracking_number=?1)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,orderId);
        return query.executeUpdate();
    }
    private int setRefusedOrder(long orderId){
        String sql ="UPDATE taxi_order\n" +
                "SET status=?1\n" +
                "WHERE tracking_number=?2";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,OrderStatus.REFUSED.toString());
        query.setParameter(2, orderId);
        return query.executeUpdate();
    }
    public boolean canselOrder(long trackingNumber){
//        EntityTransaction transaction=entityManager.getTransaction();
//        transaction.begin();
        setRefusedOrder(trackingNumber);
        try {
            incrimentUserIgnoredTimes(trackingNumber);
        } catch (SQLException e) {
            logger.error("something wrong when incriment \"ignored times\" for user ");
            logger.error(e.toString());
//            transaction.rollback();
            return false;
        }
//        transaction.commit();
        return true;
    }

    public int getUserRefusedTimes(long trackingNumber) {
        String sql ="SELECT\n" +
                "  ignored_times\n" +
                "FROM\n" +
                "  service_user\n" +
                "INNER JOIN\n" +
                "  taxi_order\n" +
                "ON\n" +
                "  service_user.user_id = taxi_order.user_id\n" +
                "WHERE\n" +
                "  taxi_order.tracking_number=?1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,trackingNumber);
        return (Integer)query.getSingleResult();
    }
}
