package ua.com.tracksee.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */

@Entity
@Table(name = "service_user", schema = "public", catalog = "tracksee")
@NamedQueries({
        @NamedQuery(name = "getAll", query = "SELECT user from ServiceUserEntity  user"),
        @NamedQuery(name = "getAllById", query = "SELECT user from ServiceUserEntity user WHERE userId in:usersId"),
        @NamedQuery(name = "blockAll", query = "UPDATE ServiceUserEntity user SET user.ignoredTimes=:ignoredTimes WHERE user.userId in :userIds"),
        @NamedQuery(name = "usersSize", query = "SELECT count (user) from ServiceUserEntity user")
})
public class ServiceUserEntity {
    private Integer userId;
    private String email;
    private String password;
    private String salt;
    private String phone;
    private Boolean driver = false;
    private Boolean admin = false;
    private String sex;
    private String groupName;
    private String driverLicense;
    private Integer ignoredTimes = 0;
    private Boolean activated = false;
    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());
    private CarEntity car;

    @Id
    @GeneratedValue(generator = "userSeq")
    @SequenceGenerator(name = "userSeq", sequenceName = "service_user_user_id_seq",
                       allocationSize = 1, initialValue= 1)
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email", nullable = false, unique = true, length = 254)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 28)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt", nullable = false, length = 8)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "phone", length = 28)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "sex", nullable = false)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "driver", nullable = false)
    public Boolean getDriver() {
        return driver;
    }

    public void setDriver(Boolean driver) {
        this.driver = driver;
    }

    @Basic
    @Column(name = "admin", nullable = false)
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
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
    @Column(name = "ignored_times", nullable = false)
    public Integer getIgnoredTimes() {
        return ignoredTimes;
    }

    public void setIgnoredTimes(Integer ignoredTimes) {
        this.ignoredTimes = ignoredTimes;
    }

    @Basic
    @Column(name = "activated", nullable = false)
    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Basic
    @Column(name = "registration_date", nullable = false)
    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceUserEntity entity = (ServiceUserEntity) o;
        return Objects.equals(userId, entity.userId) &&
                Objects.equals(email, entity.email) &&
                Objects.equals(password, entity.password) &&
                Objects.equals(phone, entity.phone) &&
                Objects.equals(sex, entity.sex) &&
                Objects.equals(groupName, entity.groupName) &&
                Objects.equals(driverLicense, entity.driverLicense) &&
                Objects.equals(ignoredTimes, entity.ignoredTimes) &&
                Objects.equals(registrationDate, entity.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, phone, sex, groupName, driverLicense, ignoredTimes, registrationDate);
    }

    @ManyToOne
    @JoinColumn(name = "car_number", referencedColumnName = "car_number")
    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity carByCarNumber) {
        this.car = carByCarNumber;
    }
}
