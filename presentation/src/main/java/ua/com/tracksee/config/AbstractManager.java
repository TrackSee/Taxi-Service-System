package ua.com.tracksee.config;

import ua.com.tracksee.config.error.ManagerError;

import java.util.ResourceBundle;

/**
 * Created by byte on 4/24/15.
 */
public abstract class AbstractManager {


    public String getString(String key){
        return getBundle().getString(key);
    }

    public Integer getInteger(String key) throws ManagerError {
        try {
            return Integer.parseInt(getBundle().getString(key));
        } catch (Exception e){
            throw new ManagerError("parse int param error!");
        }
    }

    public abstract ResourceBundle getBundle();

}
