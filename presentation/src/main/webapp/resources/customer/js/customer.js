/**
 * Created by Vadym Akymov on 29.04.15.
 */
//temporary
var pNumber = $('.pageNumber').val();
var pCount = $('.pagesCount').val();
//controll buttons enabled
if(pCount == 1 || pCount == 0){
    $('.nextButton').attr('disabled','disabled');
    $('.prevButton').attr('disabled','disabled');
}
var dataType = $('.datatype').val();
console.log("type: " + dataType);
//orders per page
var itemsPerPage = 3;
console.log('dataType value: ' + dataType);

//click on old button
$('.completed').click(function(){
	isAdmin = true;
    window.location.href = '?type=completed';
});
//click on active button
$('.act').click(function(){
	isAdmin = true;
    window.location.href = '?type=active';
});

$('.nextButton').click(function(){
    var pageNumber = $('.pageNumber').val() - 0;
    console.log(pageNumber + 1);
    if(pageNumber + 1 >= pCount){
        $('.nextButton').attr('disabled','disabled');
        $('.prevButton').removeAttr('disabled');
    } else {
        $('.nextButton').removeAttr('disabled');
        $('.prevButton').removeAttr('disabled');
    }
    $.ajax({
        type: 'GET',
        url: 'customer/get-orders',
        data: 'pageNumber=' + (++pageNumber) + '&type=' + dataType,
        success: function(data){
            $('.pageNumber').val(pageNumber);
            var ordersArray = JSON.parse(data);
            //control count of items on page
            console.log("Childrens: " + $('.orderRow').children().length);
            if(ordersArray.length < $('.orderRow').children().length){
                for(var i = ordersArray.length; i < itemsPerPage; i++){
                    $('.orderSpan' + i).remove();
                }
            } else {
                for(var i = $('.orderRow').children().length; i < itemsPerPage; i++){
                    console.log('i = ' + i);
                    var childSpan = createOrderSpan(i);
                    $('.orderRow').append(childSpan);
                }
            }
            for(var i = 0; i < ordersArray.length; i++){
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
    var pageNumber = $('.pageNumber').val() - 0;
    console.log(pageNumber - 1);
    if(pageNumber - 1 <= 1){
        $('.prevButton').attr('disabled','disabled');
        $('.nextButton').removeAttr('disabled');
    } else {
        $('.prevButton').removeAttr('disabled');
        $('.nextButton').removeAttr('disabled');
    }
    $.ajax({
        type: 'GET',
        url: 'customer/get-orders',
        data: 'pageNumber=' + (--pageNumber) + '&type=' + dataType,
        success: function(data){
            $('.pageNumber').val(pageNumber);
            var ordersArray = JSON.parse(data);
            //control count of items on page
            console.log("Childrens: " + $('.orderRow').children().length);
            if(ordersArray.length < $('.orderRow').children().length){
                for(var i = ordersArray.length; i < itemsPerPage; i++){
                    $('.orderSpan' + i).remove();
                }
            } else {
                for(var i = $('.orderRow').children().length; i < itemsPerPage; i++){
                    console.log('i = ' + i);
                    var childSpan = createOrderSpan(i);
                    var orderRow = $('.orderRow');
                    orderRow.append(childSpan);
                }
            }
            for(var i = 0; i < ordersArray.length; i++){
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
//
////tracking number href
//$('.track').click(function(){
//    var trackingNumber = $('.trackingNumber').val();
//});

function formatDate(d) {

    var dd = d.getDate();
    if ( dd < 10 ) dd = '0' + dd;

    var mm = d.getMonth()+1;
    if ( mm < 10 ) mm = '0' + mm;

    var yy = d.getFullYear();
    //if ( yy < 10 ) yy = '0' + yy;

    return dd+'-'+mm+'-'+yy
}

function createOrderSpan(orderNumber){
    var childSpan = $('.orderSpan0').clone();
    childSpan.removeClass('orderSpan0').addClass('orderSpan' + orderNumber);
    childSpan.find('.order0').removeClass('order0').addClass('order' + orderNumber);
    childSpan.find('.service0').removeClass('service0').addClass('service' + orderNumber);
    childSpan.find('.status0').removeClass('status0').addClass('status' + orderNumber);
    childSpan.find('.price0').removeClass('price0').addClass('price' + orderNumber);
    childSpan.find('.date0').removeClass('date0').addClass('date' + orderNumber);
    return childSpan;
}

window.onbeforeunload = close;
function close(){
    if (!isAdmin) {
        $.ajax({
            type: 'POST',
            async: false,
            url: 'admin/userdash',
            success: function() {
                isAdmin = false;
            }
        });
    }
}

var isAdmin = false;
