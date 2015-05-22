package ua.com.tracksee.logic.facade;

import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.enumartion.Role;
import ua.com.tracksee.exception.RegistrationException;
import ua.com.tracksee.logic.GroupBean;
import ua.com.tracksee.logic.admin.AdministratorBean;
import ua.com.tracksee.logic.group.GroupSelectAction;
import ua.com.tracksee.logic.group.GroupSelectCountAction;
import ua.com.tracksee.logic.group.GroupUpdateAction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Ruslan Gunavardana
 */
@Stateless
public class AdminFacade {
    @EJB
    private GroupBean groupBean;

    @EJB
    private AdministratorBean administratorBean;

    public void groupExecuteUpdate(GroupUpdateAction action, String groupName, String[] ids, Role role,
                                   String[] userIdsStrings, boolean[] idAdmins, boolean[] isDrivers, Integer admin) {
        groupBean.executeUpdate(action, groupName, ids, role, userIdsStrings, idAdmins, isDrivers, admin);
    }

    public List groupExecuteSelect(GroupSelectAction action, String groupName, String userEmail,
                                   Integer pageNumber, Integer pageSize) {
        return groupBean.executeSelect(action, groupName, userEmail, pageNumber, pageSize);
    }

    public Integer groupExecuteSelectCount(GroupSelectCountAction action, String groupName, String userEmail) {
        return groupBean.executeSelectCount(action, groupName, userEmail);
    }

    public boolean existsGroup(String groupName) {
        return groupBean.existsGroup(groupName);
    }


    public List<UserEntity> getUsers() {
        return administratorBean.getUsers();
    }

    public Integer addUser(UserEntity user) {
        return administratorBean.addUser(user);
    }

    public void updateUser(UserEntity user) {
        administratorBean.updateUser(user);
    }

    public void createUser(UserEntity user) throws RegistrationException {
        administratorBean.createUser(user);
    }

    public void getDriverById(int driverId) {
        administratorBean.getDriverByID(driverId);
    }

    public UserEntity getUserById(int userId) {
        return administratorBean.getUserById(userId);
    }

    public List<CarEntity> getCars() {
        return administratorBean.getCars();
    }

    public void deleteUser(int id) {
        administratorBean.deleteUser(id);
    }

    public List<UserEntity> getDriversByEmail(String email) {
        return administratorBean.getDriversByEmail(email);
    }

    ;

    public List<UserEntity> getCustomersByEmail(String email) {
        return administratorBean.getCustomersByEmail(email);
    }

    ;

    /**
     * @author Vadym Akymov
     */
    public void assignCar(String carNumber, Integer driverID) {
        administratorBean.assignCar(carNumber, driverID);
    }

    /**
     * @param carEntity entity  creating
     * @author Katia Stetsiuk
     */
    public void createCar(CarEntity carEntity) {
        administratorBean.createCar(carEntity);
    }

    /**
     * @param carEntity entity for updating
     * @author Katia Stetsiuk
     */
    public void updateCar(CarEntity carEntity) {
        administratorBean.updateCar(carEntity);
    }

    /**
     * @param carNumber car number for deleting
     * @author Katia Stetsiuk
     */
    public void deleteCar(String carNumber) {
        administratorBean.deleteCar(carNumber);
    }

    /**
     * @param partNumber number of part to review
     * @return
     * @author Katia Stetsiuk
     */
    public List<CarEntity> getCarsPart(int partNumber) {
        return administratorBean.getCarsPart(partNumber);
    }

    /**
     * @return number of pages for review driver list
     */
    public int getCarPagesCount() {
        return administratorBean.getCarPagesCount();
    }


    /**
     * @return count of drivers per page
     * @author Vadym Akymov
     */
    public int getDriverPagesCount() {
        return administratorBean.getDriverPagesCount();
    }
    /**
     * @author Vadym Akymov, Katia Stetsiuk
     */
    public List<CarEntity> getAllFreeCars(){
        return administratorBean.getAllFreeCars();
    }

    /**
     * @author Vadym Akymov, Katia Stetsiuk
     */
    public CarEntity getCarByNumber(String carNumber){
        return administratorBean.getCarByNumber(carNumber);
    }
    /**
     * @author Vadym Akymov
     */
    public UserEntity getDriverByID(int id){
        return administratorBean.getDriverByID(id);
    }

}


