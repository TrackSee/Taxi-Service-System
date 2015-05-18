$(document).ready(function() {
    $('#getResult').click(function(){
        var startDate = $('input[name = startDate]', '#serviceProfit').val();
        var endDate = $('input[name = endDate]', '#serviceProfit').val();
        $.ajax({
            type: 'POST',
            data: {startDate: startDate, endDate: endDate},
            url: 'profitableservice',
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
                    "valueField": "price",
                    "titleField": "service",
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
