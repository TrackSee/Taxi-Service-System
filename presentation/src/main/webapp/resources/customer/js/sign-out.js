/**
 * Created by Ruslan Gunavardana
 */
function signOut() {
    $.ajax({
        type: 'POST',
        url: getContextPath() + 'signout',
        success: function (data) {
            updateHeader();
        },
        error: function (xhr, str) {
            updateHeader();
        }
    });
}

function updateHeader() {
    $('#signout').remove();
    var ul = $('#button-list');
    var count = 2;
    var nameArr = ['Sign in', 'Sign up'];
    var hrefArr = ['signin', 'signup'];
    var liArr = [];
    var aArr = [];
    for (var i = 0; i < count; i++) {
        liArr[i] = document.createElement('li');
        aArr[i] = document.createElement('a');
        aArr[i].href = hrefArr[i];
        aArr[i].appendChild(document.createTextNode(nameArr[i]));
        liArr[i].appendChild(aArr[i]);
        ul.append(liArr[i]);
    }
}