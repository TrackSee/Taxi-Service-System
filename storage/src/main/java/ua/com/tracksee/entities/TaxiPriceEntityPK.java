package ua.com.tracksee.entities;

import ua.com.tracksee.enumartion.CarCategory;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana
 */
public class TaxiPriceEntityPK implements Serializable {
    private CarCategory carCategory;
    private Boolean weekend;
    private Boolean nightTariff;

    @Column(name = "car_category")
    @Enumerated(EnumType.STRING)
    @Id
    public CarCategory getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategory carCategory) {
        this.carCategory = carCategory;
    }

    @Column(name = "weekend")
    @Id
    public Boolean getWeekend() {
        return weekend;
    }

    public void setWeekend(Boolean weekend) {
        this.weekend = weekend;
    }

    @Column(name = "night_tariff")
    @Id
    public Boolean getNightTariff() {
        return nightTariff;
    }

    public void setNightTariff(Boolean nightTariff) {
        this.nightTariff = nightTariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiPriceEntityPK that = (TaxiPriceEntityPK) o;
        return Objects.equals(carCategory, that.carCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carCategory);
    }
}
