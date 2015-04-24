/**
 * Created by Ruslan Gunavardana
 */
function sendForm() {
    var msg = $('#form-sign-up').serialize();
    $.ajax({
        type: 'POST',
        url: 'signup',
        data: msg,
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
}

$().ready(function () {
    $.validator.addMethod('passwordRegex', function(value, element)
    {
        return this.optional(element) || /^(?=.*[0-9@#$%^&+=_])(?=.*[a-z])(?=.*[A-Z])[\w@#$%^&+=]*$/.test(value);
    }, 'Use at least one non-alphabet character, one lowercase letter, and one uppercase letter');

    $.validator.addMethod('phoneRegex', function(value, element)
    {
        return this.optional(element) || /^[0-9 +()-]*$/.test(value);
    }, 'Better to use digits, whitespaces or symbols +()-');

    // validate signin-form on keyup and submit
    $('#form-sign-up').validate({
        rules: {
            email : 'required email',
            password: {
                required: true,
                minlength: 6,
                maxlength: 28,
                passwordRegex: true
            },
            'repeat-password': {
                equalTo: '#password'
            },
            'phone-number' : {
                minlength: 5,
                maxlength: 28,
                phoneRegex: true
            }
        },
        messages: {
            password: {
                required:  'Please provide a password',
                minlength: 'Use between 6 and 28 characters',
                maxlength: 'Use between 6 and 28 characters'
            },
            'repeat-password': {
                required:  'Please provide a password',
                equalTo:   'Please enter the same password as above'
            },
            'phone-number' : {
                minlength: 'Sorry but phone numbers less than 5 characters are not supported',
                maxlength: 'Sorry but phone numbers more than 28 characters are not supported'
            }
        }
    })
});
