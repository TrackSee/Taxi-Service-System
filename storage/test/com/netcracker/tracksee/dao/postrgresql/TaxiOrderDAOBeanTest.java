//package com.netcracker.tracksee.dao.postrgresql;
//
//        import com.netcracker.tracksee.dao.TaxiOrderDAO;
//        import org.jboss.arquillian.container.test.api.Deployment;
//        import org.jboss.arquillian.junit.Arquillian;
//        import org.jboss.shrinkwrap.api.Archive;
//        import org.jboss.shrinkwrap.api.ShrinkWrap;
//        import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//        import org.jboss.shrinkwrap.api.spec.WebArchive;
//        import org.junit.Test;
//        import org.junit.runner.RunWith;
//
//        import javax.ejb.EJB;
//
///**
// * @author Sharaba Sasha
// */
//@RunWith(Arquillian.class)
//public class TaxiOrderDAOBeanTest {
//    @EJB
//    private TaxiOrderDAO taxiOrderDAO;
//
//    @Deployment
//    public	static Archive<?> createTestArchive()	{
//        return	ShrinkWrap.create(WebArchive.class)
//                .addPackage(TaxiOrderDAOBean.class.getPackage())
//                .addPackage(TaxiOrderDAO.class.getPackage())
//                .addAsResource("META-INF/persistence.xml")
//                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//    @Test
//    public void test(){
//        System.out.println("Hello word!");
//    }
////
////    @Test
////    public void testGetDrivers() throws Exception {
////        //get first part of drivers
////        List<ServiceUserEntity> drivers = userDAO.getDrivers(1);
////        assertTrue(drivers.size() == UserDAOBean.DRIVERS_LIMIT);
////        for(int i = 0; i < drivers.size(); i++){
////            assertTrue(drivers.get(i).isDriver());
////        }
////        fail();
////    }
////    @Test
////    public void testGetDriversException() throws Exception{
////        //negative param
////        List<ServiceUserEntity> drivers = userDAO.getDrivers(-1);
////        System.out.println("Lol");
////    }
//}
