/**
 * Created by kstes_000 on 25-Apr-15.
 */

$('#addDriver').bind('click', function () {
    var data = {};
    data["email"] = $('input[name = email]', '#createDriver').val();
    data["password"] = $('input[name = password]', '#createDriver').val();
    data["phone"] = $('input[name = phone]', '#createDriver').val();

    data = JSON.stringify(data);
    $.ajax({
        type: 'POST',
        url: 'createdriver',
        dataType: 'json',
        data: 'data=' + data
    });
})
