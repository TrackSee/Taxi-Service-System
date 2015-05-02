package ua.com.tracksee.config.manager.been;

import ua.com.tracksee.config.AbstractManager;

import javax.ejb.Singleton;
import java.util.ResourceBundle;

/**
 * Created by byte on 4/19/15.
 */
@Singleton(name = "ConfigManagerEJB")
public class ConfigManagerBean extends AbstractManager {

    private ResourceBundle bundle;

    public ConfigManagerBean()
    {
        bundle = ResourceBundle.getBundle("config");
    }

    public ResourceBundle getBundle(){
        return bundle;
    }
}
