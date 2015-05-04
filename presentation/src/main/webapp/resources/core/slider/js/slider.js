/**
 * Created by Sasha on 06.04.2015.
 */

$(function () {

    $("#slider-range0").slider({
        range: true,
        min: 0,
        max: 100,
        values: [$("#com0").val(),$("#comSoc0").val()],
        slide: function (event, ui) {
            $("#commercial0").val(ui.values[0] + "%");
            $("#social0").val(ui.values[1] - ui.values[0] + "%");
            $("#information0").val(100 - ui.values[1] + "%");
            console.log(ui.values[1] + '%');
            $('#YourDiv0').css('width', 100 - ui.values[1] +'%');
        }
    }).append('<div id="YourDiv0" style="width: 50%"></div>');

    $("#commercial0").val($("#slider-range0").slider("values", 0) + "%");
    $("#social0").val($("#slider-range0").slider("values", 1) - $("#slider-range0").slider("values", 0) + "%");
    $("#information0").val(100 - $("#slider-range0").slider("values", 1) + "%");

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    $("#slider-range1").slider({
        range: true,
        min: 0,
        max: 100,
        values: [$("#com1").val(),$("#comSoc1").val()],
        slide: function (event, ui) {
            $("#commercial1").val(ui.values[0] + "%");
            $("#social1").val(ui.values[1] - ui.values[0] + "%");
            $("#information1").val(100 - ui.values[1] + "%");
            console.log(ui.values[1] + '%');
            $('#YourDiv1').css('width', 100 - ui.values[1] +'%');
        }
    }).append('<div id="YourDiv1" style="width: 50%"></div>');

    $("#commercial1").val($("#slider-range1").slider("values", 0) + "%");
    $("#social1").val($("#slider-range1").slider("values", 1) - $("#slider-range1").slider("values", 0) + "%");
    $("#information1").val(100 - $("#slider-range1").slider("values", 1) + "%");

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    $("#slider-range2").slider({
        range: true,
        min: 0,
        max: 100,
        values: [$("#com2").val(),$("#comSoc2").val()],
        slide: function (event, ui) {
            $("#commercial2").val(ui.values[0] + "%");
            $("#social2").val(ui.values[1] - ui.values[0] + "%");
            $("#information2").val(100 - ui.values[1] + "%");
            console.log(ui.values[1] + '%');
            $('#YourDiv2').css('width', 100 - ui.values[1] +'%');
        }
    }).append('<div id="YourDiv2" style="width: 50%"></div>');

    $("#commercial2").val($("#slider-range2").slider("values", 0) + "%");
    $("#social2").val($("#slider-range2").slider("values", 1) - $("#slider-range2").slider("values", 0) + "%");
    $("#information2").val(100 - $("#slider-range2").slider("values", 1) + "%");


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    $("#slider-range3").slider({
        range: true,
        min: 0,
        max: 100,
        values: [$("#com3").val(),$("#comSoc3").val()],
        slide: function (event, ui) {
            $("#commercial3").val(ui.values[0] + "%");
            $("#social3").val(ui.values[1] - ui.values[0] + "%");
            $("#information3").val(100 - ui.values[1] + "%");
            console.log(ui.values[1] + '%');
            $('#YourDiv3').css('width', 100 - ui.values[1] +'%');
        }
    }).append('<div id="YourDiv3" style="width: 50%"></div>');

    $("#commercial3").val($("#slider-range3").slider("values", 0) + "%");
    $("#social3").val($("#slider-range3").slider("values", 1) - $("#slider-range3").slider("values", 0) + "%");
    $("#information3").val(100 - $("#slider-range3").slider("values", 1) + "%");

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    $("#slider-range4").slider({
        range: true,
        min: 0,
        max: 100,
        values: [$("#com4").val(),$("#comSoc4").val()],
        slide: function (event, ui) {
            $("#commercial4").val(ui.values[0] + "%");
            $("#social4").val(ui.values[1] - ui.values[0] + "%");
            $("#information4").val(100 - ui.values[1] + "%");
            console.log(ui.values[1] + '%');
            $('#YourDiv4').css('width', 100 - ui.values[1] +'%');
        }
    }).append('<div id="YourDiv4" style="width: 50%"></div>');

    $("#commercial4").val($("#slider-range4").slider("values", 0) + "%");
    $("#social4").val($("#slider-range4").slider("values", 1) - $("#slider-range4").slider("values", 0) + "%");
    $("#information4").val(100 - $("#slider-range4").slider("values", 1) + "%");


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    $("#slider-range5").slider({
        range: true,
        min: 0,
        max: 100,
        values: [$("#com5").val(),$("#comSoc5").val()],
        slide: function (event, ui) {
            $("#commercial5").val(ui.values[0] + "%");
            $("#social5").val(ui.values[1] - ui.values[0] + "%");
            $("#information5").val(100 - ui.values[1] + "%");
            console.log(ui.values[1] + '%');
            $('#YourDiv5').css('width', 100 - ui.values[1] +'%');
        }
    }).append('<div id="YourDiv5" style="width: 50%"></div>');

    $("#commercial5").val($("#slider-range5").slider("values", 0) + "%");
    $("#social5").val($("#slider-range5").slider("values", 1) - $("#slider-range5").slider("values", 0) + "%");
    $("#information5").val(100 - $("#slider-range5").slider("values", 1) + "%");



});