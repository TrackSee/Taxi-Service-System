/**
 * Created by Ruslan Gunavardana
 */
document.addEventListener("DOMContentLoaded", function(event) {
    $('#sign-out-button').click(function(){
        $.ajax({
            type: 'POST',
            url: getContextPath() + 'signout',
            success: function () {
                updateHeader();
            },
            error: function () {
                updateHeader();
            }
        });
    });

    function updateHeader() {
        if (window.location.href.search('customerProfile') != -1) {
            window.location.href = getContextPath();
        } else {
            $('#signout').remove();
            $('#customerProfile').remove();
            var ul = $('#button-list');
            var elems = [
                {text: 'Sign in', href: 'signin'},
                {text: 'Sign up', href: 'signup'}
            ];
            var liArr = [];
            var aArr = [];
            for (var i = 0; i < elems.length; i++) {
                liArr[i] = document.createElement('li');
                aArr[i] = document.createElement('a');
                aArr[i].href = getContextPath() + elems[i].href;
                aArr[i].appendChild(document.createTextNode(elems[i].text));
                liArr[i].appendChild(aArr[i]);
                ul.append(liArr[i]);
            }
        }
    }
});