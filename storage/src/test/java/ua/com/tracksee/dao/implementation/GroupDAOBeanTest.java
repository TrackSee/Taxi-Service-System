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
import ua.com.tracksee.dao.GroupDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.EJB;
import javax.persistence.EntityTransaction;
import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by Igor Gula on 28.04.2015.
 */
@RunWith(Arquillian.class)
public class GroupDAOBeanTest {
    @EJB
    private GroupDAO groupDAO;

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
                .addPackage(UserEntity.class.getPackage())
                .addPackage(UserDAOBean.class.getPackage())
                .addPackage(UserDAO.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private static File[] getLibraryFromMaven(String groupId, String artifactId, String version){
        return Maven.resolver().resolve(groupId + ":" + artifactId + ":" + version)
                .withTransitivity().asFile();
    }

    @Test
    public void testRemoveUser() throws Exception {
        //TODO working code
//        EntityTransaction et;
//        et = groupDAO.getEntityManager().getTransaction();
//        et.begin();
//        int count = groupDAO.removeGroup("regular client");
//        et.commit();
//        assertTrue(count == 1);
//        et.rollback();
    }
}
