package ua.com.tracksee.dao.implementation;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.tracksee.entities.FavoritePlaceEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author Ruslan Gunavardana
 */
public class FavoritePlaceDAOTest {
    private static EntityManager em;

    private String TEST_ADDRESS_NAME = "HOME";
    private Integer TEST_USER_ID = 34;
    private Point TEST_POINT;

    @BeforeClass
    public static void setEntityManager() throws Exception {
        em = PostgresEntityManagerFactory.getEnityManager();
    }

    @Before
    public void setUp() throws Exception {
        TEST_POINT = (Point) wktToGeometry("POINT(10 5)");
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM Favorite_Place").executeUpdate();
        em.getTransaction().commit();
    }

    private Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom;
        try {
            geom = fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }

    @Test
    public void testAdd() throws Exception {
        em.getTransaction().begin();
        String sql = "INSERT INTO Favorite_Place (name, user_id, location) VALUES (?1, ?2, ?3)";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, TEST_ADDRESS_NAME);
        query.setParameter(2, TEST_USER_ID);
        query.setParameter(3, TEST_POINT);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    public void testGet() throws Exception {
        try {
            testAdd();
        } catch (Exception e) {
            // If happens, than constraint violation happened.
            // This means, that the row already exists, just as we need!
        }
        String sql = "SELECT * FROM Favorite_Place WHERE user_id = ?1 AND name = ?2";
        Query query = em.createNativeQuery(sql, FavoritePlaceEntity.class);
        query.setParameter(1, TEST_USER_ID);
        query.setParameter(2, TEST_ADDRESS_NAME);
        FavoritePlaceEntity address = (FavoritePlaceEntity) query.getSingleResult();
        Assert.assertEquals(TEST_POINT, address.getLocation());
    }
}
