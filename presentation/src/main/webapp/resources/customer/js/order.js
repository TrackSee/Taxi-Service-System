/**
 * Created by Ruslan Gunavardana
 */
function calculatePrice() {

}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$(function() {
    $('#order-form').submit(function() {
        $.ajax({
            type: 'POST',
            url: 'signup',
            data: JSON.stringify(this.serializeObject()),
            success: function (data) {
                if (data == "error") {
                    $.notify("Invalid data entered!", "error");
                } else {
                    $('document').html(data);
                }
            },
            error: function (xhr, str) {
                $.notify("Internal server error occurred.", "warn");
            }
        });
        return false;
    });
});
