package com.netcracker.bootcamp.tracksee.entity;

/**
 * @author Ruslan Gunavardana.
 */
public class User {
    private Role role;
    private Sex sex;
    private String email;
    private String password;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
