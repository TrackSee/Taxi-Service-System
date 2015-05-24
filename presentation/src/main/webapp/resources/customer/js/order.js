/**
 * @author Ruslan Gunavardana
 */
$(document).ready(function(){

    /**
     * Order submit handler.
     */
    $('#order-form').submit(function(){
        var formData = $(this).serialize();
        formData += '&order=' + JSON.stringify({routes: getRoutesData()});
        var req = $.post(getContextPath() + 'order', formData);
        req.done(function (data) {
            if (data == "error") {
                $.notify("Invalid data entered!", "error");
            } else {
                var newDoc = document.open("text/html", "replace");
                newDoc.write(data);
                newDoc.close();
            }
        });

        req.fail(function(){
                $.notify("Internal server error occurred.", "warn");
        });
    });

    $(".form_datetime").on('changeDate', updatePrice);
    $("#carCategory").change(updatePrice);
});

/**
 * getTaxiPricePerKm
 * @returns {number}
 */
function getTaxiPriceEntity() {
    var date = $(".form_datetime").datetimepicker('getDate');

    // price parameters
    var isWeekEnd = date.toString().indexOf('Sun') != -1 || date.toString().indexOf('Sat') != -1;
    var isNight   = date.getHours() > 22 || date.getHours() < 6;
    var carCategory = $('#carCategory').val();

    return getPriceList().filter(function(e){
        return carCategory == e.carCategory && isWeekEnd ==  e.weekend && isNight == e.nightTariff;
    })[0];
}

/**
 * updatePrice
 * Updates order price when user change route addresses
 */
function updatePrice() {
    var service = $('#service').val();
    var isTimePriced = service == 'CELEBRATION_TAXI' && service == 'TAXI_FOR_LONG_TERM';
    var priceEntity = getTaxiPriceEntity();
    var price = isTimePriced? priceEntity.pricePerMin : priceEntity.pricePerKm;

    var distance = getRoutesData().reduce(function(pv, cv) {return pv + cv.distance; }, 0);
    // taxi for very short distance has constant min price
    var businessDistance = (distance > getMinDistance()) ? distance : getMinDistance();
    var totalCost = price * businessDistance;
    $('#price').val("$ " + totalCost.toFixed(2));
}