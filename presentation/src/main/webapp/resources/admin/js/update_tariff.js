/**
 * Created by Vitalii Diravka on 29.04.2015.
 */

function save(index, prefix)
    {
        //alert(index);
    var carCategory = document.getElementsByClassName("car")[index].innerHTML;
    var isWeekend = document.getElementById('ch_weeke'+index).checked;
    var isNightTariff = document.getElementById('ch_nt'+index).checked;
        $.ajax({
            type: 'POST',
            url : 'tariff',
            data : {
                newPriceVal : $('#'+prefix+index).val(),
                carCategory : carCategory,
                weekend : isWeekend,
                nightTariff : isNightTariff,
                priceType : prefix
            },
            success : function(responseText) {
                $('#text_'+prefix+index).val(responseText);
            }
            ,
            error: function (errMsg) {
                $('#text_'+prefix+index).val(errMsg);
            }
        });
}


