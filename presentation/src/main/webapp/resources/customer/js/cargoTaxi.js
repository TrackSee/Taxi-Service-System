/**
 * This script hide fields that unnecessary
 * when select service "Cargo taxi"
 *
 * author Sharaban Sasha
 */

$(document).ready(function() {
    $('#service').on('change', function() {
        if ($(this).val() == 'cargoTaxi'){
            $("#carCategoryGroup").slideUp("slow");
            $('#musicStyleGroup').slideUp("slow");
            $('#freeWifiCh').slideUp("slow");
            $('#smokingDriverCh').slideUp("slow");
            $('#airConditionerCh').slideUp("slow");
            $('#animalTransportationCh').slideDown("slow");
        }
    });
});
