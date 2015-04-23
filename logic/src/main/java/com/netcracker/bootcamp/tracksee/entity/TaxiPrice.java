package com.netcracker.bootcamp.tracksee.entity;

/**
 * @author Sharaban Sasha
 */
public class TaxiPrice {
    private Double pricePerKm;
    private Double pricePerMin;
    private String carCategory;
    private Boolean weekend;
    private Boolean nightTariff;

    public Double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(Double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public Double getPricePerMin() {
        return pricePerMin;
    }

    public void setPricePerMin(Double pricePerMin) {
        this.pricePerMin = pricePerMin;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public Boolean getWeekend() {
        return weekend;
    }

    public void setWeekend(Boolean weekend) {
        this.weekend = weekend;
    }

    public Boolean getNightTariff() {
        return nightTariff;
    }

    public void setNightTariff(Boolean nightTariff) {
        this.nightTariff = nightTariff;
    }
}
