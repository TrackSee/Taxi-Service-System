package com.netcracker.bootcamp.tracksee.entities;

import javax.persistence.*;

/**
 * Created by Vadym_Akymov on 19.04.15.
 */
@Entity
@Table(name = "car", schema = "public", catalog = "taxi")
public class CarEntity {
    private String carNumber;
    private String carModel;
    private String color;
    private String carCategory;
    private Boolean animalTransportationApplicable;
    private Boolean freeWifi;
    private Boolean airConditioner;

    @Id
    @Column(name = "car_number")
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Basic
    @Column(name = "car_model")
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
    @Column(name = "animal_transportation_applicable")
    public Boolean getAnimalTransportationApplicable() {
        return animalTransportationApplicable;
    }

    public void setAnimalTransportationApplicable(Boolean animalTransportationApplicable) {
        this.animalTransportationApplicable = animalTransportationApplicable;
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
    @Column(name = "air_conditioner")
    public Boolean getAirConditioner() {
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

        if (airConditioner != null ? !airConditioner.equals(carEntity.airConditioner) : carEntity.airConditioner != null)
            return false;
        if (animalTransportationApplicable != null ? !animalTransportationApplicable.equals(carEntity.animalTransportationApplicable) : carEntity.animalTransportationApplicable != null)
            return false;
        if (carCategory != null ? !carCategory.equals(carEntity.carCategory) : carEntity.carCategory != null)
            return false;
        if (carModel != null ? !carModel.equals(carEntity.carModel) : carEntity.carModel != null) return false;
        if (carNumber != null ? !carNumber.equals(carEntity.carNumber) : carEntity.carNumber != null) return false;
        if (color != null ? !color.equals(carEntity.color) : carEntity.color != null) return false;
        if (freeWifi != null ? !freeWifi.equals(carEntity.freeWifi) : carEntity.freeWifi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carNumber != null ? carNumber.hashCode() : 0;
        result = 31 * result + (carModel != null ? carModel.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (carCategory != null ? carCategory.hashCode() : 0);
        result = 31 * result + (animalTransportationApplicable != null ? animalTransportationApplicable.hashCode() : 0);
        result = 31 * result + (freeWifi != null ? freeWifi.hashCode() : 0);
        result = 31 * result + (airConditioner != null ? airConditioner.hashCode() : 0);
        return result;
    }
}
