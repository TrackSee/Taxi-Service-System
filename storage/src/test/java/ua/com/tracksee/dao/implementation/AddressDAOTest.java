package ua.com.tracksee.dao.implementation;

import org.junit.BeforeClass;
import org.junit.Test;
import org.postgresql.geometric.PGpoint;
import org.postgresql.jdbc2.optional.SimpleDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 * @author Ruslan Gunavardana
 */
public class AddressDAOTest {
    private static EntityManager em;

    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";

    @BeforeClass
    public static void setUp() throws Exception {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/tracksee");
        dataSource.setUser(USER_NAME);
        dataSource.setPassword(PASSWORD);

        // Create initial context
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.naming.java.javaURLContextFactory");
        System.setProperty(Context.URL_PKG_PREFIXES,
                "org.apache.naming");
        InitialContext ic = new InitialContext();

        ic.createSubcontext("java:jboss");
        ic.createSubcontext("java:jboss/datasources");

        InitialContext context = new InitialContext();
        context.bind("java:jboss/datasources/trackseeDS", dataSource);
        EntityManagerFactory factory = createEntityManagerFactory("HibernatePU");
        System.out.println(factory);
        em = factory.createEntityManager();
    }

    @Test
    public void testName() throws Exception {
        em.getTransaction().begin();
        String sql = "INSERT INTO address (name, user_id, location) VALUES (?1, ?2, ?3)";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, "Home");
        query.setParameter(2, 1);
        query.<PGpoint>setParameter(3, new PGpoint("(10, 10)"));
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
