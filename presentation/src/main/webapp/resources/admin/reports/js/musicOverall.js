/**
 * Created by Олександр on 14.05.2015.
 */
//Most popular music style overall
$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: 'music-overall',
        success: function (data) {
            console.log(data);
            var driverChart = AmCharts.makeChart("musicOverallDiv", {
                "dataProvider": JSON.parse(data),
                "titleField": "style",
                "valueField": "order_count",
                "type": "pie",
                "theme": "light",
                "path": "http://www.amcharts.com/lib/3/",
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
            $('.amcharts-chart-div a').remove();
        }
    });
});