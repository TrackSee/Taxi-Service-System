package com.netcracker.bootcamp.tracksee.entities;

import javax.persistence.*;

/**
 * Created by Vadym_Akymov on 19.04.15.
 */
@Entity
@Table(name = "taxi_price", schema = "public", catalog = "taxi")
@IdClass(TaxiPriceEntityPK.class)
public class TaxiPriceEntity {
    private double pricePerKm;
    private double pricePerMin;
    private String carCategory;
    private boolean isWeekend;
    private boolean isNightTariff;

    @Basic
    @Column(name = "price_per_km")
    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    @Basic
    @Column(name = "price_per_min")
    public double getPricePerMin() {
        return pricePerMin;
    }

    public void setPricePerMin(double pricePerMin) {
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

    @Id
    @Column(name = "is_weekend")
    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    @Id
    @Column(name = "is_night_tariff")
    public boolean isNightTariff() {
        return isNightTariff;
    }

    public void setNightTariff(boolean isNightTariff) {
        this.isNightTariff = isNightTariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiPriceEntity that = (TaxiPriceEntity) o;

        if (isNightTariff != that.isNightTariff) return false;
        if (isWeekend != that.isWeekend) return false;
        if (Double.compare(that.pricePerKm, pricePerKm) != 0) return false;
        if (Double.compare(that.pricePerMin, pricePerMin) != 0) return false;
        if (carCategory != null ? !carCategory.equals(that.carCategory) : that.carCategory != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(pricePerKm);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pricePerMin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (carCategory != null ? carCategory.hashCode() : 0);
        result = 31 * result + (isWeekend ? 1 : 0);
        result = 31 * result + (isNightTariff ? 1 : 0);
        return result;
    }
}
