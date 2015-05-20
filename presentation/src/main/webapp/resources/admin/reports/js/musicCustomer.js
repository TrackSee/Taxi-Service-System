/**
 * Created by Олександр on 14.05.2015.
 */
//Most popular music style
$(document).ready(function () {
    $('#getResult').click(function () {
        var userId = $('select[name=user]').val()
        $.ajax({
            type: 'POST',
            data: {userId: userId},
            url: 'music-user',
            success: function (data) {
                var driverChart = AmCharts.makeChart("musicCustomerDiv", {
                    "dataProvider": JSON.parse(data),
                    "valueField": "1",
                    "titleField": "0",
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
            }
        });
    });
});
