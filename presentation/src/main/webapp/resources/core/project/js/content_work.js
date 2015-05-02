//$(document).ready(function() {
//    var availableTotal = 100;
//
//    $("#submit-btn").on("click", submit );
//
//    function submit() {
//        if (!check()) {
//            alert("Incorrect data");
//        }
//    }
//
//    function check() {
//        var result = true;
//        if (parseInt($("#z1-b").val()) + parseInt($("#z1-s").val()) + parseInt($("#z1-i").val()) == 100) {
//            $("#z1").addClass("has-success");
//        } else {
//            $("#z1").addClass("has-error").removeClass("has-success");
//            result = false;
//        }
//        if (parseInt($("#z2-b").val()) + parseInt($("#z2-s").val()) + parseInt($("#z2-i").val()) == 100) {
//            $("#z2").addClass("has-success");
//        } else {
//            $("#z2").addClass("has-error").removeClass("has-success");
//            result = false;
//        }
//        if (parseInt($("#z3-b").val()) + parseInt($("#z3-s").val()) + parseInt($("#z3-i").val()) == 100) {
//            $("#z3").addClass("has-success");
//        } else {
//            $("#z3").addClass("has-error").removeClass("has-success");
//            result = false;
//        }if (parseInt($("#z4-b").val()) + parseInt($("#z4-s").val()) + parseInt($("#z4-i").val()) == 100) {
//            $("#z4").addClass("has-success");
//        } else {
//            $("#z4").addClass("has-error").removeClass("has-success");
//            result = false;
//        }if (parseInt($("#z5-b").val()) + parseInt($("#z5-s").val()) + parseInt($("#z5-i").val()) == 100) {
//            $("#z5").addClass("has-success");
//        } else {
//            $("#z5").addClass("has-error").removeClass("has-success");
//            result = false;
//        }if (parseInt($("#z6-b").val()) + parseInt($("#z6-s").val()) + parseInt($("#z6-i").val()) == 100) {
//            $("#z6").addClass("has-success");
//        } else {
//            $("#z6").addClass("has-error").removeClass("has-success");
//            result = false;
//        }
//        return result;
//
//    }
//});