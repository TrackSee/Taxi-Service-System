/**
 * Created by Katia Stetsiuk on 25-Apr-15.
 */
$(document).ready(function () {

    $('#addCar').click(function(){
        var data = {};
        data["carNumber"] = $('input[name = carNumber]', '#createCar').val();
        data["carModel"] = $('input[name = carModel]', '#createCar').val();
        data["color"] = $('input[name = carColor]', '#createCar').val();
        data["carCategory"] = $('select option:selected').val();
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
        data = null;
    });
});