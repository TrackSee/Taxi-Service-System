/**
 * Created by Ruslan Gunavardana
 */
$().ready(function () {
    $.validator.addMethod('password-regex', function(value, element)
    {
        return this.optional(element) || /^(?=.*[0-9@#$%^&+=])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9@#$%^&+=]{6,28}$/i.test(value);
    }, 'Use at least one non-alphabet character, one lowercase letter, and one uppercase letter. <br>Use between 6 and 40 characters');

    // validate signin-form on keyup and submit
    $('.form-sign-up').validate({
        rules: {
            email : 'required email',
            password: 'required password-regex',
            'repeat-password': {
                equalTo: '#password'
            },
            'phone-number' : {
                minlength: 5,
                maxlength: 28
            }
        },
        messages: {
            password: {
                required:  'Please provide a password',
                minlength: 'Your password must be at least 6 characters long',
                maxlength: 'Your password must be less than 29 characters long'
            },
            'repeat-password': {
                equalTo:   'Please enter the same password as above'
            },
            'phone-number' : {
                minlength: 'Sorry but phone numbers less than 5 characters are not supported',
                maxlength: 'Sorry but phone numbers more than 28 characters are not supported'
            }
        }
    })
});
