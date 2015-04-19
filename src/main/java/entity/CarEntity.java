package entity;

import javax.persistence.*;

/**
 * Created by byte on 4/19/15.
 */
@Entity
@Table(name = "Car", schema = "public", catalog = "taxi_service")
public class CarEntity {
    private String carNumber;
    private String carCategory;
    private Boolean animalTransportationApplicable;
    private Boolean freeWifi;
    private Boolean airConditioner;

    @Id
    @Column(name = "carNumber")
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Basic
    @Column(name = "carCategory")
    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public Boolean isAnimalTransportationApplicable() {
        return animalTransportationApplicable;
    }

    public void setAnimalTransportationApplicable(Boolean animalTransportationApplicable) {
        this.animalTransportationApplicable = animalTransportationApplicable;
    }

    public Boolean isFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(Boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    public Boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (carNumber != null ? !carNumber.equals(carEntity.carNumber) : carEntity.carNumber != null) return false;
        if (carCategory != null ? !carCategory.equals(carEntity.carCategory) : carEntity.carCategory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carNumber != null ? carNumber.hashCode() : 0;
        result = 31 * result + (carCategory != null ? carCategory.hashCode() : 0);
        return result;
    }
}
