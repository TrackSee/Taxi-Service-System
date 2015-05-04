/**
 * Created by Vadym_Akymov on 25.04.15.
 */

var displayStatus = true;
//car choosing button
$('.carChooseButton').click(function(event){
    if(displayStatus){
        $('.carChoose').css('display', 'block');
        displayStatus = false;
    } else {
        $('.carChoose').css('display', 'none');
        displayStatus = true;
    }
});


//Ok button
$('.okBtn').click(function(event){
    //get carNumber
    var carNumber = $('.taxiCar').val();
    var driverId = $('#driverId').val();
    console.log('driver id: ' + driverId);
    $.ajax({
        url: 'assigncar',
        type: 'POST',
        data: 'car=' + carNumber + '&driver=' + driverId,
        success: function(data){
            $('.carNumb').html(carNumber);
            //close carChoose panel
            $('.carChooseButton').click();
        }
    });
});