package ua.com.tracksee.logic;

import ua.com.tracksee.enumartion.Role;

import javax.ejb.Stateless;


/**
 * Created by Vitalii Diravka on 18.05.2015.
 */
@Stateless
public class UserRoleBean {
    public String getRole(Boolean admin, Boolean driver){
//        String role = "";
        if (admin == true) {
            return "admin";
        } else if (driver == true && admin ==false){
            return "driver";
        } else if (driver == false && admin ==false){
            return "register_customer";
        } else {
            return "undefined";
        }
    }
}
