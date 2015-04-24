<%--
  Created by IntelliJ IDEA.
  User: Igor Gula
  Date: 22.04.2015
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core"--%>
           <%--prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"--%>
           <%--prefix="fn" %>--%>
<html>

<jsp:include page="header.jsp"/>

<head>

  <jsp:include page="menu.jsp"/>

  <style>
    #tableEmailDiv {
      width: 100%;
    }
    #search {
      width: 20%;
    }
    .modal .modal-body {
      overflow-y: auto;
    }
  </style>

</head>
<body class="container">

<div class="form-group" id = "search">
  <input id="input1" type="text" class="form-control" placeholder="Search" oninput="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_GROUPS'), SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), pageNumber)">
</div>

<div id="tablediv" class="panel panel-default">
  <div class="panel-heading">Group modification</div>

  <table cellspacing="0" id="groupsTable" class="table table-hover">
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Count</th>
      <th scope="col">Edit</th>
    </tr>
  </table>

</div>

<nav>
  <ul class="pager">
    <li><button id = "nextPage" type="submit" class="btn btn-default" onclick="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_GROUPS'), SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), --pageNumber)">Previous</button></li>
    <li><button id = "previousPage" type="submit" class="btn btn-default" onclick="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_GROUPS'), SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), ++pageNumber)">Next</button></li>
  </ul>
</nav>

<!-- Large modal -->
<button id="addGroup" class="btn btn-primary" data-toggle="modal" data-target="#largeModal" onclick="editGroup('')" onshow="editGroup('')" >ADD GROUP</button>

<div id="largeModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <input id="inputEmail" type="text" class="form-control" placeholder="Search" oninput="manageGroup(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), '', '$('#inputEmail.val()')', 1)">
      </div>
      <div class="modal-body" id = "tableEmailDiv">
        <%--<p>Add the <code>.modal-lg</code> class on <code>.modal-dialog</code> to create this large modal.</p>--%>
          <div class="container">
          <div id="usersTablediv" class="panel panel-default">
            <div class="panel-heading">EDIT GROUP</div>

            <table cellspacing="0" id="usersTable" class="table table-hover">
              <tr>
                <th scope="col">ID</th>
                <th scope="col">EMAIL</th>
                <th scope="col">PHONE</th>
                <th scope="col">DRIVER</th>
                <th scope="col">ADMIN</th>
                <th scope="col">GROUP_NAME</th>
                <th scope="col">ACTION</th>
              </tr>
            </table>

          </div>
            </div>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">CANCEL</button>
        <button type="button" class="btn btn-primary" onclick="saveChanges()">SAVE</button>
      </div>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>

</html>
