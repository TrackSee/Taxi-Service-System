package ua.com.tracksee.servlets.orders;

/**
 * This servlet contains attribute names
 * that used on order servlets.
 *
 * @author Sharaban Sasha
 */
public interface OrderAttributeNames {

    String PRICE_LIST_ALIAS = "priceList";
    String MINIMAL_ORDER_DISTANCE_ALIAS = "minimalOrderDistance";
    String ORDER_ALIAS= "order";
    String TRACKING_NUMBER_ALIAS ="trackingNumber";
    String USER_ID_ALIAS ="userId";
    String AMOUNT_OF_CARS_ALIAS ="amountOfCars";
    String AMOUNT_OF_HOURS_ALIAS ="amountOfHours";
    String AMOUNT_OF_MINUTES_ALIAS ="amountOfMinutes";
    String PHONE_NUMBER_ALIAS ="phoneNumber";
    String EMAIL_ALIAS ="email";
    String PRICE_ALIAS ="price";
    String ARRIVE_DATE_ALIAS ="arriveDate";
    String END_DATE_ALIAS ="endDate";
    String ADDRESS_DESTINATION_ALIAS ="addressDestination";
    String ADDRESS_ORIGIN_ALIAS ="addressOrigin";
    String ORDER_STATUS_ALIAS ="orderStatus";
    String SERVICE_ALIAS="service";
    String WAY_OF_PAYMENT_ALIAS ="wayOfPayment";
    String CAR_CATEGORY_ALIAS ="carCategory";
    String MUSIC_STYLE_ALIAS ="musicStyle";
    String DRIVER_SEX_ALIAS ="driverSex";
    String DESCRIPTION_ALIAS="description";
    String COMMENTS_ALIAS ="comments";
    String COMMENTS_STATE_ALIAS ="commentsState";
    String BUTTON_COMMENTS_HIDE_ALIAS ="buttonCommentsHide";
    String ANIMAL_TRANSPORTATION_ALIAS ="animalTransportation";
    String FREE_WIFI_ALIAS ="freeWifi";
    String NON_SMOKING_DRIVER_ALIAS ="nonSmokingDriver";
    String AIR_CONDITIONER_ALIAS ="airConditioner";
    String ANIMAL_TRANSPORTATION_MULTIPLIER = "animalTransportationMultiplier";
    String EMAIL_FOR_NON_ACTIVE_USER_ALIAS="emailForNonActiveUser";
    String EMAIL_FOR_NON_ACTIVE_USER= "<label>Email</label>"+
            "<input type=\"email\"  "+
            "class=\"form-control\" name=\"email\" placeholder=\"Enter email\""+
            "title=\"For example: alex@gmail.com\" value=\"\" required>";

}
