/**
 * Created by Katia Stetsiuk on 25-Apr-15.
 */
$(document).ready(function () {

    var data = {};

    $('#addCar').click(function(){
       // data["carNumber"] = $('input[name = carNumber]', '#updateItCar').val();
        data["carModel"] = $('input[name = carModel]', '#updateItCar').val();
        data["color"] = $('input[name = carColor]', '#updateItCar').val();
        // data["carCategory"] =$('select[name=category]').val()

        data["animalTransportationApplicable"] = $('input[name = animalTransportationApplicable]', '#updateItCar').prop("checked");
        data["freeWifi"] = $('input[name = freeWifi]', '#updateItCar').prop("checked");
        data["airConditioner"] = $('input[name = airConditioner]', '#updateItCar').prop("checked");
        data = JSON.stringify(data);
        $.ajax({
            type: 'POST',
            url: 'updatecar',
            data: data,
            success: function(data){
                window.location.href = 'cars';
            }
        });
    });
});