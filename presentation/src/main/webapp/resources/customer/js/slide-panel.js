/**
 * This script show hidden block
 * of additional options in order
 * and additional information in
 * order info.
 *
 * author Sharaban Sasha
 */

$(document).ready(function () {
    $("#flip").click(function () {
        $('#panel').slideDown("slow");
        $('#flip').hide();
    });
});