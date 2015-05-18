
/**
 * This script
 *
 * author Sharaban Sasha
 */

$('#service').on('change', function () {
    if ($(this).val() == 'FOODSTUFF_DELIVERY') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();

    } else if ($(this).val() == 'CARGO_TAXI') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        additionalOptionsHide();
        amountOfTripTimeHide();
        $('#animalTransportationCh').slideDown("slow");

    } else if ($(this).val() == 'MEET_MY_GUEST') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestMessageShow();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();

    } else if ($(this).val() == 'SOBER_DRIVER') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();

    } else if ($(this).val() == 'SIMPLE_TAXI' || $(this).val() == 'GUEST_DELIVERY') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeHide();

    } else if ($(this).val() == 'TAXI_FOR_LONG_TERM') {
        additionalOptionsShow();
        taxiForLongTermShow();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsHide();
        amountOfTripTimeShow();

    } else if ($(this).val() == 'CELEBRATION_TAXI') {
        additionalOptionsShow();
        taxiForLongTermShow();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesHide();
        amountOfCarsShow();
        amountOfTripTimeShow();

    } else if ($(this).val() == 'CONVEY_CORPORATION_EMPLOYEES') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestMessageHide();
        conveyCorporationEmployeesShow();
        amountOfCarsHide();
        amountOfTripTimeHide();
    }

    function additionalOptionsShow() {
        $("#carCategoryGroup").slideDown("slow");
        $('#musicStyleGroup').slideDown("slow");
        $('#freeWifiCh').slideDown("slow");
        $('#nonSmokingDriverCh').slideDown("slow");
        $('#airConditionerCh').slideDown("slow");
        $('#animalTransportationCh').slideDown("slow");
    }

    function additionalOptionsHide() {
        $("#carCategoryGroup").slideUp("slow");
        $('#musicStyleGroup').slideUp("slow");
        $('#animalTransportationCh').slideUp("slow");
        $('#freeWifiCh').slideUp("slow");
        $('#nonSmokingDriverCh').slideUp("slow");
        $('#airConditionerCh').slideUp("slow");
    }

    function taxiForLongTermShow() {
        $('#endDateBlock').slideDown("slow");
        $('#importanceAddressDestination').hide();
        $('#destination').removeAttr("required");
    }

    function taxiForLongTermHide() {
        $('#endDateBlock').slideUp("slow");
        $('#importanceAddressDestination').show();
        $('#destination').attr("required");
    }

    function amountOfCarsShow() {
        $('#amountOfCarsBlock').slideDown("slow");
        $('#amountOfCars').attr("required");
    }

    function amountOfCarsHide() {
        $('#amountOfCarsBlock').slideUp("slow");
        $('#amountOfCars').removeAttr("required");
    }

    function amountOfTripTimeShow(){
        $('#amountOfTripTimeBlock').slideDown("slow");
        $('#amountOfHours').attr("required");
        $('#amountOfMinutes').attr("required");
    }
    function amountOfTripTimeHide(){
        $('#amountOfTripTimeBlock').slideUp("slow");
        $('#amountOfHours').removeAttr("required");
        $('#amountOfMinutes').removeAttr("required");
    }

    function meetMyGuestMessageShow() {
        $('#description').attr('placeholder', 'Here you can write the name of the guest and additional information');
    }

    function meetMyGuestMessageHide() {
        $('#description').attr('placeholder', '');

    }

    function conveyCorporationEmployeesHide() {
        $('#buttonAddressOrigin').slideUp("slow");
        $('#addinput').slideUp("slow");
    }

    function conveyCorporationEmployeesShow() {
        $('#addinput').slideDown("slow");
        $('#buttonAddressOrigin').slideDown("slow");
    }

});


