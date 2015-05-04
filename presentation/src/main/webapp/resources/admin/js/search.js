$(document).ready(function () {

    var data = {};

    $('#addCar').click(function(){
        data["email"] = $('input[name = email]', '#createCar').val();

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