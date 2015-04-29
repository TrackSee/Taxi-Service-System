package ua.com.tracksee.config.manager.been;

import ua.com.tracksee.config.AbstractManager;

import javax.ejb.Singleton;
import java.util.ResourceBundle;

/**
 * Created by byte on 4/21/15.
 */
@Singleton(name = "MessageManagerEJB")
public class MessageManagerBean  extends AbstractManager{
    private ResourceBundle bundle;

    public MessageManagerBean() {
        bundle = ResourceBundle.getBundle("message");
    }

    public ResourceBundle getBundle(){
        return bundle;
    }
}
