package ua.com.tracksee.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ruslan Gunavardana.
 */
@Entity
@Table(name = "car", schema = "public", catalog = "tracksee")
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
    public Boolean getAnimalTransportationApplicable(){
        return animalTransportationApplicable;
    }

    public void setAnimalTransportationApplicable(Boolean animalTransportationApplicable) {
        this.animalTransportationApplicable = animalTransportationApplicable;
    }

    @Basic
    @Column(name = "free_wifi")
    public Boolean getFreeWifi(){
        return freeWifi;
    }

    public void setFreeWifi(Boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    @Basic
    @Column(name = "air_conditioner")
    public Boolean getAirConditioner(){
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
        return Objects.equals(carNumber, carEntity.carNumber) &&
                Objects.equals(carModel, carEntity.carModel) &&
                Objects.equals(color, carEntity.color) &&
                Objects.equals(carCategory, carEntity.carCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber, carModel, color, carCategory);
    }
}
