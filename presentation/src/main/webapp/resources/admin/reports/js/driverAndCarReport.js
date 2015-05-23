/*author Vadym Akymov*/


$(document).ready(function(){

    $.ajax({
        type: 'GET',
        url: 'driver-sex',
        success: function (data) {
            console.log(data);
            //for driver category report
            var driverChart = AmCharts.makeChart( "driverDiv", {
                "type": "pie",
                "theme": "light",
                "dataProvider": JSON.parse(data),
                //"legend": {
                //    "markerType": "circle",
                //    "position": "right",
                //    "marginRight": 80,
                //    "autoMargins": false
                //},
                "valueField": "order_count",
                "titleField": "sex",
                "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                "export": {
                    "enabled": true,
                    "libs": {
                        "path": "http://www.amcharts.com/lib/3/plugins/export/libs/"
                    }
                }
            });
            $('.amcharts-chart-div a').remove();
        }
    });

    $.ajax({
        type: 'GET',
        url: 'car-category',
        success: function (data) {
            console.log(data);
            //For car report
            var carChart = AmCharts.makeChart( "carDiv", {
                "type": "pie",
                "theme": "light",
                //"legend": {
                //    "markerType": "circle",
                //    "position": "right",
                //    "marginRight": 80,
                //    "autoMargins": false
                //},
                "dataProvider":JSON.parse(data),
                "valueField": "ordered_count",
                "titleField": "car_category",
                "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
                "export": {
                    "enabled": true,
                    "libs": {
                        "path": "http://www.amcharts.com/lib/3/plugins/export/libs/"
                    }
                }
            });
            $('.amcharts-chart-div a').remove();
        }
    });
});
