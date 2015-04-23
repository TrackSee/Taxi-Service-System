package com.netcracker.tracksee.entities;

/**
 * @author Ruslan Gunavardana.
 */
public class Address {
    private String address;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
