package com.netcracker.bootcamp.tracksee.util.config;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.ResourceBundle;

/**
 * Created by byte on 4/18/15.
 *
 */
@Singleton(name = "ConfigManagerEJB")
public class ConfigManagerBean {

    private ResourceBundle resourceBundle;

    public ConfigManagerBean() {
        resourceBundle = ResourceBundle.getBundle("config");
    }

    /**
     * method which return config from rescorces/config.properties
     * @param key search key
     * @return some config
     */
    @Lock(LockType.READ)
    public String getString(String key){
        return resourceBundle.getString(key);
    }
}
