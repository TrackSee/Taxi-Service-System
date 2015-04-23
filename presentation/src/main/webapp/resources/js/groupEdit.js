function loadGroups(event, servletName, pageSize1, pageNumber1, groupName1) {
    if ((pageSize1 > 0) && (pageNumber1 > 0)) {
        $.get(servletName, {pageSize: pageSize1, pageNumber: pageNumber1, groupName : groupName1}, function (responseJson) {
            if (responseJson !== null) {
                $("#countrytable").find("tr:gt(0)").remove();
                var table1 = $("#countrytable");
                $.each(responseJson, function (key, value) {
                    var rowNew = $("<tr><td></td><td></td><td></td><td></td></tr>");
                    rowNew.children().eq(0).text(value['name']);
                    rowNew.children().eq(1).text(value['countUser']);
                    rowNew.children().eq(2).html('<div class="row"><div class="col-lg-6"><button class="btn btn-default" onclick = "opendialog()" type="button">Edit</button></div><!-- /.col-lg-6 -->');
                    rowNew.children().eq(3).html('<div class="row"><div class="col-lg-6"><button class="btn btn-default" type="button">Delete</button></div><!-- /.col-lg-6 -->');
                    rowNew.appendTo(table1);
                });
            }
        });
        $("#tablediv").show();
    }
}