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
    $('input.pricedOption').change(updatePrice);
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

function getAdditionalOptionsMultiplier() {
    var multiplier = 1;
    if ($('#animalTransportationCheckbox').is(":checked"))
        multiplier *= getAnimalTransportationMultiplier();
    return multiplier;
}

/**
 * updatePrice
 * Updates order price when user change route addresses
 */
function updatePrice() {
    var service = $('#service').val();
    //var isTimePriced = service == 'CELEBRATION_TAXI' && service == 'TAXI_FOR_LONG_TERM';
    //isTimePriced? priceEntity.pricePerMin :
    var priceEntity = getTaxiPriceEntity();
    var price = priceEntity.pricePerKm;

    var distance = getRoutesData().reduce(function(pv, cv) {return pv + cv.distance; }, 0);
    // taxi for very short distance has constant min price
    var businessDistance = (distance > getMinDistance()) ? distance : getMinDistance();
    var totalCost = price * businessDistance * getAdditionalOptionsMultiplier();
    $('#price').val("$ " + totalCost.toFixed(2));
}
    $('#amountOfHours').on('blur', function() {
        if($(this).val()<8){
            $(this).val(8);
        }
        if($(this).val()>1000){
            $(this).val(1000);
        }
    });
$('#amountOfMinutes').on('blur', function() {
    if($(this).val()<1){
        $(this).val(1);
    }
    if($(this).val()>60){
        $(this).val(60);
    }
});
$('#amountOfCars').on('blur', function() {
    if($(this).val()<5){
        $(this).val(5);
    }
    if($(this).val()>100){
        $(this).val(100);
    }
});