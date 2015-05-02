/**
 * Created by Ruslan Gunavardana
 */
function sendForm() {
    var msg = $('#form-sign-in').serialize();
    $.ajax({
        type: 'POST',
        url: 'signin',
        data: msg,
        success: function (data) {
            console.log('success' == data);
            console.log(data);
            if (data != 'error') {
                window.location.replace('.');
            } else {
                $.notify("The username or password is incorrect. Please try again.", "error");
            }
        },
        error: function (xhr, str) {
            $.notify("Internal server error occurred.", "warn");
        }
    });
}
