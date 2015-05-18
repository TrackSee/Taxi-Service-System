$(document).on('click', 'button', function(event) {
    var carNumber = this.className;
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
            alert('Can not delete!');
        }
    });

});

