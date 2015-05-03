/**
 * This script show additional information
 * for service "Meet my guest"
 *
 * author Sharaban Sasha
 */
$(document).ready(function() {

    $('#service').on('change', function() {
        if ($(this).val() == 'meetMyGuest'){
            $('#description').attr('placeholder','Here you can write the name of the guest and additional information');
        }else{
            $('#description').removeAttr('placeholder');
        }
    });
});
