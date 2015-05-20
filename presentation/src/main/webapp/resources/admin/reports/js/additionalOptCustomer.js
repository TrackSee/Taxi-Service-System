/**
 * Created by Олександр on 14.05.2015.
 */
//Most popular additional car options
$(document).ready(function () {
    $('#getResult').click(function () {
        var userId = $('select[name=user]').val()
        $.ajax({
            type: 'POST',
            data: {userId: userId},
            url: 'options-user',
            success: function (data) {
                var carChart = AmCharts.makeChart("additionalOptCustomerDiv", {
                    "dataProvider": JSON.parse(data),
                    "type": "serial",
                    "theme": "light",
                    "path": "http://www.amcharts.com/lib/3/",
                    "valueAxes": [{
                        "gridColor": "#FFFFFF",
                        "gridAlpha": 0.2,
                        "dashLength": 0
                    }],
                    "gridAboveGraphs": true,
                    "startDuration": 1,
                    "graphs": [{
                        "balloonText": "[[category]]: <b>[[value]]</b>",
                        "fillAlphas": 0.8,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "valueField": "count"
                    }],
                    "chartCursor": {
                        "categoryBalloonEnabled": false,
                        "cursorAlpha": 0,
                        "zoomable": false
                    },
                    "categoryField": "additionalOptions",
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
            }
        });
    });
});
