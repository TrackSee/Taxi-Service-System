/**
 * Created by kstes_000 on 25-Apr-15.
 */
$(document).ready(function () {

    $('#updateItDriver').validate({ // initialize the plugin
        rules: {
            email: {
                required: false,
                email:true
            },
            phone: {
                required: false,
                minlength:7
            }
        }
    });



    $('#updateDriver').bind('click', function () {
        var data = {};
        data["email"] = $('input[name = email]', '#updateItDriver').val();
        data["phone"] = $('input[name = phone]', '#updateItDriver').val();
        data["driverLicense"] = $('input[name = driverLicense]', '#updateItDriver').val();
        data = JSON.stringify(data);

        $.ajax({
            type: 'POST',
            url: 'updatedriver',
            data: data,
            success: function(data){
                window.location.href = 'drivers';
            }
        });
    });
});