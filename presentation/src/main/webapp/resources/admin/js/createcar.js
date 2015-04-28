/**
 * Created by kstes_000 on 27-Apr-15.
 */
/**
 * Created by kstes_000 on 25-Apr-15.
 */
$(document).ready(function () {

//
//    $('#createCar').validate({
//        rules: {
//            email: {
//                required: true,
//                email:true
//            },
//            password: {
//                required: true,
//                minlength:5
//            },
//            confirmpassword: {
//                required: true,
//                minlength:5,
//                equalTo: "#password"
//            },
//            phone: {
//                required: true,
//                minlength:9
//            }
//        }
//    });

    var data = {};

    $('#addCar').click(function(){
        data["carNumber"] = $('input[name = carNumber]', '#createCar').val();
        data["carModel"] = $('input[name = carModel]', '#createCar').val();
        data["color"] = $('input[name = carColor]', '#createCar').val();
       // data["carCategory"] =$('select[name=category]').val()

        data["animalTransportationApplicable"] = $('input[name = animalTransportationApplicable]', '#createCar').prop("checked");
        data["freeWifi"] = $('input[name = freeWifi]', '#createCar').prop("checked");
        data["airConditioner"] = $('input[name = airConditioner]', '#createCar').prop("checked");
        data = JSON.stringify(data);
        $.ajax({
            type: 'POST',
            url: 'createcar',
            data: data,
            success: function(data){
                window.location.href = 'cars';
            }
        });
    });
});
