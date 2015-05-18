/**
 * Shows hidden block
 * of additional options in order.
 *
 * author Sharaban Sasha
 */
var visibleState = false;
$("#flip").click(function () {
    if(visibleState==false){
    $('#panel').slideDown("slow");
        visibleState=true;
    }else
    if(visibleState==true){
        $('#panel').slideUp("slow");
        visibleState=false;
    }
});




