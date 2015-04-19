package com.netcracker.bootcamp.tracksee.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Vadym_Akymov on 19.04.15.
 */
public class TaxiPriceEntityPK implements Serializable {
    private String carCategory;
    private boolean weekend;
    private boolean nightTariff;

    @Column(name = "car_category")
    @Id
    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    @Column(name = "is_weekend")
    @Id
    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    @Column(name = "is_night_tariff")
    @Id
    public boolean isNightTariff() {
        return nightTariff;
    }

    public void setNightTariff(boolean nightTariff) {
        this.nightTariff = nightTariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiPriceEntityPK that = (TaxiPriceEntityPK) o;

        if (nightTariff != that.nightTariff) return false;
        if (weekend != that.weekend) return false;
        if (carCategory != null ? !carCategory.equals(that.carCategory) : that.carCategory != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carCategory != null ? carCategory.hashCode() : 0;
        result = 31 * result + (weekend ? 1 : 0);
        result = 31 * result + (nightTariff ? 1 : 0);
        return result;
    }
}
