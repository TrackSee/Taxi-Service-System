package ua.com.tracksee.dao.implementation;

import org.postgresql.jdbc2.optional.SimpleDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.io.InputStream;
import java.util.Properties;

import static java.lang.System.setProperty;
import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.URL_PKG_PREFIXES;
import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 * @author Ruslan Gunavardana
 */
public class PostgresEntityManagerFactory {
    private static final String PROP_FILE_NAME = "datasource.properties";

    private static EntityManagerFactory factory;

    public static EntityManagerFactory getFactory() throws Exception {
        if (factory == null) {
            ClassLoader loader = PostgresEntityManagerFactory.class.getClassLoader();
            InputStream stream = loader.getResourceAsStream(PROP_FILE_NAME);
            Properties properties = new Properties();
            properties.load(stream);

            SimpleDataSource dataSource = new SimpleDataSource();
            dataSource.setUrl(properties.getProperty("database.url"));
            dataSource.setUser(properties.getProperty("database.user"));
            dataSource.setPassword(properties.getProperty("database.password"));

            // binding datasource
            setProperty(INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            setProperty(URL_PKG_PREFIXES, "org.apache.naming");
            InitialContext ic = new InitialContext();
            ic.createSubcontext("java:jboss");
            ic.createSubcontext("java:jboss/datasources");
            ic.bind("java:jboss/datasources/trackseeDS", dataSource);
            factory = createEntityManagerFactory("HibernatePU");
        }

        return factory;
    }

    public static EntityManager getEnityManager() throws Exception {
        return getFactory().createEntityManager();
    }
}
