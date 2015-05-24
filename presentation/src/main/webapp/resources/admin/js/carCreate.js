/**
 * Created by Katia Stetsiuk on 25-Apr-15.
 */
$(document).ready(function () {

    $("#carNumber").focus(function() {
        $(this).notify('Please, use at least 3 characters, but no more than 15');
    });

    $('#createCar').validate({
        rules: {
            carNumber: {
                required: true,
                minlength:3,
                maxlength: 15
            },
            carModel: {
                required: true,
                minlength:3,
                maxlength:15
            }
        }
    });

    $('#addCar').click(function(){
        var data = {};
        data["carNumber"] = $('input[name = carNumber]', '#createCar').val();
        data["carModel"] = $('input[name = carModel]', '#createCar').val();
        data["color"] = $('input[name = carColor]', '#createCar').val();
        data["carCategory"] = $('select option:selected').val();
        data["acceptsVisa"] = $('input[name = acceptsVisa]', '#createCar').prop("checked");
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
            },
            error: function(jqXHR, error, errorThrown) {
                window.location.replace("cars");
                alert('Sorry, but such car number already exist!');
            }
        });
        data = null;
    });
});
