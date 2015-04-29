/**
 * author Sharaban Sasha
 */

$(document).ready(function() {

    $('#service').on('change', function() {
        if ($(this).val() == 'soberDriver'){
            $('#carCategory').attr('disabled','disabled');
            $('#musicStyle').attr('disabled','disabled');
            $('#animalTransportation').attr('disabled','disabled');
            $('#freeWifi').attr('disabled','disabled');
            $('#smokingDriver').attr('disabled','disabled');
            $('#airConditioner').attr('disabled','disabled');
        }else{
            $('#carCategory').removeAttr('disabled');
            $('#musicStyle').removeAttr('disabled');
            $('#animalTransportation').removeAttr('disabled');
            $('#freeWifi').removeAttr('disabled');
            $('#smokingDriver').removeAttr('disabled');
            $('#airConditioner').removeAttr('disabled');
        }
    });
});

