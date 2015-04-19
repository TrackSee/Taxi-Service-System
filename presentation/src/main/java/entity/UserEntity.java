package entity;

import javax.persistence.*;

/**
 * Created by byte on 4/19/15.
 */
@Entity
@Table(name = "User", schema = "public", catalog = "taxi_service")
public class UserEntity {
    @Id
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "phone")
    private String phone;
    private String sex;
    private Boolean driver;
    @Basic
    @Column(name = "isAdmin")
    private Boolean admin;
    private String driverLicense;
    private Integer ignoredTimes;
    private Boolean activated;


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
    @Column(name = "isDriver")
    public Boolean getDriver() {
        return driver;
    }

    public void setDriver(Boolean driver) {
        this.driver = driver;
    }


    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "driverLicense")
    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Basic
    @Column(name = "ignoredTimes")
    public Integer getIgnoredTimes() {
        return ignoredTimes;
    }

    public void setIgnoredTimes(Integer ignoredTimes) {
        this.ignoredTimes = ignoredTimes;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity entity = (UserEntity) o;

        if (email != null ? !email.equals(entity.email) : entity.email != null) return false;
        if (password != null ? !password.equals(entity.password) : entity.password != null) return false;
        if (phone != null ? !phone.equals(entity.phone) : entity.phone != null) return false;
        if (sex != null ? !sex.equals(entity.sex) : entity.sex != null) return false;
        if (driverLicense != null ? !driverLicense.equals(entity.driverLicense) : entity.driverLicense != null)
            return false;
        if (ignoredTimes != null ? !ignoredTimes.equals(entity.ignoredTimes) : entity.ignoredTimes != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (driverLicense != null ? driverLicense.hashCode() : 0);
        result = 31 * result + (ignoredTimes != null ? ignoredTimes.hashCode() : 0);
        return result;
    }
}
