package ua.com.tracksee.entity;

import java.sql.Timestamp;

/**
 * @author Ruslan Gunavardana
 */
public class UnactivatedUser extends User {
    private Timestamp registrationTime;

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }
}
