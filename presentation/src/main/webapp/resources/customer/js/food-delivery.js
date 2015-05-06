/**
 * This script hide fields that unnecessary
 * when select service "Foodstuff delivery"
 *
 * author Sharaban Sasha
 */

$(document).ready(function() {
    $('#service').on('change', function() {
        if ($(this).val() == 'foodStuffDelivery'){
            $("#carCategoryGroup").slideUp("slow");
            $('#musicStyleGroup').slideUp("slow");
            $('#animalTransportationCh').slideUp("slow");
            $('#freeWifiCh').slideUp("slow");
            $('#smokingDriverCh').slideUp("slow");
            $('#airConditionerCh').slideUp("slow");
        }
    });
});