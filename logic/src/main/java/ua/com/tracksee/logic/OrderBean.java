package ua.com.tracksee.logic;

import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.sql.Timestamp;
import java.util.List;

import static ua.com.tracksee.enumartion.OrderStatus.QUEUED;

/**
 * Bean used for any order processing business logic.
 *
 * @author Ruslan Gunavardana
 */
@Stateless
@DeclareRoles({"customer", "unregistered", "driver"})
public class OrderBean {
    @EJB
    private TaxiOrderDAO taxiOrderDao;

    /**
     * Creates taxi order of authorised user.
     */
    @RolesAllowed("customer")
    public void createAuthorisedOrder(Integer userId) {
        TaxiOrderEntity order = new TaxiOrderEntity();
        order.setUserId(userId);
        order.setStatus(QUEUED);

    }

    @RolesAllowed("driver")
    public List<TaxiOrderEntity> getAvailableOrders(ServiceUserEntity driver){
        return taxiOrderDao.getAvailableOrders(driver);
    }

    @RolesAllowed("driver")
    public List<TaxiOrderEntity> getHistoryOfOrders(ServiceUserEntity driver){
        return taxiOrderDao.getHistoryOfOrders(driver);
    }

    @RolesAllowed("driver")
    public TaxiOrderEntity getAssignedOrder(ServiceUserEntity driver){
        return taxiOrderDao.getAssignedOrder(driver);
    }

    @RolesAllowed("driver")
    public void setAssignOrder(ServiceUserEntity driver, TaxiOrderEntity taxiOrderEntity, Timestamp carArriveTime){
        taxiOrderDao.setAssignOrder(driver, taxiOrderEntity, carArriveTime);
    }

    @RolesAllowed("driver")
    public void setInProgressOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setInProgressOrder(taxiOrderEntity);
    }

    @RolesAllowed("driver")
    public void setCompletedOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setCompletedOrder(taxiOrderEntity);
    }

    @RolesAllowed("driver")
    public void setRefusedOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setRefusedOrder(taxiOrderEntity);
    }

}
