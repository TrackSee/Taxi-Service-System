/**
 * This script hide fields that unnecessary
 * when select service "Sober driver"
 *
 * author Sharaban Sasha
 */

$(document).ready(function() {
    $('#service').on('change', function() {
        if ($(this).val() == 'soberDriver') {
            $("#carCategoryGroup").slideUp("slow");
            $('#musicStyleGroup').slideUp("slow");
            $('#animalTransportationCh').slideUp("slow");
            $('#freeWifiCh').slideUp("slow");
            $('#smokingDriverCh').slideUp("slow");
            $('#airConditionerCh').slideUp("slow");
        }
    });
});
