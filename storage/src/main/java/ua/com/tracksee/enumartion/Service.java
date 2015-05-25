package ua.com.tracksee.enumartion;

/**
 * @author Ruslan Gunavardana
 */
public enum Service {

    /** Just simple taxi. */
    SIMPLE_TAXI("Simple taxi"),

    /** If this service is ordered by customer, driver uses client's car to handle order. */
    SOBER_DRIVER("Sober driver"),

    /** Drivers should take people at several places and deliver them to the corporation. */
    CONVEY_CORPORATION_EMPLOYEES("Convey corporation emplyees"),

    /** Customer user can order taxi for guests. */
    GUEST_DELIVERY("Guest delivery"),

    /** This service can be ordered in case user needs a truck for his removal */
    CARGO_TAXI("Cargo taxi"),

    /** This service provides an ability for users to a taxi for long term */
    TAXI_FOR_LONG_TERM("Taxi for long term"),

    /**
     * If this service is ordered by customer,
     * driver will wait for user’s guest in the airport,
     * railway/bus station with guest’s name placing on the table
     */
    MEET_MY_GUEST("Meet my guest"),

    /** 5+ cars for a 8+ hours (in this case user may not specify his destination address) */
    CELEBRATION_TAXI("Celebration taxi"),

    /** This service allows to order some food */
    FOODSTUFF_DELIVERY("Foodstuff delivery");

    private String name;

    Service(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
