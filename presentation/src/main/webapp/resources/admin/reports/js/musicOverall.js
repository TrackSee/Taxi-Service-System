/**
 * Created by Олександр on 14.05.2015.
 */
//Most popular music style overall
var driverChart = AmCharts.makeChart( "musicOverallDiv", {
    "type": "pie",
    "theme": "light",
    "path": "http://www.amcharts.com/lib/3/",
    //"legend": {
    //    "align": "center",
    //    "markerType": "circle"
    //},
    "dataProvider": [ {
        "style": "Any",
        "ordersCount": 15000
    }, {
        "style": "Blues",
        "ordersCount": 10000
    }, {
        "style": "Classical music",
        "ordersCount": 10000
    }, {
        "style": "Rock",
        "ordersCount": 10000
    }, {
        "style": "Jazz",
        "ordersCount": 10000
    }, {
        "style": "Dance music",
        "ordersCount": 10000
    }, {
        "style": "Electronic music",
        "ordersCount": 10000
    }, {
        "style": "Hip Hop",
        "ordersCount": 10000
    }, {
        "style": "Other",
        "ordersCount": 25000
    }],
    "valueField": "ordersCount",
    "titleField": "style",
    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
    "labelText": "[[title]]",
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