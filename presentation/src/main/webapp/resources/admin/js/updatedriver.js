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
            password: {
                required: false,
                minlength:5
            },
            confirmpassword: {
                required: false,
                minlength:5,
                equalTo: "#password"
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
        data["password"] = calcMD5($('input[name = password]', '#updateItDriver').val());
        data["phone"] = $('input[name = phone]', '#updateItDriver').val();
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