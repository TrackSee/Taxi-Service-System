/*author Vadym Akymov*/

//for driver category report
var driverChart = AmCharts.makeChart( "driverDiv", {
    "type": "pie",
    "theme": "light",
    "legend": {
        "markerType": "circle",
        "position": "right",
        "marginRight": 80,
        "autoMargins": false
    },
    "dataProvider": [ {
        "sex": "Male",
        "ordersCount": 15000
    }, {
        "sex": "Female",
        "ordersCount": 10000
    }, {
        "sex": "Nevermind",
        "ordersCount": 25000
    }],
    "valueField": "ordersCount",
    "titleField": "sex",
    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
    "export": {
        "enabled": true,
        "libs": {
            "path": "http://www.amcharts.com/lib/3/plugins/export/libs/"
        }
    }
} );


//For car report
var carChart = AmCharts.makeChart( "carDiv", {
    "type": "pie",
    "theme": "light",
    "legend": {
        "markerType": "circle",
        "position": "right",
        "marginRight": 80,
        "autoMargins": false
    },
    "dataProvider": [ {
        "car category": "Business class",
        "ordersCount": 15000
    }, {
        "car category": "Economy class",
        "ordersCount": 75000
    }, {
        "car category": "VAN",
        "ordersCount": 5000
    }, {
        "car category": "User car",
        "ordersCount": 19000
    }],
    "valueField": "ordersCount",
    "titleField": "car category",
    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
    "export": {
        "enabled": true,
        "libs": {
            "path": "http://www.amcharts.com/lib/3/plugins/export/libs/"
        }
    }
} );