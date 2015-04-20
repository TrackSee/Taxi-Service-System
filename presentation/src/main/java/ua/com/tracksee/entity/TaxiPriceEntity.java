package ua.com.tracksee.entity;

import javax.persistence.*;

/**
 * Created by byte on 4/19/15.
 */
@Entity
@Table(name = "TaxiPrice", schema = "public", catalog = "taxi_service")
public class TaxiPriceEntity {
    private Double priceForKm;
    private Double priceForMin;
    private String carCategory;
    private Boolean isWeekend;
    private Boolean isNightTariff;

    @Basic
    @Column(name = "priceForKm")
    public Double getPriceForKm() {
        return priceForKm;
    }

    public void setPriceForKm(Double priceForKm) {
        this.priceForKm = priceForKm;
    }

    @Basic
    @Column(name = "priceForMin")
    public Double getPriceForMin() {
        return priceForMin;
    }

    public void setPriceForMin(Double priceForMin) {
        this.priceForMin = priceForMin;
    }

    @Basic
    @Column(name = "carCategory")
    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    @Basic
    @Column(name = "isWeekend")
    public Boolean getIsWeekend() {
        return isWeekend;
    }

    public void setIsWeekend(Boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    @Basic
    @Column(name = "isNightTariff")
    public Boolean getIsNightTariff() {
        return isNightTariff;
    }

    public void setIsNightTariff(Boolean isNightTariff) {
        this.isNightTariff = isNightTariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiPriceEntity that = (TaxiPriceEntity) o;

        if (priceForKm != null ? !priceForKm.equals(that.priceForKm) : that.priceForKm != null) return false;
        if (priceForMin != null ? !priceForMin.equals(that.priceForMin) : that.priceForMin != null) return false;
        if (carCategory != null ? !carCategory.equals(that.carCategory) : that.carCategory != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = priceForKm != null ? priceForKm.hashCode() : 0;
        result = 31 * result + (priceForMin != null ? priceForMin.hashCode() : 0);
        result = 31 * result + (carCategory != null ? carCategory.hashCode() : 0);
        return result;
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
