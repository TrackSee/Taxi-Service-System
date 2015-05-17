/**
 * Created by Vadym Akymov, Ruslan Gunavardana
 */

$(document).ready(function() {
    $('.pageLi1').addClass('active');
    var pageButton = $('.pageButton').click(function(event){
        var pageNumber = this.innerHTML;
        $('.active').removeClass('active');
        $('.pageLi' + pageNumber).addClass('active');
        $.ajax({
            type: 'POST',
            data: 'pageNumber=' + pageNumber,
            url: 'drivers',
            success: function(data){
                var driversArray = JSON.parse(data);
                var tBody = document.getElementById('table-body');
                var rows = tBody.children;
                var i;
                for (i = 0; i < Math.max(rows.length, driversArray.length); i++) {
                    if (i < driversArray.length){
                        rows[i].style.display = 'table-row';
                        rows[i].children[0].innerHTML = '<a href="driver?id=' + driversArray[i].userId + '">'+
                            driversArray[i].email + '</a>';

                        rows[i].children[1].innerHTML = driversArray[i].phone != null? driversArray[i].phone : "-";
                        rows[i].children[2].innerHTML = driversArray[i].sex != null? driversArray[i].sex : "-";
                        rows[i].children[3].innerHTML = driversArray[i].groupName != null? driversArray[i].groupName : "-";
                    } else {
                        rows[i].style.display = 'none';
                    }
                }
            }
        });
    });
});
