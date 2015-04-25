/**
 * Created by Vadym_Akymov on 25.04.15.
 */

$('.removeBtn').click(function(event){
    var driverId = $('#driverId').val();
    if(confirm("Do you really want to delete this driver?") == true) {
        $.ajax({
            type: 'POST',
            url: 'driver',
            data: 'id=' + driverId,
            success: function(data){
                window.location.href = "drivers";
            }
        });
    }
});