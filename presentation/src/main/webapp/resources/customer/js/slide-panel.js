/**
 * This script show hidden block
 * of additional options in order
 * and additional information in
 * order info.
 *
 * author Sharaban Sasha
 */

//if turn status true - panel is hidden
var hiddenStatus = true;
$('.turnButton').click(function(){
    if(hiddenStatus == false) {
        $('#panel').css('display', 'none');
    } else {
        $('#panel').css('display', 'block');
    }
    hiddenStatus = !hiddenStatus;
});