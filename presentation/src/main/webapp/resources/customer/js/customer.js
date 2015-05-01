/**
 * Created by Vadym Akymov on 29.04.15.
 */

$('.nextButton').click(function(){
    var pageNumber = $('.pageNumber').val();
    var pagesCount = $('.pagesCount').val();
    console.log(pageNumber + '/' + pagesCount);
    if((++pageNumber) >= pagesCount){
        $('.nextButton').attr('disabled','disabled');
    } else {
        $('.nextButton').removeAttr('disabled');
    }
    if((--pageNumber) < 1){
        $('.prevButton').attr('disabled','disabled');
    } else {
        $('.prevButton').removeAttr('disabled');
    }
    $.ajax({
        type: 'GET',
        url: 'get-orders',
        data: 'pageNumber=' + (++pageNumber),
        success: function(data){
            $('.pageNumber').val(pageNumber);
            var ordersArray = JSON.parse(data);
            for(var i = 0; i < ordersArray.length; i++){
                console.log(ordersArray[i].trackingNumber);
                $('.order' + i).text('Order № ' + ordersArray[i].trackingNumber);
                $('.service' + i).html('<b>SERVICE:</b> ' + ordersArray[i].service);
                $('.status' + i).html('<b>STATUS:</b> ' + ordersArray[i].status);
                $('.price' + i).html('<b>PRICE:</b> ' + ordersArray[i].price.toFixed(2) + ' grn');
                var orderedDate = new Date(ordersArray[i].orderedDate);
                $('.date' + i).html('<b>DATE:</b> ' + formatDate(orderedDate));
            }
        }
    });
});


//Previous button click
$('.prevButton').click(function(){
    var pageNumber = $('.pageNumber').val();
    var pagesCount = $('.pagesCount').val();
    console.log(pageNumber + '/' + pagesCount);
    if((--pageNumber) <= 1){
        $('.prevButton').attr('disabled','disabled');
    } else {
        $('.prevButton').removeAttr('disabled');
    }
    if((++pageNumber) > pagesCount){
        $('.nextButton').attr('disabled','disabled');
    } else {
        $('.nextButton').removeAttr('disabled');
    }
    $.ajax({
        type: 'GET',
        url: 'get-orders',
        data: 'pageNumber=' + (--pageNumber),
        success: function(data){
            $('.pageNumber').val(pageNumber);
            var ordersArray = JSON.parse(data);
            for(var i = 0; i < ordersArray.length; i++){
                console.log(ordersArray[i].trackingNumber);
                $('.order' + i).text('Order № ' + ordersArray[i].trackingNumber);
                $('.service' + i).html('<b>SERVICE:</b> ' + ordersArray[i].service);
                $('.status' + i).html('<b>STATUS:</b> ' + ordersArray[i].status);
                $('.price' + i).html('<b>PRICE:</b> ' + ordersArray[i].price.toFixed(2) + ' grn');
                var orderedDate = new Date(ordersArray[i].orderedDate);
                $('.date' + i).html('<b>DATE:</b> ' + formatDate(orderedDate));
            }
        }
    });
});

function formatDate(d) {

    var dd = d.getDate();
    if ( dd < 10 ) dd = '0' + dd;

    var mm = d.getMonth()+1;
    if ( mm < 10 ) mm = '0' + mm;

    var yy = d.getFullYear();
    //if ( yy < 10 ) yy = '0' + yy;

    return dd+'-'+mm+'-'+yy
}