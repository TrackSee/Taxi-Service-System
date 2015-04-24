package com.netcracker.tracksee.entities;

/**
 * @author Ruslan Gunavardana.
 */
public class User {
    private int UserId;
    private String email;
    private String password;
    private long phone;
    public long getPhone() {
		return phone;
	}
    private boolean activated;

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setPhone(long phone) {
		this.phone = phone;
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
