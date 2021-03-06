package ua.com.tracksee.dao.implementation;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.geometric.PGpoint;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.implementation.exceptions.CarNotFoundException;
import ua.com.tracksee.dao.implementation.exceptions.ServiceUserNotFoundException;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.enumartion.Sex;
import ua.com.tracksee.error.PersistError;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Vadym_Akymov
 */
//TODO add data initialization

@RunWith(Arquillian.class)
public class UserDAOBeanTest {
    @EJB
    private UserDAO userDAO;

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
                .addPackage(PersistError.class.getPackage())
                .addPackage(CarNotFoundException.class.getPackage())
                .addPackage(UserEntity.class.getPackage())
                .addPackage(UserDAOBean.class.getPackage())
                .addClass(ServiceUserNotFoundException.class)
                .addPackage(Sex.class.getPackage())
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
    public void testGetDrivers() throws Exception {
        //get first part of drivers
        List<UserEntity> drivers = userDAO.getDrivers(2);
        assertEquals(drivers.size(), UserDAO.DRIVERS_PAGE_SIZE);
        for (UserEntity driver : drivers) {
            assertTrue(driver.getDriver());
        }
    }

    @Test(expected = EJBException.class)
    public void testGetDriversException() throws Exception{
        //negative param
        List<UserEntity> drivers = userDAO.getDrivers(-1);
    }
}