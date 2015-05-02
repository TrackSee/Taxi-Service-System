/**
 * Created by Slava on 13.04.2015.
 */
$(document).ready(function () {
    $("#saveBt").click(function() {
        $("#form").attr('action', "/root/user-submit").submit();
    });

    $("#deleteBt").click(function() {
        $("#form").attr('action', "/root/user-delete").submit();
    });

    $("#createBt").click(function() {
        $("#form").attr('action', "/root/user-create").submit();
    });
});