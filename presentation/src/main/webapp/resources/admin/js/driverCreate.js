/**
 * Created by kstes
 */
$(document).ready(function () {
    $('#password').focus(function() {
        $(this).notify('Please, use at least 6 characters');
    });
    $('#phone').focus(function() {
        $(this).notify('Please, use at least 7 characters');
    })

    $('#createDriver').validate({
        rules: {
            email: {
                required: true,
                email:true
            },
            password: {
                required: true,
                minlength:6
            },
            confirmpassword: {
                required: true,
                minlength:6,
                equalTo: "#password"
            },
            phone: {
                required: false,
                minlength:7
            }
        }
    });

    $(".dropdown-menu").on('click', 'li a', function(){
        $(".btn-sm:first-child").text($(this).text());
        $(".btn-sm:first-child").val($(this).text());
    });



    $('#addDriver').click(function(){
        var data = {};
        data["email"] = $('input[name = email]', '#createDriver').val();
        data["password"] = $('input[name = password]', '#createDriver').val();
        data["phone"] = $('input[name = phone]', '#createDriver').val();
        data["driverLicense"] = $('input[name = driverLicense]', '#createDriver').val();
        data["sex"] = $('select option:selected').val();
        data = JSON.stringify(data);
        $.ajax({
            type: 'POST',
            url: 'createdriver',
            data: data,
            success: function(data){
                window.location.href = 'drivers';
            },
            error: function(jqXHR, error, errorThrown) {
                window.location.replace("drivers");
                alert('Sorry, but such user already exist!');
            }
        });
    });
});