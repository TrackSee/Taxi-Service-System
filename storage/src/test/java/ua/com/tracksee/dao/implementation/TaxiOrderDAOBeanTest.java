package ua.com.tracksee.dao.implementation;


import com.vividsolutions.jts.geom.LineString;
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
import ua.com.tracksee.dao.implementation.exceptions.CarNotFoundException;
import ua.com.tracksee.dao.implementation.exceptions.ServiceUserNotFoundException;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;
import ua.com.tracksee.enumartion.Sex;
import ua.com.tracksee.error.PersistError;

import javax.ejb.EJB;
import java.io.File;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;
import static ua.com.tracksee.enumartion.OrderStatus.QUEUED;

/**
 * @author Ruslan Gunavardana
 */
@RunWith(Arquillian.class)
public class TaxiOrderDAOBeanTest {

    private @EJB
    TaxiOrderDAOBean taxiOrderDAO;

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
                .addPackage(TaxiOrderEntity.class.getPackage())
                .addPackage(TaxiOrderDAOBean.class.getPackage())
                .addPackage(TaxiOrderDAO.class.getPackage())
                .addPackage(CarNotFoundException.class.getPackage())
                .addPackage(PersistError.class.getPackage())
                .addClass(ServiceUserNotFoundException.class)
                .addPackage(Sex.class.getPackage())
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
    public void testAddOrder() throws Exception {
        TaxiOrderEntity order = new TaxiOrderEntity(QUEUED);
        order.setNonSmokingDriver(TRUE);
        order.setFreeWifi(TRUE);
        order.setDescription("I like to mov it mov it");


        Long trackingNumber = taxiOrderDAO.addOrder(order);

        TaxiOrderEntity databaseOrder = taxiOrderDAO.getOrder(trackingNumber);
        assertEquals(databaseOrder.getTrackingNumber(), trackingNumber);
        assertEquals(databaseOrder.getNonSmokingDriver(), order.getNonSmokingDriver());
        assertEquals(databaseOrder.getFreeWifi(), order.getFreeWifi());
        assertEquals(databaseOrder.getStatus(), order.getStatus());
    }
    @Test
    public void testGetOrder(){
        TaxiOrderEntity order = taxiOrderDAO.getOrder(Long.valueOf(3));
        List<TaxiOrderItemEntity> items = order.getItemList();
        for(int i = 0; i < items.size(); i++){
            LineString path = items.get(i).getPath();
            System.out.println(path);
        }
    }
}
