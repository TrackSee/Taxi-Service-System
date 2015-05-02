package ua.com.tracksee.dao.postrgresql;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Sasha on 5/2/2015.
 */
@Stateless(name = "CanselDAOBeanEJB")
public class CanselDAOBean {
    @PersistenceContext(unitName = "HibernatePU")
    private EntityManager entityManager;
    private int incrimentUserIgnoredTimes(long orderId){
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
    public void canselOrder(long orderId){
        incrimentUserIgnoredTimes(orderId);
    }
}
