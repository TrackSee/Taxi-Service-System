/**
 * Created by Ruslan Gunavardana
 */
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


$(document).ready(function(){

    /**
     * Order submit handler.
     */
    $('#order-form').submit(function() {
        var formData = $(this).serialize();
        formData += '&order=' + JSON.stringify({routes: getRoutesData()});
        $.ajax({
            type: 'POST',
            url: getContextPath() + 'order',
            data: formData,
            success: function (data) {
                if (data == "error") {
                    $.notify("Invalid data entered!", "error");
                } else {
                    //$('html').replaceData(data);
                    window.location.replace("/TaxiService/orderSuccess");
                    //window.location.href = 'orderInfo';
                }
            },
            error: function () {
                $.notify("Internal server error occurred.", "warn");
            }
        });
        return false;
    });

    $(".form_datetime").on('changeDate', updatePrice);
    $("#carCategory").change(updatePrice);
});

/**
 * getTaxiPricePerKm
 * @returns {number}
 */
function getTaxiPricePerKm() {
    var date = $(".form_datetime").datetimepicker('getDate');
    function isWeekEnd() { return date.toString().contains('Sun') || date.toString().contains('Sat'); }
    function isNight() { return date.getHours() > 22 || date.getHours() < 6; }

    return getPriceList().find(function(e){
        return $('#carCategory').val() == e.carCategory && isWeekEnd() ==  e.weekend && isNight() == e.nightTariff;
    }).pricePerKm;
}

/**
 * updatePrice
 * Updates order price when user change route addresses
 */
function updatePrice() {
    var distance = getRoutesData().reduce(function(pv, cv) {return pv + cv.distance; }, 0);
    // taxi for very short distance has constant min price
    var businessDistance = (distance > getMinDistance()) ? distance : getMinDistance();
    var price = getTaxiPricePerKm() * businessDistance;
    $('#price').val(price + " UAH");
}