/**
 * Created by kstes_000 on 25-Apr-15.
 */
$(document).ready(function () {


    $('#createDriver').validate({
        rules: {
            email: {
                required: true,
                email:true
            },
            password: {
                required: true,
                minlength:5
            },
            confirmpassword: {
                required: true,
                minlength:5,
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
        data["password"] = calcMD5($('input[name = password]', '#createDriver').val());
        data["phone"] = $('input[name = phone]', '#createDriver').val();
        data["sex"] = $('select option:selected').val();
        data = JSON.stringify(data);
        $.ajax({
            type: 'POST',
            url: 'createdriver',
            data: data,
            success: function(data){
                window.location.href = 'drivers';
            }
        });
    });
});