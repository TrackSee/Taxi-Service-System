<%--
  Created by IntelliJ IDEA.
  User: Igor Gula
  Date: 22.04.2015
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <title>AJAX JsonArray Example</title>

  <!--        <link href='http://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>-->
  <script type="text/javascript"></script>>






  <link href="<%=application.getContextPath()%>/resources/css/bootstrap2/bootstrap.css" rel="stylesheet">
  <link href="<%=application.getContextPath()%>/resources/css/bootstrap2/bootstrap-responsive.css" rel="stylesheet">
  <link href="<%=application.getContextPath()%>/resources/css/style.css" rel="stylesheet">
  <link href="<%=application.getContextPath()%>/resources/css/corrections/position.css" rel="stylesheet">




  <script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <script src="../../resources/js/groupEdit.js"></script>

  <script>

    var pageNumber = 1;
    var pageSize = 10;
    var pageMaxNumber = 5;
    var userIds = [];
    var groupIds = [];

    function removeGroup(el) {
      el = el.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
      groupIds.push(el);
      manageGroup(el, "GroupActionServlet", pageSize, pageNumber, "removeGroup");
    }

    function getPageNum(pageSize1, pageMaxNumber1, pageNumber1) {
      if (pageMaxNumber1 <= pageNumber1*pageSize1) {
        if (pageMaxNumber1 = pageNumber1*pageSize1) {
          return pageNumber1;
        } else {
          return Math.floor(pageMaxNumber1/pageSize1) + 1;
        }
      } else {
        return pageNumber1;
      }
    }

    function manageGroup(event, servletName1, pageSize1, pageNumber1, action1) {
      if ((pageSize > 0) && (pageNumber > 0)) {
        pageNumber = getPageNum(pageSize, pageMaxNumber, pageNumber);
        $.post(servletName1, {pageSize: pageSize, pageNumber: 1, groupName : groupIds[0], action : action1}, function (responseJson) {
          alert("post");
          if (responseJson !== null) {
            $("#countrytable").find("tr:gt(0)").remove();
            var table1 = $("#countrytable");
            $.each(responseJson, function (key, value) {
              if (value['name'] != "") {
                var rowNew = $("<tr><td></td><td></td><td></td><td></td></tr>");
                rowNew.children().eq(0).text(value['name']);
                rowNew.children().eq(1).text(value['countUsers']);
                rowNew.children().eq(2).html('<div class="row"><div class="col-lg-6"><button class="btn btn-default" onclick = "opendialog()" type="button">Edit</button></div><!-- /.col-lg-6 -->');
                rowNew.children().eq(3).html('<div class="row"><div class="col-lg-6"><button class="btn btn-default" onclick="removeGroup(this)" type="button">Delete</button></div><!-- /.col-lg-6 -->');
                rowNew.appendTo(table1);
              } else {
                pageMaxNumber = value['countUsers'];
              }
            });
          }
        });
        $("#tablediv").show();
      } else {
        pageNumber = 1;
      }
      alert("namege group");
    }

    function loadGroups(event, servletName1, pageSize1, pageNumber1, action1) {
//      alert(pageNumber1 + "pageNumber1");
//      alert(pageNumber + "pageNumber");
//      alert(pageMaxNumber + "pageMaxNumber");
//      alert(pageMaxNumber/pageSize + "pageMaxNumber/pageSize");
      if ((pageSize > 0) && (pageNumber > 0)) {
      pageNumber1 = getPageNum(pageSize, pageMaxNumber, pageNumber);
//        alert(pageNumber1 + "pageNumber");
        $.get(servletName1, {pageSize: pageSize1, pageNumber: 1, groupName : $("#input1").val(), action : action1}, function (responseJson) {
          if (responseJson !== null) {
            $("#countrytable").find("tr:gt(0)").remove();
            var table1 = $("#countrytable");
            $.each(responseJson, function (key, value) {
              if (value['name'] != "") {
                var rowNew = $("<tr><td></td><td></td><td></td><td></td></tr>");
                rowNew.children().eq(0).text(value['name']);
                rowNew.children().eq(1).text(value['countUsers']);
                rowNew.children().eq(2).html('<div class="row"><div class="col-lg-6"><button class="btn btn-default" type="button">Edit</button></div><!-- /.col-lg-6 -->');
                rowNew.children().eq(3).html('<div class="row"><div class="col-lg-6"><button class="btn btn-default" onclick="removeGroup(this)" type="button">Delete</button></div><!-- /.col-lg-6 -->');
                rowNew.appendTo(table1);
              } else {
                pageMaxNumber = value['countUsers'];
              }
            });
          }
        });
        $("#tablediv").show();
      } else {
        pageNumber = 1;
      }
    }

  </script>

  <script src="http://code.jquery.com/jquery-latest.min.js"></script>

  <style>
    #search {
      width: 20%;
    }
  </style>

  <script>

    $(document).ready(function () {
      $("#tablediv").hide();
      loadGroups(event, "FindGroupsByNameServlet", pageSize, pageNumber);
    });

      $(document).ready(function () {
        $("#addGroup").click( function () {
          $("#myModal").modal('show');
        });
      });

  </script>

</head>
<body class="container">

<div class="form-group" id = "search">
  <input id="input1" type="text" class="form-control" placeholder="Search" oninput='loadGroups(event, "FindGroupsByNameServlet", pageSize, pageNumber)'>
</div>

<div id="tablediv" class="panel panel-default">
  <div class="panel-heading">Group modification</div>

  <table cellspacing="0" id="countrytable" class="table">
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Count</th>
      <th scope="col">Edit</th>
      <th scope="col">Delete</th>
    </tr>
  </table>

</div>

<nav>
  <ul class="pager">
    <li><button id = "nextPage" type="submit" class="btn btn-default" onclick='loadGroups(event, "FindGroupsByNameServlet", pageSize, --pageNumber)'>Previous</button></li>
    <li><button id = "previousPage" type="submit" class="btn btn-default" onclick='loadGroups(event, "FindGroupsByNameServlet", pageSize, ++pageNumber)'>Next</button></li>
  </ul>
</nav>

<!-- Large modal -->
<button class="btn btn-primary" data-toggle="modal" data-target="#largeModal">ADD GROUP</button>

<div id="largeModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Large Modal</h4>
      </div>
      <div class="modal-body">
        <p>Add the <code>.modal-lg</code> class on <code>.modal-dialog</code> to create this large modal.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary">OK</button>
      </div>
    </div>
  </div>
</div>

</body>

</html>
