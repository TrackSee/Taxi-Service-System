package ua.com.tracksee.dao.implementation.exceptions;

/**
 * Created by Vadym_Akymov on 25.04.15.
 */
public class ServiceUserNotFoundException extends RuntimeException {
    public ServiceUserNotFoundException(){
        super();
    }
    public ServiceUserNotFoundException(String message){
        super(message);
    }
}
