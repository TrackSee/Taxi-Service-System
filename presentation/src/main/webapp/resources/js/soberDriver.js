/**
 * author Sharaban Sasha
 */

$(document).ready(function() {

    $('#service').on('change', function() {
        if ($(this).val() == 'soberDriver'){
            $('#carCategory').attr('hidden','hidden');
            $('#musicStyle').attr('hidden','hidden');
            $('#animalTransportation').attr('hidden','hidden');
            $('#freeWifi').attr('hidden','hidden');
            $('#smokingDriver').attr('hidden','hidden');
            $('#airConditioner').attr('hidden','hidden');
        }else{
            $('#carCategory').removeAttr('hidden');
            $('#musicStyle').removeAttr('hidden');
            $('#animalTransportation').removeAttr('hidden');
            $('#freeWifi').removeAttr('hidden');
            $('#smokingDriver').removeAttr('hidden');
            $('#airConditioner').removeAttr('hidden');
        }
    });
});

