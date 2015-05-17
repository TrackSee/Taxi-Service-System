$(document).ready(function() {
    $('.pageLi1').addClass('active');
    var pageButton = $('.pageButton').click(function(event){
        var pageNumber = this.innerHTML;

        $('.active').removeClass('active');
        $('.pageLi' + pageNumber).addClass('active');
        $.ajax({
            type: 'POST',
            data: 'pageNumber=' + pageNumber,
            url: 'cars',
            success: function(data){
                var carsArray = JSON.parse(data);
                var tBody = document.getElementById('table-body');
                var rows = tBody.children;
                var i;
                for (i = 0; i < Math.max(rows.length, carsArray.length); i++) {
                    if (i < carsArray.length){
                        rows[i].style.display = 'table-row';
                        rows[i].children[0].innerHTML = '<a href="updatecar?carNumber=' + carsArray[i].carNumber + '">'+
                            carsArray[i].carNumber + '</a>';
                        rows[i].children[1].innerHTML = carsArray[i].carModel != null ? carsArray[i].carModel : "-";
                        rows[i].children[2].innerHTML = carsArray[i].color != null ? carsArray[i].color : "-";
                        rows[i].children[3].innerHTML = carsArray[i].carCategory!= null ? carsArray[i].carCategory : "-";
                        rows[i].children[4].innerHTML = carsArray[i].acceptsVisa == true ? "+" : "-";
                        rows[i].children[5].innerHTML = carsArray[i].animalTransportationApplicable == true ? "+" : "-";
                        rows[i].children[6].innerHTML = carsArray[i].freeWifi == true ? "+" : "-";
                        rows[i].children[7].innerHTML = carsArray[i].airConditioner == true ? "+" : "-";
                        rows[i].children[8].innerHTML = '<a href="updatecar?carNumber=' + carsArray[i].carNumber + '">'+ "Edit" +'</a>';
                        rows[i].children[9].innerHTML = '<a href="deletecar?carNumber=' + carsArray[i].carNumber + '">'+ "Delete" +'</a>';
                    } else {
                        rows[i].style.display = 'none';
                    }
                }
            }
        });
    });
});
