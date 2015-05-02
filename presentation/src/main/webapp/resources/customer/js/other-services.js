/**
 * This script disable hidden from fields when
 * selected services that don't need hidden fields.
 *
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
