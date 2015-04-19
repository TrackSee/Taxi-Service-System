package com.netcracker.bootcamp.tracksee.entities;

import javax.persistence.*;


/**
 * Created by Vadym_Akymov on 19.04.15.
 */
@Entity
@Table(name = "service_user", schema = "public", catalog = "taxi")
public class ServiceUserEntity {
    private int userId;
    private String email;
    private String password;
    private String phone;
    private String sex;
    private Boolean isdriver;
    private Boolean isadmin;
    private String groupName;
    private String driverLicense;
    private Integer ignoredTimes;
    private Boolean activated;
    private CarEntity carByCarNumber;

    @Id
    @Column(name = "user_id", insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "isdriver")
    public Boolean getIsdriver() {
        return isdriver;
    }

    public void setIsdriver(Boolean isdriver) {
        this.isdriver = isdriver;
    }

    @Basic
    @Column(name = "isadmin")
    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "driver_license")
    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Basic
    @Column(name = "ignored_times")
    public Integer getIgnoredTimes() {
        return ignoredTimes;
    }

    public void setIgnoredTimes(Integer ignoredTimes) {
        this.ignoredTimes = ignoredTimes;
    }

    @Basic
    @Column(name = "activated")
    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceUserEntity that = (ServiceUserEntity) o;

        if (userId != that.userId) return false;
        if (activated != null ? !activated.equals(that.activated) : that.activated != null) return false;
        if (driverLicense != null ? !driverLicense.equals(that.driverLicense) : that.driverLicense != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (ignoredTimes != null ? !ignoredTimes.equals(that.ignoredTimes) : that.ignoredTimes != null) return false;
        if (isadmin != null ? !isadmin.equals(that.isadmin) : that.isadmin != null) return false;
        if (isdriver != null ? !isdriver.equals(that.isdriver) : that.isdriver != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (isdriver != null ? isdriver.hashCode() : 0);
        result = 31 * result + (isadmin != null ? isadmin.hashCode() : 0);
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (driverLicense != null ? driverLicense.hashCode() : 0);
        result = 31 * result + (ignoredTimes != null ? ignoredTimes.hashCode() : 0);
        result = 31 * result + (activated != null ? activated.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "car_number", referencedColumnName = "car_number")
    public CarEntity getCarByCarNumber() {
        return carByCarNumber;
    }

    public void setCarByCarNumber(CarEntity carByCarNumber) {
        this.carByCarNumber = carByCarNumber;
    }
}
