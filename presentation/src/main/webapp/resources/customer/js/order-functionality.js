/**
 * author Sharaban Sasha
 */

/**
 * This script
 *
 * author Sharaban Sasha
 */

$('#service').on('change', function () {
    if ($(this).val() == 'FOODSTUFF_DELIVERY') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestHide();

    } else
    if ($(this).val() == 'CARGO_TAXI') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestHide();
        $('#animalTransportationCh').slideDown("slow");
    } else
    if ($(this).val() == 'MEET_MY_GUEST') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestShow();

    } else
    if ($(this).val() == 'SOBER_DRIVER') {
       additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestHide();

    }else
    if ($(this).val() == 'SIMPLE_TAXI'||$(this).val() == 'GUEST_DELIVERY'){
        additionalOptionsShow();
        taxiForLongTermHide();
    }else
    if ($(this).val() == 'CELEBRATION_TAXI'||$(this).val() == 'TAXI_FOR_LONG_TERM'){
        additionalOptionsShow();
        taxiForLongTermShow();
        meetMyGuestHide();
    }else
    if ($(this).val() == 'CONVEY_CORPORATION_EMPLOYEES'){
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestHide();
        alert("Convey");
        conveyCorporationEmployees();
    }
});
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
function meetMyGuestHide() {
    $('#description').remove('placeholder');
}
function meetMyGuestShow() {
    //TODO complete this method
    $('#description').attr('placeholder', 'Here you can write the name of the guest and additional information');
 //  $('#description').add("placeholder=\"Here you can write the name of the guest and additional information\"");
}
function conveyCorporationEmployees(){
    var counter = 1;
    var limit = 100;
    alert("fdg");
    //function addInput(divName){
            var newdiv = document.createElement('div');
            newdiv.innerHTML = "Entry " + (counter + 1) +
            "<div class=\"form-group\"> <label>Address from</label>"+
            +"<input type=\"text\" id=\"origin"+counter+"\" class=\"form-control\" value=\"\""+
            +"name=\"addressOrigin"+counter+"\" title=\"That address is invalid\""+
            +"required onblur=\"updateRoute()\">"+
           " <span class=\"red-star\">★</span></div>";
            document.getElementById(divName).appendChild(newdiv);
            counter++;
        if (counter == limit)  {
            $('#addNextAddressFrom').slideUp();
        }
        //}
    }


