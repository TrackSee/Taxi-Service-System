$(document).on('click', 'button', function(event) {
    var carNumber = this.className;
    if(confirm("Do you really want to delete this car?") == true) {
    $.ajax({
        type: 'GET',
        url: 'deletecar',
        data: {carNumber: carNumber},
        success: function() {
            window.location.replace("cars");
            alert('Delete!');
        },
        error: function(jqXHR, error, errorThrown) {
            window.location.replace("cars");
            alert('Сan not be deleted because it is assigned by the driver!');
        }
    });
    }
});

