/**
 * Created by Igor Gula on 03.05.2015.
 */
var ORDER_ATTRIBUTES = (function() {
    var private = {
        'TRACKING_NUMBER': 'trackingNumber',
        'STATUS': 'status',
        'PRICE': 'price',
        'SERVICE': 'service',
        'CAR_CATEGORY': 'carCategory',
        'PAYMENT': 'wayOfPayment',
        'SEX_DRIVER': 'driverSex',
        'MUSIC': 'musicStyle',
        'ANIMALS': 'animalTransportation',
        'WIFI': 'freeWifi',
        'SMOKING_DRIVER': 'smokingDriver',
        'CONDITIONER': 'airConditioner',
        'COMMENT': 'comment'
    };
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var ITEM_ATTRIBUTES = (function() {
    var private = {
        'ITEM_ID': 'taxiItemId',
        'PATH': 'path'
    };
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var TRACK_NUMBER = 1;

$("#refuseOrder").click(function() {
    $.post('RefuseOrderServlet', {trackNumber: TRACK_NUMBER}, function (responseJson) {

    });
});

$(function() {
    $.get('ModifyOrderServlet', {trackNumber: TRACK_NUMBER}, function(responseJson) {
        getOrder(responseJson);
    });
    $.get('OrderEntitiesServlet', {trackNumber: TRACK_NUMBER}, function(responseJson) {
        getItems(responseJson);
    });
});

function getOrder(responseJson) {
    if (responseJson != null) {
        $("#orderTable").find("tr:gt(0)").remove();
        var table2 = $("#orderTable");
        $.each(responseJson, function(key, value) {
            var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>" +
            "</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
            rowNew.children().eq(0).text(value[ORDER_ATTRIBUTES.get(TRACK_NUMBER)]);
            rowNew.children().eq(1).text(value[ORDER_ATTRIBUTES.get(STATUS)]);
            rowNew.children().eq(2).text(value[ORDER_ATTRIBUTES.get(PRICE)]);
            rowNew.children().eq(3).text(value[ORDER_ATTRIBUTES.get(SERVICE)]);
            rowNew.children().eq(4).text(value[ORDER_ATTRIBUTES.get(CAR_CATEGORY)]);
            rowNew.children().eq(5).text(value[ORDER_ATTRIBUTES.get(PAYMENT)]);
            rowNew.children().eq(6).text(value[ORDER_ATTRIBUTES.get(SEX_DRIVER)]);
            rowNew.children().eq(7).text(value[ORDER_ATTRIBUTES.get(MUSIC)]);
            rowNew.children().eq(8).text(value[ORDER_ATTRIBUTES.get(ANIMALS)]);
            rowNew.children().eq(9).text(value[ORDER_ATTRIBUTES.get(WIFI)]);
            rowNew.children().eq(10).text(value[ORDER_ATTRIBUTES.get(SMOKING_DRIVER)]);
            rowNew.children().eq(11).text(value[ORDER_ATTRIBUTES.get(CONDITIONER)]);
            var comment = value[ORDER_ATTRIBUTES.get(COMMENT)];
            rowNew.children().eq(12).text(comment);

            if ((comment != null) || (comment.length)) {
                $("#textArea").hide();
            }

            rowNew.appendTo(table2);
        });
    }
}

function getItems(responseJson) {
    if (responseJson != null) {
        $("#orderTable").find("tr:gt(0)").remove();
        var table2 = $("#orderTable");
        $.each(responseJson, function(key, value) {
            var rowNew = $("<tr><td></td><td></td></tr>");
            rowNew.children().eq(0).text(value[ITEM_ATTRIBUTES.get(ITEM_ID)]);
            rowNew.children().eq(1).text(value[ITEM_ATTRIBUTES.get(PATH)]);

            rowNew.appendTo(table2);
        });
    }
}