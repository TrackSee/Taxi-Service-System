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
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.enumartion.Sex;

import javax.ejb.EJB;
import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by Igor on 04.05.2015.
 */
@RunWith(Arquillian.class)
public class OrderDAOBeanTest {
    @EJB
    private TaxiOrderDAO taxiOrderDAO;

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

    /**
     *  Method is used for getting all library files from maven repo
     */
    private static File[] getLibraryFromMaven(String groupId, String artifactId, String version){
        return Maven.resolver().resolve(groupId + ":" + artifactId + ":" + version)
                .withTransitivity().asFile();
    }


    @Test
    public void updateComment() throws Exception {
        int i = taxiOrderDAO.updateComment(4, "test comment");
        assertTrue(i == 1);
        taxiOrderDAO.updateComment(4, "everything is good");
    }

    @Test
    public void updateOrder() throws Exception {
        TaxiOrderEntity entity = new TaxiOrderEntity();
        entity.setDriverSex(Sex.M);
        long trackNumb=4;
        entity.setTrackingNumber(trackNumb);
        entity.setAirConditioner(Boolean.FALSE);
        taxiOrderDAO.updateOrder(entity);
    }
}
