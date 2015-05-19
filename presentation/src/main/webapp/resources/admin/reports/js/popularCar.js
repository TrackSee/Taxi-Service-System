/**
 * Created by Олександр on 16.05.2015.
 */

var popCarChart = AmCharts.makeChart( "popularCarDiv", {
    "type": "pie",
    "theme": "light",
    "path": "http://www.amcharts.com/lib/3/",
    "dataProvider": [ {
        "car category": "Business class",
        "ordersCount": 4852
    }, {
        "car category": "Economy class",
        "ordersCount": 5555
    }, {
        "car category": "Van",
        "ordersCount": 9899
    } ],
    "titleField": "car category",
    "valueField": "ordersCount",
    "labelRadius": 5,

    "radius": "42%",
    "innerRadius": "60%",
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
} );