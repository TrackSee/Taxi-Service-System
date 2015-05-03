/**
 * This script show hidden block
 * of additional options in order
 * and additional information in
 * order info.
 *
 * author Sharaban Sasha
 */

//if turn status true - panel is hidden
var turnStatus = true;
$(document).ready(function () {
    $('#panel').css('display', 'none');
});
$('.turnButton').click(function(){
    if(turnStatus == false) {
        $('#panel').css('display', 'none');
    } else {
        $('#panel').css('display', 'block');
    }
    turnStatus = !turnStatus;
});