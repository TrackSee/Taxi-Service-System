/**
 * author Sharaban Sasha
 */

$(document).ready(function() {
    $('#service').on('change', function() {
         if ($(this).val() == 'default'||$(this).val() == 'meetMyGuest'||$(this).val() == 'guestDelivery'
                ||$(this).val() == 'celebrationTaxi'){
                $("#carCategoryGroup").slideDown("slow");
                $('#musicStyleGroup').slideDown("slow");
                $('#freeWifiCh').slideDown("slow");
                $('#smokingDriverCh').slideDown("slow");
                $('#airConditionerCh').slideDown("slow");
                $('#animalTransportationCh').slideDown("slow");
            }
    });
});
