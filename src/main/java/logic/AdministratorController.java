package logic;


import entity.Driver;
import entity.User;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * @author Ruslan Gunavardana.
 */
@Stateless
@Local
@DeclareRoles({"admin"})
@RolesAllowed({"admin"})
public class AdministratorController extends User {

    /**
     * Admin
     * @return
     */
    public Driver createDriverAccount() {
        return null;
    }

    /**
     * Admin
     */
    public void deleteDriverAccount() {

    }

    /**
     * Admin
     */
    public void editDriverAccount() {

    }

    /**
     * Admin
     * TODO O
     */
    public void blockAccount() {

    }
}
