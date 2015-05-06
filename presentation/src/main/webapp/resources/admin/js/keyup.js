/**
 * @author Katia Stetsiuk
 */
$(document).ready(function () {

    var keyUpTime = 2000;
    var keyUpTimeout = null;
    $("#search").on('input', function () {

        clearTimeout(keyUpTimeout);
            var $item = $(this),
                value = $item.val();
            var email = value;
        keyUpTimeout = setTimeout(function() { $.ajax({
            type: 'POST',
            url: 'driverbysearch',
            data: {email: email},
            success: function(data){
                var driversArray = JSON.parse(data);
                var tBody = document.getElementById('table-body');
                var rows = tBody.children;
                var i;
                for (i = 0; i < Math.max(rows.length, driversArray.length); i++) {
                    //add all necessary td (if drivers.length >= rows.length)
                    if (i >= rows.length) {
                        var newTr = document.createElement('tr');
                        //TODO Don't forget to change magic number of attribute tr count
                        for(var j = 0; j < 4; j++){
                            newTr.appendChild(document.createElement('td'));
                        }
                        tBody.appendChild(newTr);
                    }
                    //remove unused td (if rows < drivers)
                    if (i < driversArray.length){
                        //insert driver data
                        rows[i].children[0].innerHTML = '<a href="driver?id=' + driversArray[i].userId + '">'+
                            driversArray[i].email + '</a>';

                        rows[i].children[1].innerHTML = driversArray[i].phone != null? driversArray[i].phone : "-";
                        rows[i].children[2].innerHTML = driversArray[i].sex != null? driversArray[i].sex : "-";
                        rows[i].children[3].innerHTML = driversArray[i].groupName != null? driversArray[i].groupName : "-";

                    } else {
                        tBody.removeChild(rows[i]);
                    }
                }
            }
        }); }, keyUpTime);

    });
});