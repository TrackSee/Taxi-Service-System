
/**
 * @author Sharaban Sasha
 */
$(document).ready(function(){
    var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
    var pricePerKmNone=$('#pricePerKmNone').val();
    var pricePerKmNight=$('#pricePerKmNight').val();
    var pricePerKmWeekend=$('#pricePerKmWeekend').val();
    var pricePerKmNightWeekend=$('#pricePerKmNightWeekend').val();
    var minPriceNone=$('#minPriceNone').val();
    var minPriceNight=$('#minPriceNight').val();
    var minPriceWeekend=$('#minPriceWeekend').val();
    var minPriceNightWeekend=$('#minPriceNightWeekend').val();
    var pricePerMinNone=$('#pricePerMinNone').val();
    var pricePerMinNight=$('#pricePerMinNight').val();
    var pricePerMinWeekend=$('#pricePerMinWeekend').val();
    var pricePerMinNightWeekend=$('#pricePerMinNightWeekend').val();
    var pricePerHourNone=$('#pricePerHourNone').val();
    var pricePerHourNight=$('#pricePerHourNight').val();
    var pricePerHourWeekend= $('#pricePerHourWeekend').val();
    var pricePerHourNightWeekend=$('#pricePerHourNightWeekend').val();

    var barChartData = {
        labels : ["price per km","minimal price(5km) ","price per min","price per hour"],
        datasets : [
            {
                fillColor : "rgba(30,144,255,0.5)",
                strokeColor : "rgba(220,220,220,0.8)",
                highlightFill: "rgba(30,144,255,0.75)",
                highlightStroke: "rgba(220,220,220,1)",
                data : [pricePerKmNone,pricePerKmNight,pricePerKmWeekend,pricePerKmNightWeekend]
            },
            {
                fillColor : "rgba(0,255,255,0.5)",
                strokeColor : "rgba(151,187,205,0.8)",
                highlightFill : "rgba(0,255,255,0.75)",
                highlightStroke : "rgba(151,187,205,1)",
                data : [minPriceNone,minPriceNight,minPriceWeekend,minPriceNightWeekend]
            },
            {
                fillColor : "rgba(129,21,133,0.5)",
                strokeColor : "rgba(151,187,205,0.8)",
                highlightFill : "rgba(129,21,133,0.75)",
                highlightStroke : "rgba(151,187,205,1)",
                data : [pricePerMinNone,pricePerMinNight ,pricePerMinWeekend,pricePerMinNightWeekend]
            },
            {
                fillColor : "rgba(255,127,0,0.5)",
                strokeColor : "rgba(151,187,205,0.8)",
                highlightFill : "rgba(255,127,0,0.75)",
                highlightStroke : "rgba(151,187,205,1)",
                data : [pricePerHourNone,pricePerHourNight,pricePerHourWeekend,pricePerHourNightWeekend]
            }
        ]

    }
    window.onload = function(){
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myBar = new Chart(ctx).Bar(barChartData, {
            responsive : true
        });
    }

});

