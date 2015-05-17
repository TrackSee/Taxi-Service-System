
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
        amountOfTripTimeHide();

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
        $('#buttonAddressOrigin').show();
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
    }

    function conveyCorporationEmployeesShow() {
        $('#buttonAddressOrigin').slideDown("slow");
        //TODO autocreating fields
        //var counter = 1;
        //var limit = 100;
        ////function addInput(divName){
        //        var newdiv = document.createElement('div');
        //        newdiv.innerHTML = "Entry " + (counter + 1) +
        //        "<div class=\"form-group\"> <label>Address from</label>"+
        //        +"<input type=\"text\" id=\"origin"+counter+"\" class=\"form-control\" value=\"\""+
        //        +"name=\"addressOrigin"+counter+"\" title=\"That address is invalid\""+
        //        +"required onblur=\"updateRoute()\">"+
        //       " <span class=\"red-star\">★</span></div>";
        //        document.getElementById(divName).appendChild(newdiv);
        //        counter++;
        //    if (counter == limit)  {
        //        $('#addNextAddressFrom').slideUp();
        //    }
        //}
    }

});

//$('#buttonAddressOrigin').click(function (){
//    alert("button");
//    var counter = 1;
//    if(counter==1){
//        $('#addressOrigin1').show();
//    }else if (counter==2){
//        $('#addressOrigin2').show();
//    }else if (counter==3){
//        $('#addressOrigin3').show();
//    }else if (counter==4){
//        $('#addressOrigin4').show();
//    }else if (counter==5){
//        $('#addressOrigin5').show();
//    }
//});