/**
 * Created by Олександр on 14.05.2015.
 */
//Most popular additional car options
var carChart = AmCharts.makeChart( "additionalOptCustomerDiv", {
    "type": "serial",
    "theme": "light",
    "path": "http://www.amcharts.com/lib/3/",
    "dataProvider": [ {
        "additional option": "Credit card",
        "ordersCount": 1500
    }, {
        "additional option": "Animal transportation",
        "ordersCount": 7500
    }, {
        "additional option": "Free Wi-Fi",
        "ordersCount": 3000
    }, {
        "additional option": "Non-smoking driver",
        "ordersCount": 5000
    }, {
        "additional option": "Air-conditioner",
        "ordersCount": 6100
    }],
    //"valueField": "ordersCount",
    //"titleField": "car category",
    //"balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
    "valueAxes": [ {
        "gridColor": "#FFFFFF",
        "gridAlpha": 0.2,
        "dashLength": 0
    } ],
    "gridAboveGraphs": true,
    "startDuration": 1,
    "graphs": [ {
        "balloonText": "[[category]]: <b>[[value]]</b>",
        "fillAlphas": 0.8,
        "lineAlpha": 0.2,
        "type": "column",
        "valueField": "ordersCount"
    } ],
    "chartCursor": {
        "categoryBalloonEnabled": false,
        "cursorAlpha": 0,
        "zoomable": false
    },
    "categoryField": "additional option",
    "categoryAxis": {
        "gridPosition": "start",
        "gridAlpha": 0,
        "labelRotation": 45,
        "tickPosition": "start",
        "tickLength": 20
    },
    "export": {
        "enabled": true,
        "libs": {
            "path": "http://www.amcharts.com/lib/3/plugins/export/libs/"
        }
        ,
        "menu": [{
            "format": "XLSX",
            "label": "Save as XLSX",
            "title": "Export chart to XLSX"
        }]
    }
});