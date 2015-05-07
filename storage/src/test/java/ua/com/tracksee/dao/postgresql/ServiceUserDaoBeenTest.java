package ua.com.tracksee.dao.postgresql;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.geometric.PGpoint;
import org.postgresql.util.PGBinaryObject;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.dao.postrgresql.ServiceUserDaoBeen;
import ua.com.tracksee.dao.postrgresql.UserDAOBean;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.enumartion.Sex;
import ua.com.tracksee.error.PersistError;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by byte on 4/25/15.
 */
@RunWith(Arquillian.class)
public class ServiceUserDaoBeenTest {

    @EJB
    ServiceUserDaoBeen daoBeen;

    @EJB
    EntityManager em;

    @Deployment
    public static WebArchive createTestArchive() {
        File[] log4jApi = getLibraryFromMaven("org.apache.logging.log4j", "log4j-api", "2.2");
        File[] log4jCore = getLibraryFromMaven("org.apache.logging.log4j", "log4j-core", "2.2");
        File[] hibernateLib = getLibraryFromMaven("org.hibernate", "hibernate-core", "4.3.9.Final");
        return ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(log4jApi)
                .addAsLibraries(log4jCore)
                .addAsLibraries(hibernateLib)
                .addPackage(PGpoint.class.getPackage())
                .addPackage(PGBinaryObject.class.getPackage())
                .addPackage(ServiceUserEntity.class.getPackage())
                .addPackage(UserDAOBean.class.getPackage())
                .addPackage(Sex.class.getPackage())
                .addPackage(PersistError.class.getPackage())
                .addPackage(UserDAO.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /*
    @author Vadym
    * Method is used for getting all library files from maven repo
     */
    private static File[] getLibraryFromMaven(String groupId, String artifactId, String version) {
        return Maven.resolver().resolve(groupId + ":" + artifactId + ":" + version)
                .withTransitivity().asFile();
    }


    @Test
    public void testServiceUserDaoGetUsersById() throws Exception {
        List<ServiceUserEntity> entities = daoBeen.getAll();
        List<Integer> usersId = new ArrayList<>();
        for (ServiceUserEntity serviceUserEntity:entities){
            usersId.add(serviceUserEntity.getUserId());
        }
        List<ServiceUserEntity> entityList = daoBeen.getAllById(usersId);
        assertTrue(entityList.size() == usersId.size());
    }
}
