package entity;

import javax.persistence.*;

/**
 * Created by byte on 4/19/15.
 */
@Entity
@Table(name = "TaxiOrder", schema = "public", catalog = "taxi_service")
public class TaxiOrderEntity {
    private int trackingnumber;
    private String status;
    private int origin;
    private Integer destination;
    private String carcategory;
    private String wayofpayment;
    private Boolean smokingdriver;
    private Boolean freewifi;
    private Boolean animaltransportation;
    private Boolean airconditioner;
    private String musicstyle;
    private String comment;

    @Id
    @Column(name = "trackingnumber")
    public int getTrackingnumber() {
        return trackingnumber;
    }

    public void setTrackingnumber(int trackingnumber) {
        this.trackingnumber = trackingnumber;
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
    @Column(name = "origin")
    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    @Basic
    @Column(name = "destination")
    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    @Basic
    @Column(name = "carcategory")
    public String getCarcategory() {
        return carcategory;
    }

    public void setCarcategory(String carcategory) {
        this.carcategory = carcategory;
    }

    @Basic
    @Column(name = "wayofpayment")
    public String getWayofpayment() {
        return wayofpayment;
    }

    public void setWayofpayment(String wayofpayment) {
        this.wayofpayment = wayofpayment;
    }

    public Boolean isSmokingdriver() {
        return smokingdriver;
    }

    public void setSmokingdriver(Boolean smokingdriver) {
        this.smokingdriver = smokingdriver;
    }

    public Boolean isFreewifi() {
        return freewifi;
    }

    public void setFreewifi(Boolean freewifi) {
        this.freewifi = freewifi;
    }

    public Boolean isAnimaltransportation() {
        return animaltransportation;
    }

    public void setAnimaltransportation(Boolean animaltransportation) {
        this.animaltransportation = animaltransportation;
    }

    public Boolean isAirconditioner() {
        return airconditioner;
    }

    public void setAirconditioner(Boolean airconditioner) {
        this.airconditioner = airconditioner;
    }

    @Basic
    @Column(name = "musicstyle")
    public String getMusicstyle() {
        return musicstyle;
    }

    public void setMusicstyle(String musicstyle) {
        this.musicstyle = musicstyle;
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

        if (trackingnumber != that.trackingnumber) return false;
        if (origin != that.origin) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (carcategory != null ? !carcategory.equals(that.carcategory) : that.carcategory != null) return false;
        if (wayofpayment != null ? !wayofpayment.equals(that.wayofpayment) : that.wayofpayment != null) return false;
        if (musicstyle != null ? !musicstyle.equals(that.musicstyle) : that.musicstyle != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trackingnumber;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + origin;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (carcategory != null ? carcategory.hashCode() : 0);
        result = 31 * result + (wayofpayment != null ? wayofpayment.hashCode() : 0);
        result = 31 * result + (musicstyle != null ? musicstyle.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
