package ua.com.tracksee.logic;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.postgresql.geometric.PGpoint;
import org.postgresql.util.PGBinaryObject;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.UserDAOBean;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.EJB;
import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ruslan Gunavardana
 */
public class ValidationBeanTest {

    private @EJB ValidationBean validationBean;

    @Deployment
    public	static WebArchive createTestArchive(){
        File[] log4jApi = getLibraryFromMaven("org.apache.logging.log4j","log4j-api","2.2");
        File[] log4jCore = getLibraryFromMaven("org.apache.logging.log4j", "log4j-core", "2.2");
        File[] hibernateLib = getLibraryFromMaven("org.hibernate", "hibernate-core", "4.3.9.Final");
        return	ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(log4jApi)
                .addAsLibraries(log4jCore)
                .addAsLibraries(hibernateLib)
                .addPackage(PGpoint.class.getPackage())
                .addPackage(PGBinaryObject.class.getPackage())
                .addPackage(ServiceUserEntity.class.getPackage())
                .addPackage(ValidationBean.class.getPackage())
                .addPackage(UserDAOBean.class.getPackage())
                .addPackage(UserDAO.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     *  Method is used for getting all library files from maven repo
     */
    private static File[] getLibraryFromMaven(String groupId, String artifactId, String version){
        return Maven.resolver().resolve(groupId + ":" + artifactId + ":" + version)
                .withTransitivity().asFile();
    }

    @Test
    public void testIsValidPassword() throws Exception {

    }

    @Test
    public void testIsValidEmail() throws Exception {

    }

    @Test
    public void testValidatePhoneNumber() throws Exception {
        // good
        assertTrue(validationBean.isValidPhoneNumber("+380635293333"));
        assertTrue(validationBean.isValidPhoneNumber("+38 (063) 529 - 33 - 33"));
        assertTrue(validationBean.isValidPhoneNumber("421 - 08 - 09"));
        assertTrue(validationBean.isValidPhoneNumber("044-421- 08- 09"));

        // bad values
        assertFalse(validationBean.isValidPhoneNumber("+380635helloWORLD"));
        assertFalse(validationBean.isValidPhoneNumber("+38063@12312"));
        assertFalse(validationBean.isValidPhoneNumber("1"));
        assertFalse(validationBean.isValidPhoneNumber("12"));
        assertFalse(validationBean.isValidPhoneNumber("+380"));
    }
}
