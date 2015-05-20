/**
 * Created by Oleksandr Kozin on 20.05.2015.
 */

$(document).ready(function () {
    $('#getResultProfit').click(function () {
        var startDate = $('input[name = date]', '#datepicker-profit').val();
        $.ajax({
            type: 'POST',
            data: {startDate: startDate},
            url: 'most-profit',
            success: function (result) {
                $("#placeholder2").text(result);
            }
        });
    });
});
