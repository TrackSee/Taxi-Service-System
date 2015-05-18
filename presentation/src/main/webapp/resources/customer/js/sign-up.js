/**
 * Created by Ruslan Gunavardana
 */
$('#password').focus(function() {
    $(this).notify('Please, use at least 1 digit, 1 uppercase and 1 lowercase letter ' +
    '\nfor making your password safe', 'info')
});

$(document).ready(function(){
    $('#form-sign-up').submit(function() {
        var form = $(this);
        $.ajax({
            type: 'POST',
            url: getContextPath() + 'signup',
            data: form.serialize(),
            success: function (data) {
                form.html(data);
            },
            error: function (xhr) {
                if (xhr.status = 422) {
                    var data = xhr.responseText;
                    if (data.search('bad-email') != -1) {
                        $('#email').notify('Please, enter a valid email!', 'error');
                    }
                    if (data.search('bad-password') != -1) {
                        $('#password').notify('Please, enter a valid password', 'error');
                    }
                    if (data.search('bad-phone') != -1) {
                        $('#phone-number').notify('Please, enter a valid phone number', 'error');
                    }
                    if (data.search('user-exists') != -1) {
                        $('#email').notify('This email is already registered', 'info');
                    }
                    if (data.search('bad-first-name') != -1) {
                        $('#first-name').notify('Please, shorten your first name to 50 symbols', 'warn');
                    }
                    if (data.search('bad-last-name') != -1) {
                        $('#last-name').notify('Please, shorten your last name to 50 symbols', 'warn');
                    }
                } else {
                    $('#sign-up-submit').notify('Internal server error occurred.', 'warn');
                }
            }
        });
    });
});

/**
 * Sign up form validation using jQuery validation plugin.
 */
$().ready(function () {
    $.validator.addMethod('passwordRegex', function(value, element)
    {
        return this.optional(element) || /^(?=.*[0-9@#$%^&+=_])(?=.*[a-z])(?=.*[A-Z])[\w@#$%^&+=]*$/.test(value);
    }, 'Use at least one non-alphabet character, one lowercase letter, and one uppercase letter');

    $.validator.addMethod('phoneRegex', function(value, element)
    {
        return this.optional(element) || /^\+?[0-9 ()-]{5,27}$/.test(value);
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
