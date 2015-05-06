
var chart = AmCharts.makeChart( "chartdiv", {
    "type": "pie",
    "theme": "light",
    "legend": {
        "markerType": "circle",
        "position": "right",
        "marginRight": 80,
        "autoMargins": false
    },
    "dataProvider": [ {
        "country": "Czech Republic",
        "litres": 256.9
    }, {
        "country": "Ireland",
        "litres": 131.1
    }, {
        "country": "Germany",
        "litres": 115.8
    }, {
        "country": "Australia",
        "litres": 109.9
    }, {
        "country": "Austria",
        "litres": 108.3
    }, {
        "country": "UK",
        "litres": 65
    }, {
        "country": "Belgium",
        "litres": 40
    } ],
    "valueField": "litres",
    "titleField": "country",
    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
    "export": {
        "enabled": true,
        "libs": {
            "path": "http://www.amcharts.com/lib/3/plugins/export/libs/"
        }
    }
} );