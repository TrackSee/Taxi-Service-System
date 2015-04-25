package ua.com.tracksee.dao.postrgresql.exceptions;

/**
 * Created by Vadym_Akymov on 25.04.15.
 */
public class ServiceUserNotFoundException extends Exception {
    public ServiceUserNotFoundException(){
        super();
    }
    public ServiceUserNotFoundException(String message){
        super(message);
    }
}
