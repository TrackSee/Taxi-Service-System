/**
 * @author Ruslan Gunavardana
 */
document.addEventListener("DOMContentLoaded", function(event) {
    $('#sign-out-button').click(function(){
        $.post(getContextPath() + 'signout', updateHeader)
            .fail(function(){
                $(this).notify("Server connection problems.", "warn")
            });
    });

    function updateHeader() {
        if (window.location.href.indexOf('customer') != -1 ||
            window.location.href.indexOf('admin')    != -1 ||
            window.location.href.indexOf('driver')   != -1)
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