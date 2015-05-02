/**
 * This script show hidden block
 * of additional options in order
 *
 * author Sharaban Sasha
 */

$(document).ready(function () {
    $("#flip").click(function () {
        $('#panel').slideDown("slow");
        $('#flip').hide();
    });
});