package ua.com.tracksee.entity;

import java.sql.Timestamp;

/**
 * @author Ruslan Gunavardana.
 */
public class TaxiOrder {
    private Address origin;
    private Address destination;
    private Timestamp creationTime;
    private Timestamp orderTime;

    public Address getOrigin() {
        return origin;
    }

	public void setOrigin(Address origin) {
        this.origin = origin;
    }

    public Address getDestination() {
        return destination;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }
    public void incruasePrice(){

    }
}
