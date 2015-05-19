/**
 * Created by kstes_000 on 06-May-15.
 */
$(document).ready(function() {
    $('#getResult').click(function(){
        var userId = $('select[name=user]').val()
        $.ajax({
            type: 'POST',
            data: {userId: userId},
            url: 'mostpopularopt',
            success: function(data){
                var dataArray = JSON.parse(data);
                var chart = AmCharts.makeChart( "chartdiv", {
                    "type": "pie",
                    "theme": "light",
                    "legend": {
                        "markerType": "circle",
                        "position": "right",
                        "marginRight": 80,
                        "autoMargins": false
                    },
                    "dataProvider": dataArray,
                    "valueField": "count",
                    "titleField": "additionalOptions",
                    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
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
            }
        });
    });
});
