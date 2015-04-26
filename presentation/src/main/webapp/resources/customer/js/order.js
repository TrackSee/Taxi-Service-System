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
                var notifyMessage;

                if (data == "success") {
                    $(location).attr('href', '/');
                } else

                if (data == "bad-email") {
                    $.notify("Invalid email!", "error");
                } else if (data == "bad-password") {
                    $.notify("Invalid password", "error");
                } else if (data == "bad-phone") {
                    $.notify("Invalid phone number", "error");
                } else if (data == "send-fail") {
                    $.notify("Email sending failed", "info");
                } else if (data == "user-exists") {
                    $.notify("You are already registered", "info");
                } else {
                    $('document').html(data);
                }
            },
            error: function (xhr, str) {
                $.notify("Server failed", "info");
            }
        });
        return false;
    });
});
