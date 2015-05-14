package ua.com.tracksee.logic.facade;

import ua.com.tracksee.enumartion.Role;
import ua.com.tracksee.logic.GroupBean;
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
}
