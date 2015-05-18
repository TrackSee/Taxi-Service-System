/**
 * Created by Ruslan Gunavardana
 */
function calc() {

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

$(document).ready(function(){
    $('#order-form').submit(function() {
        var formData = $(this).serialize();
        formData += '&route=' + JSON.stringify(getRouteData())
        $.ajax({
            type: 'POST',
            url: getContextPath() + 'order',
            //data: JSON.stringify($(this).serializeObject()),
            data: formData,
            success: function (data) {
                if (data == "error") {
                    $.notify("Invalid data entered!", "error");
                } else {
                    $(document).html(data);
                }
            },
            error: function () {
                $.notify("Internal server error occurred.", "warn");
            }
        });
        return false;
    });
});