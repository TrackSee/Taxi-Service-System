$(document).ready(function () {

    var data = {};

    $('#getDriver').click(function(){
        data["email"] = $('input[name = email]', '#createCar').val();

        $.ajax({
            type: 'POST',
            url: 'searchdriver',
            data: data,
            success: function(data){
                window.location.href = 'driver';
            }
        });
    });
});