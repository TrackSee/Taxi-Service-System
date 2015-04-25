/**
 * Created by kstes_000 on 25-Apr-15.
 */
$(document).ready(function () {
//    $('#createDriver').validate({ // initialize the plugin
//        rules: {
//            email: {
//                required: true
//            },
//            password: {
//                required: true
//            },
//            phone: {
//                required: true
//            }
//        }
//    });
//

        $('#createDriver').validate({ // initialize the plugin
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
                required: true,
                minlength:9
            }
        }
    });



$('#addDriver').bind('click', function () {
    var data = {};
    data["email"] = $('input[name = email]', '#createDriver').val();
    data["password"] = $('input[name = password]', '#createDriver').val();
    data["phone"] = $('input[name = phone]', '#createDriver').val();

    data = JSON.stringify(data);
    $.ajax({
        type: 'POST',
        url: 'createdriver',
        dataType: 'json',
        data: 'data=' + data
    });
});
});