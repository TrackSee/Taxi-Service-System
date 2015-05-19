/**
 * Created by Олександр on 16.05.2015.
 */

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: 'driver-sex',
        success: function (data) {
            console.log(data);
            var popDriverChart = AmCharts.makeChart("popularDriverDiv", {
                "dataProvider": JSON.parse(data),
                "titleField": "sex",
                "valueField": "order_count",
                "type": "pie",
                "theme": "light",
                "path": "http://www.amcharts.com/lib/3/",
                "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                "radius": "42%",
                "innerRadius": "60%",
                "allLabels": [],
                "balloon": {},
                "titles": [],
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
            $('.amcharts-chart-div a').remove();
        }
    });
});