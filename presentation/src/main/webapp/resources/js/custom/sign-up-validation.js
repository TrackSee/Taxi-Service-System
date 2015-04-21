/**
 * Created by Ruslan Gunavardana
 */
$().ready(function () {
    $.validator.addMethod('passwordRegex', function(value, element)
    {
        return this.optional(element) || /^(?=.*[0-9@#$%^&+=])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9@#$%^&+=]*$/i.test(value);
    }, 'Use at least one non-alphabet character, one lowercase letter, and one uppercase letter');

    $.validator.addMethod('phoneRegex', function(value, element)
    {
        return this.optional(element) || /^[0-9 +()-]*$/i.test(value);
    }, 'Better to use digits, whitespaces or symbols +()-');

    // validate signin-form on keyup and submit
    $('.form-sign-up').validate({
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
