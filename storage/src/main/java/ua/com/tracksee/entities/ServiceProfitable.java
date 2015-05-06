package ua.com.tracksee.entities;

/**
 * Created by kstes_000 on 05-May-15.
 */
public class ServiceProfitable {
    private String service;
    private Double price;

    public ServiceProfitable(String s, Double p){
        this.service = s;
        this.price = p;
    }
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
