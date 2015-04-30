package ua.com.tracksee.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Maria Komar on 30.04.2015.
 */
public class TaxiOrderItemEntityPK implements Serializable {
    private Integer trackingNumber;

    @Column(name = "tracking_number")
    @Id
    public Integer getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Integer trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiOrderItemEntityPK that = (TaxiOrderItemEntityPK) o;

        if (trackingNumber != null ? !trackingNumber.equals(that.trackingNumber) : that.trackingNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return trackingNumber != null ? trackingNumber.hashCode() : 0;
    }

}
