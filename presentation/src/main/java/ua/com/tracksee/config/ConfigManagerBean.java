package ua.com.tracksee.config;

import javax.ejb.Singleton;
import java.util.ResourceBundle;

/**
 * Created by byte on 4/19/15.
 */
@Singleton(name = "ConfigManagerEJB")
public class ConfigManagerBean {

    private ResourceBundle bundle;

    public ConfigManagerBean() {
        bundle = ResourceBundle.getBundle("config");
    }

    public String getString(String key){
        return bundle.getString(key);
    }
}
