package com.netcracker.bootcamp.tracksee.entities;

import org.postgresql.geometric.PGpath;

import javax.persistence.*;

/**
 * Created by Vadym_Akymov on 19.04.15.
 */
@Entity
@Table(name = "taxi_order", schema = "public", catalog = "taxi")
public class TaxiOrderEntity {
    private int trackingNumber;
    private String status;
    private PGpath path;
    private String carCategory;
    private String wayOfPayment;
    private String driverSex;
    private String musicStyle;
    private Boolean animalTransportation;
    private Boolean freeWifi;
    private Boolean smokingDriver;
    private Boolean airConditioner;
    private String comment;
    private ServiceUserEntity serviceUserByDriver;
    private ServiceUserEntity serviceUserByUserId;

    @Id
    @Column(name = "tracking_number")
    public int getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(int trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "path")
    public PGpath getPath() {
        return path;
    }

    public void setPath(PGpath path) {
        this.path = path;
    }

    @Basic
    @Column(name = "car_category")
    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    @Basic
    @Column(name = "way_of_payment")
    public String getWayOfPayment() {
        return wayOfPayment;
    }

    public void setWayOfPayment(String wayOfPayment) {
        this.wayOfPayment = wayOfPayment;
    }

    @Basic
    @Column(name = "driver_sex")
    public String getDriverSex() {
        return driverSex;
    }

    public void setDriverSex(String driverSex) {
        this.driverSex = driverSex;
    }

    @Basic
    @Column(name = "music_style")
    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    @Basic
    @Column(name = "animal_transportation")
    public Boolean getAnimalTransportation() {
        return animalTransportation;
    }

    public void setAnimalTransportation(Boolean animalTransportation) {
        this.animalTransportation = animalTransportation;
    }

    @Basic
    @Column(name = "free_wifi")
    public Boolean getFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(Boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    @Basic
    @Column(name = "smoking_driver")
    public Boolean getSmokingDriver() {
        return smokingDriver;
    }

    public void setSmokingDriver(Boolean smokingDriver) {
        this.smokingDriver = smokingDriver;
    }

    @Basic
    @Column(name = "air_conditioner")
    public Boolean getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiOrderEntity that = (TaxiOrderEntity) o;

        if (trackingNumber != that.trackingNumber) return false;
        if (airConditioner != null ? !airConditioner.equals(that.airConditioner) : that.airConditioner != null)
            return false;
        if (animalTransportation != null ? !animalTransportation.equals(that.animalTransportation) : that.animalTransportation != null)
            return false;
        if (carCategory != null ? !carCategory.equals(that.carCategory) : that.carCategory != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (driverSex != null ? !driverSex.equals(that.driverSex) : that.driverSex != null) return false;
        if (freeWifi != null ? !freeWifi.equals(that.freeWifi) : that.freeWifi != null) return false;
        if (musicStyle != null ? !musicStyle.equals(that.musicStyle) : that.musicStyle != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (smokingDriver != null ? !smokingDriver.equals(that.smokingDriver) : that.smokingDriver != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (wayOfPayment != null ? !wayOfPayment.equals(that.wayOfPayment) : that.wayOfPayment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trackingNumber;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (carCategory != null ? carCategory.hashCode() : 0);
        result = 31 * result + (wayOfPayment != null ? wayOfPayment.hashCode() : 0);
        result = 31 * result + (driverSex != null ? driverSex.hashCode() : 0);
        result = 31 * result + (musicStyle != null ? musicStyle.hashCode() : 0);
        result = 31 * result + (animalTransportation != null ? animalTransportation.hashCode() : 0);
        result = 31 * result + (freeWifi != null ? freeWifi.hashCode() : 0);
        result = 31 * result + (smokingDriver != null ? smokingDriver.hashCode() : 0);
        result = 31 * result + (airConditioner != null ? airConditioner.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "user_id")
    public ServiceUserEntity getServiceUserByDriver() {
        return serviceUserByDriver;
    }

    public void setServiceUserByDriver(ServiceUserEntity serviceUserByDriver) {
        this.serviceUserByDriver = serviceUserByDriver;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public ServiceUserEntity getServiceUserByUserId() {
        return serviceUserByUserId;
    }

    public void setServiceUserByUserId(ServiceUserEntity serviceUserByUserId) {
        this.serviceUserByUserId = serviceUserByUserId;
    }
}
