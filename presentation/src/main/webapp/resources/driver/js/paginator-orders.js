/**
 * Created by maria on 05.05.2015.
 */
$(document).ready(function () {
    $('.pageLi1').addClass('active');
    var pageButton = $('.pageButton').click(function (event) {
        var pageNumber = this.innerHTML;
        $('.active').removeClass('active');
        $('.pageLi' + pageNumber).addClass('active');
        $.ajax({
            type: 'POST',
            data: 'pageNumber=' + pageNumber,

            url: 'history-of-orders',

            success: function (data) {
                ;
                var ordersArray = JSON.parse(data);
                var tBody = document.getElementById('table-body');
                var rows = tBody.children;
                var i;
                for (i = 0; i < Math.max(rows.length, ordersArray.length); i++) {

                    //add all necessary td (if cars.length > rows.length)
                    if (i >= rows.length) {
                        var newTr = document.createElement('tr');
                        //TODO Don't forget to change magic number of attribute tr count
                        for (var j = 0; j <6 ; j++) {
                            newTr.appendChild(document.createElement('td'));
                        }
                        tBody.appendChild(newTr);
                    }
                    //remove unused td (if rows < cars)
                    if (i < ordersArray.length) {

                        rows[i].children[0].innerHTML = ordersArray[i].trackingNumber;
                        rows[i].children[1].innerHTML = ordersArray[i].carArriveTime;
                        rows[i].children[2].innerHTML = "-";
                        rows[i].children[4].innerHTML = "-";
                        rows[i].children[5].innerHTML = ordersArray[i].price;
                        rows[i].children[6].innerHTML = ordersArray[i].comment;

                    } else {
                        tBody.removeChild(rows[i]);
                    }
                }
            }
        });
    });
});