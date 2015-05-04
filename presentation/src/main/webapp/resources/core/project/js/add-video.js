///**
// * Created by Vadym on 10.02.2015.
// */

$(document).ready(function () {
    $('#from').datetimepicker(
        {format: 'DD-MM-YYYY'}
    );
    $('#to').datetimepicker(
        {format: 'DD-MM-YYYY'}
    );
});


$(document).ready(function () {
    //called when key is pressed in textbox
    $("#videoAmount1").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg1").html("Только числа").show().fadeOut("slow");
            return false;
        }
    });
});

$(document).ready(function () {
    //called when key is pressed in textbox
    $("#videoAmount2").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg2").html("Только числа").show().fadeOut("slow");
            return false;
        }
    });
});

$(document).ready(function () {
    //called when key is pressed in textbox
    $("#videoAmount3").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg3").html("Только числа").show().fadeOut("slow");
            return false;
        }
    });
});

$(document).ready(function () {
    //called when key is pressed in textbox
    $("#videoAmount4").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg4").html("Только числа").show().fadeOut("slow");
            return false;
        }
    });
});

$(document).ready(function () {
    //called when key is pressed in textbox
    $("#videoAmount5").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg5").html("Только числа").show().fadeOut("slow");
            return false;
        }
    });
});

$(document).ready(function () {
    //called when key is pressed in textbox
    $("#videoAmount6").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg6").html("Только числа").show().fadeOut("slow");
            return false;
        }
    });
});


$(document).ready(function () {
    //called when key is pressed in textbox
    $("#length").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg7").html("Только числа").show().fadeOut("slow");
            return false;
        }
    });
});