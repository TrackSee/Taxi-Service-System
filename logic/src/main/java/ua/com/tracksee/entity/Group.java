package ua.com.tracksee.entity;

import java.math.BigInteger;

/**
 * Created by Igor Gula on 20.04.2015.
 */
public class Group {
    private String name;
    private Integer countUsers;

    public Group(String name,Integer countUsers) {
        this.name = name;
        this.countUsers = countUsers;
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(Integer countUsers) {
        this.countUsers = countUsers;
    }
}

