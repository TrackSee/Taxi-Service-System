package ua.com.tracksee.entities;

import org.postgresql.geometric.PGpath;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "taxi_order_item", schema = "public", catalog = "tracksee")
public class TaxiOrderItemEntity {
    private Integer taxiItemId;
    private PGpath path;
    private ServiceUserEntity serviceUserByDriverId;
    private TaxiOrderEntity taxiOrderByTrackingNumer;

    @Id
    @GeneratedValue
    @Column(name = "taxi_item_id")
    public Integer getTaxiItemId() {
        return taxiItemId;
    }

    public void setTaxiItemId(Integer taxiItemId) {
        this.taxiItemId = taxiItemId;
    }

    @Basic
    @Column(name = "path")
    public PGpath getPath() {
        return path;
    }

    public void setPath(PGpath path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiOrderItemEntity that = (TaxiOrderItemEntity) o;
        return Objects.equals(taxiItemId, that.taxiItemId) &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxiItemId, path);
    }

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "user_id")
    public ServiceUserEntity getServiceUserByDriverId() {
        return serviceUserByDriverId;
    }

    public void setServiceUserByDriverId(ServiceUserEntity serviceUserByDriverId) {
        this.serviceUserByDriverId = serviceUserByDriverId;
    }

    @ManyToOne
    @JoinColumn(name = "tracking_numer", referencedColumnName = "tracking_number")
    public TaxiOrderEntity getTaxiOrderByTrackingNumer() {
        return taxiOrderByTrackingNumer;
    }

    public void setTaxiOrderByTrackingNumer(TaxiOrderEntity taxiOrderByTrackingNumer) {
        this.taxiOrderByTrackingNumer = taxiOrderByTrackingNumer;
    }
}
