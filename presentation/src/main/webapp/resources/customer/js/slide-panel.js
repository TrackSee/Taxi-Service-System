/**
 * This script show hidden block
 * of additional options in order
 * and additional information in
 * order info.
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




