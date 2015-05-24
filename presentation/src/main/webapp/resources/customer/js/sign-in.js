/**
 * @author Ruslan Gunavardana
 */
$(document).ready(function(){
    $('#form-sign-in').submit(function() {
        var req = $.post(getContextPath() + 'signin', $(this).serialize());

        req.done(function () {
            window.location.replace('.');
        });

        req.fail(function (xhr) {
            var btn = $('#sign-in-submit');
            if (xhr.status == 422) {
                btn.notify("The username or password is incorrect. Please try again.", "error");
            } else {
                btn.notify("Internal server error occurred.", "warn");
            }
        });
    });
});
