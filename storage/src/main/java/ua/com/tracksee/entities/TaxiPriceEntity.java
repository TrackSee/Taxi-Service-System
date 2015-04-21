package ua.com.tracksee.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
@Entity
@Table(name = "taxi_price", schema = "public", catalog = "tracksee")
@IdClass(TaxiPriceEntityPK.class)
public class TaxiPriceEntity {
    private Double pricePerKm;
    private Double pricePerMin;
    private String carCategory;
    private Boolean weekend;
    private Boolean nightTariff;

    @Basic
    @Column(name = "price_per_km")
    public Double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(Double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    @Basic
    @Column(name = "price_per_min")
    public Double getPricePerMin() {
        return pricePerMin;
    }

    public void setPricePerMin(Double pricePerMin) {
        this.pricePerMin = pricePerMin;
    }

    @Id
    @Column(name = "car_category")
    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public Boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(Boolean weekend) {
        this.weekend = weekend;
    }

    public Boolean isNightTariff() {
        return nightTariff;
    }

    public void setNightTariff(Boolean nightTariff) {
        this.nightTariff = nightTariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiPriceEntity that = (TaxiPriceEntity) o;
        return Objects.equals(pricePerKm, that.pricePerKm) &&
                Objects.equals(pricePerMin, that.pricePerMin) &&
                Objects.equals(carCategory, that.carCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricePerKm, pricePerMin, carCategory);
    }
}
