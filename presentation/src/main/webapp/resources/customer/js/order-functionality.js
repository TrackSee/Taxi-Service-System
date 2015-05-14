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
        conveyCorporationEmployeesHide()

    } else if ($(this).val() == 'CARGO_TAXI') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestHide();
        conveyCorporationEmployeesHide();
        $('#animalTransportationCh').slideDown("slow");
    } else if ($(this).val() == 'MEET_MY_GUEST') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestShow();
        conveyCorporationEmployeesHide();

    } else if ($(this).val() == 'SOBER_DRIVER') {
        additionalOptionsHide();
        taxiForLongTermHide();
        meetMyGuestHide();
        conveyCorporationEmployeesHide()

    } else if ($(this).val() == 'SIMPLE_TAXI' || $(this).val() == 'GUEST_DELIVERY') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestHide();
        conveyCorporationEmployeesHide()
    } else if ($(this).val() == 'CELEBRATION_TAXI' || $(this).val() == 'TAXI_FOR_LONG_TERM') {
        additionalOptionsShow();
        taxiForLongTermShow();
        meetMyGuestHide();
        conveyCorporationEmployeesHide()
    } else if ($(this).val() == 'CONVEY_CORPORATION_EMPLOYEES') {
        additionalOptionsShow();
        taxiForLongTermHide();
        meetMyGuestHide();
        $('#buttonAddressOrigin').show();
        conveyCorporationEmployeesShow();
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

    function meetMyGuestHide() {
        $('#description').remove('placeholder');
    }

    function meetMyGuestShow() {
        //TODO complete this method
        $('#description').attr('placeholder', 'Here you can write the name of the guest and additional information');
        //  $('#description').add("placeholder=\"Here you can write the name of the guest and additional information\"");
    }

    function conveyCorporationEmployeesHide() {
        $('#buttonAddressOrigin').hide();
    }

    function conveyCorporationEmployeesShow() {
        alert("show convey");
        $('#buttonAddressOrigin').show();

        //var counter = 1;
        //var limit = 100;
        //alert("fdg");
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

$('#buttonAddressOrigin').click(function (){
    alert("button");
    var counter = 1;
    if(counter==1){
        $('#addressOrigin1').show();
    }else if (counter==2){
        $('#addressOrigin2').show();
    }else if (counter==3){
        $('#addressOrigin3').show();
    }else if (counter==4){
        $('#addressOrigin4').show();
    }else if (counter==5){
        $('#addressOrigin5').show();
    }
});