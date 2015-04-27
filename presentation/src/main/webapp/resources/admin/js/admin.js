/**
 * Created by Vadym_Akymov on 25.04.15.
 */

var displayStatus = true;
//car choosing button
$('.carChooseButton').click(function(event){
    console.log(displayStatus);
    if(displayStatus){
        $('.carChoose').css('display', 'block');
        displayStatus = false;
    } else {
        $('.carChoose').css('display', 'none');
        displayStatus = true;
    }
});