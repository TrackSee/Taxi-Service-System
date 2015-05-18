/**
 * Created by Ruslan Gunavardana
 */
function sendForm() {
    $.ajax({
        type: 'POST',
        url: getContextPath() + 'signin',
        data: $('#form-sign-in').serialize(),
        success: function () {
            window.location.replace('.');
        },
        error: function (xhr) {
            var btn = $('#sign-in-submit');
            if (xhr.status == 422) {
                btn.notify("The username or password is incorrect. Please try again.", "error");
            } else {
                btn.notify("Internal server error occurred.", "warn");
            }
        }
    });
}
