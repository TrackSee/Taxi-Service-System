
/**
 * Provides changing of order
 * input form structure, options
 * and requirements depending on the
 * selected service.
 *
 * @author Sharaban Sasha
 */
$(document).ready(function(){
var service=$('#service');
    if (service.val() == 'FOODSTUFF_DELIVERY') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();

    } else if (service.val() == 'CARGO_TAXI') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();
        $('#animalTransportationCh').slideDown("slow");

    } else if (service.val() == 'MEET_MY_GUEST') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestMessageShow();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();

    } else if (service.val() == 'SOBER_DRIVER') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();

    } else if (service.val() == 'SIMPLE_TAXI' || $(this).val() == 'GUEST_DELIVERY') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();

    } else if (service.val() == 'TAXI_FOR_LONG_TERM') {
        additionalOptionsShow();
        taxiForLongTermShow();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeShow();

    } else if (service.val() == 'CELEBRATION_TAXI') {
        additionalOptionsShow();
        taxiForLongTermShow();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsShow();
        amountOfTripTimeShow();

    } else if (service.val() == 'CONVEY_CORPORATION_EMPLOYEES') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesShow();
        amountOfCarsHide();
        amountOfTripTimeHide();
    }

    /**
     * Provides showing of additional options if
     * they are hidden. It's need for all services
     * except those: "Food stuff delivery",
     * "Cargo taxi" and "Sober driver".
     *
     * @author Sharaban Sasha
     */
    function additionalOptionsShow() {
        $("#carCategoryGroup").slideDown("slow");
        $('#musicStyleGroup').slideDown("slow");
        $('#freeWifiCh').slideDown("slow");
        $('#nonSmokingDriverCh').slideDown("slow");
        $('#airConditionerCh').slideDown("slow");
        $('#animalTransportationCh').slideDown("slow");
    }
    /**
     * Provides hiding additional options if was
     * chosen such services: "Food stuff delivery",
     * "Cargo taxi" and "Sober driver".
     *
     * @author Sharaban Sasha
     */
    function additionalOptionsHide() {
        $("#carCategoryGroup").slideUp("slow");
        $('#musicStyleGroup').slideUp("slow");
        $('#animalTransportationCh').slideUp("slow");
        $('#freeWifiCh').slideUp("slow");
        $('#nonSmokingDriverCh').slideUp("slow");
        $('#airConditionerCh').slideUp("slow");
    }
    /**
     * Makes address destination not required,
     * it's need for such services: "Taxi for
     * long term" and "Celebration taxi".
     *
     * @author Sharaban Sasha
     */
    function taxiForLongTermShow() {
        $('#importanceAddressDestination').hide();
        $('#destination').removeAttr("required");
    }
    /**
     * Makes address destination required
     * if it's not. It's need for all
     * services except those: "Taxi for
     * long term" and "Celebration taxi".
     *
     * @author Sharaban Sasha
     */
    function taxiForLongTermHide() {
        $('#importanceAddressDestination').show();
        $('#destination').attr("required");
    }
    /**
     * Shows required filed amount of cars
     * that need for service "Celebration taxi".
     *
     * @author Sharaban Sasha
     */
    function amountOfCarsShow() {
        $('#amountOfCarsBlock').slideDown("slow");
        $('#amountOfCars').attr("required");
    }
    /**
     * Hides filed amount of cars that
     * need for all services except service
     * "Celebration taxi".
     *
     * @author Sharaban Sasha
     */
    function amountOfCarsHide() {
        $('#amountOfCarsBlock').slideUp("slow");
        $('#amountOfCars').removeAttr("required");
    }
    /**
     * Shows required fields amount of hours
     * and amount of minutes that need for
     * such services: "Celebration taxi" and
     * "Taxi for long term".
     *
     * @author Sharaban Sasha
     */
    function amountOfTripTimeShow(){
        $('#amountOfTripTimeBlock').slideDown("slow");
        $('#amountOfHours').attr("required");
        $('#amountOfMinutes').attr("required");
    }
    /**
     * Hides fields amount of hours
     * and amount of minutes that need for all
     * services except those: "Celebration taxi" and
     * "Taxi for long term".
     *
     * @author Sharaban Sasha
     */
    function amountOfTripTimeHide(){
        $('#amountOfTripTimeBlock').slideUp("slow");
        $('#amountOfHours').removeAttr("required");
        $('#amountOfMinutes').removeAttr("required");
    }
    /**
     * Shows message for user about ability for
     * him to enter name of the guest in description
     * field. It's need for service "Meet my guest".
     *
     * @author Sharaban Sasha
     */
    function meetMyGuestMessageShow() {
        $('#description').attr('placeholder', 'Here you can write the name of the guest and additional information');
    }
    /**
     * Hides message for user about ability for
     * him to enter name of the guest in description
     * field. It's need for all services except service
     * "Meet my guest".
     *
     * @author Sharaban Sasha
     */
    function meetMyGuestMessageHide() {
        $('#description').attr('placeholder', '');

    }
    /**
     * Shows button that allow creating more "Address from"
     * fields  It's need for service "Convey corporation
     * employees".
     *
     * @author Sharaban Sasha
     */
    function conveyCorporationEmployeesShow() {
        $('#addExtraAddresses').slideDown("slow");
    }
    /**
     * Hides button that allow creating more "Address from"
     * fields  It's need for all services except service
     * "Convey corporation employees".
     *
     * @author Sharaban Sasha
     */
    function conveyCorporationEmployeesHide() {
        $('#addExtraAddresses').slideUp("slow");
    }
});



