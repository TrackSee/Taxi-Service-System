package ua.com.tracksee.enumartion;

/**
 * @author Ruslan Gunavardana
 */
public enum OrderStatus {
    QUEUED("Queued"),
    ASSIGNED("Assigned"),
    UPDATED("Updated"),
    REFUSED("Refused"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
