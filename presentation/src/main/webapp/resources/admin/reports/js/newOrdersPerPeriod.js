/**
 * Created by Oleksandr Kozin on 20.05.2015.
 */

$(document).ready(function () {
    $('#getResultOrders').click(function () {
        var startDate = $('input[name = start]', '#datepicker-new-orders').val();
        var endDate = $('input[name = end]', '#datepicker-new-orders').val();
        $.ajax({
            type: 'POST',
            data: {startDate: startDate, endDate: endDate},
            url: 'new-orders',
            success: function (result) {
                $("#placeholder1").text(result);
            }
        });
    });
});
