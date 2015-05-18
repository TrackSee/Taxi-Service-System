/**
 * Dynamically adds new
 * input field with close
 * hyperlink.
 *
 * @author Sharaban Sasha
 */

var addDiv = $('#addinput');
var i = $('#addinput p').size() + 1;
$('#buttonAddressOrigin').click(function(){
    $('<p><label> Address from</label>' +
    '<input type="text" id="p_new" size="40" name="p_new_' + i +'" value="" required>' +
    '<span class="red-star">★</span>' +
    '<a href="#" id="remNew">Close</a></p>').appendTo(addDiv);
    i++;
    return false;
});

/**
 * Provides removing of
 * any of created field
 * by clicking on close
 * hyperlink.
 *
 * @author Sharaban Sasha
 */
$('#remNew').live('click', function() {
    if( i > 2 ) {
        $(this).parents('p').remove();
        i--;
    }
    return false;
});