/**
 * author Sharaban Sasha
 */

/**
 * This script hide fields that unnecessary
 * when select service "Foodstuff delivery"
 *
 * author Sharaban Sasha
 */

$('#service').on('change', function () {
    if ($(this).val() == 'FOODSTUFF_DELIVERY') {
        $("#carCategoryGroup").slideUp("slow");
        $('#musicStyleGroup').slideUp("slow");
        $('#animalTransportationCh').slideUp("slow");
        $('#freeWifiCh').slideUp("slow");
        $('#nonSmokingDriverCh').slideUp("slow");
        $('#airConditionerCh').slideUp("slow");

    } else
    if ($(this).val() == 'CARGO_TAXI') {
        $("#carCategoryGroup").slideUp("slow");
        $('#musicStyleGroup').slideUp("slow");
        $('#freeWifiCh').slideUp("slow");
        $('#nonSmokingDriverCh').slideUp("slow");
        $('#airConditionerCh').slideUp("slow");
        $('#animalTransportationCh').slideDown("slow");


    } else
    if ($(this).val() == 'MEET_MY_GUEST') {
        $("#carCategoryGroup").slideDown("slow");
        $('#musicStyleGroup').slideDown("slow");
        $('#freeWifiCh').slideDown("slow");
        $('#nonSmokingDriverCh').slideDown("slow");
        $('#airConditionerCh').slideDown("slow");
        $('#animalTransportationCh').slideDown("slow");
        alert("vd");
        $('#description').attr('placeholder', 'Here you can write the name of the guest and additional information');
    } else
    if ($(this).val() == 'SOBER_DRIVER') {
        $("#carCategoryGroup").slideUp("slow");
        $('#musicStyleGroup').slideUp("slow");
        $('#animalTransportationCh').slideUp("slow");
        $('#freeWifiCh').slideUp("slow");
        $('#nonSmokingDriverCh').slideUp("slow");
        $('#airConditionerCh').slideUp("slow");

    }else
    if ($(this).val() == 'SIMPLE_TAXI'){
        $("#carCategoryGroup").slideDown("slow");
        $('#musicStyleGroup').slideDown("slow");
        $('#freeWifiCh').slideDown("slow");
        $('#nonSmokingDriverCh').slideDown("slow");
        $('#airConditionerCh').slideDown("slow");
        $('#animalTransportationCh').slideDown("slow");
    }else
    if ($(this).val() == 'CELEBRATION_TAXI'|| (this).val() == 'TAXI_FOR_LONG_TERM'){
        alert("LONG TERM");
        $("#carCategoryGroup").slideDown("slow");
        $('#musicStyleGroup').slideDown("slow");
        $('#freeWifiCh').slideDown("slow");
        $('#nonSmokingDriverCh').slideDown("slow");
        $('#airConditionerCh').slideDown("slow");
        $('#animalTransportationCh').slideDown("slow");
        $('#endDateBlock').slideDown("slow");
    }
});

