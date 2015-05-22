/**
 * Created by Ruslan Gunavardana
 */
document.addEventListener("DOMContentLoaded", function(event) {
    $('#sign-out-button').click(function(){
        $.post(getContextPath() + 'signout', updateHeader)
            .fail(function(){
                $(this).notify("Server connection problems.", "warn")
            });
    });

    function updateHeader() {
        if (window.location.href.contains('customer') ||
            window.location.href.contains('admin') ||
            window.location.href.contains('driver'))
        {
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