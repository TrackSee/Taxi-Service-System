/**
 * Created by Олександр on 16.05.2015.
 */

var popDriverChart = AmCharts.makeChart( "popularDriverDiv", {
    "type": "pie",
    "theme": "light",
    "path": "http://www.amcharts.com/lib/3/",
    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
    "radius": "42%",
    "innerRadius": "60%",
    "titleField": "sex",
    "valueField": "ordersCount",
    "allLabels": [],
    "balloon": {},
    "titles": [],
    "dataProvider": [ {
        "sex": "Male",
        "ordersCount": 9899
    }, {
        "sex": "Female",
        "ordersCount": 4852
    }, {
        "sex": "Nevermind",
        "ordersCount": 5555
    } ],

    //"labelRadius": 5,
    //
    //"radius": "42%",
    //"innerRadius": "60%",
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