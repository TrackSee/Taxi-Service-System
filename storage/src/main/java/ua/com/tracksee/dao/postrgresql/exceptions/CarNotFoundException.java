package ua.com.tracksee.dao.postrgresql.exceptions;

/**
 * Created by Katia Stetsiuk on 28-Apr-15.
 */
public class CarNotFoundException extends RuntimeException {

        public CarNotFoundException(){
            super();
        }
        public CarNotFoundException(String message){
            super(message);
        }


}
