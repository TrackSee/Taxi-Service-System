package ua.com.tracksee.entities;

/**
 * Created on 04.05.2015
 *
 * @author Oleksandr Kozin
 */
public class ProfitEntity {
    private String service;
    private Double price;

    public ProfitEntity() {
    }

    public ProfitEntity(String service, Double price) {
        this.service = service;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfitEntity)) return false;

        ProfitEntity that = (ProfitEntity) o;

        if (getService() != that.getService()) return false;
        return getPrice().equals(that.getPrice());

    }

    @Override
    public int hashCode() {
        int result = getService().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
