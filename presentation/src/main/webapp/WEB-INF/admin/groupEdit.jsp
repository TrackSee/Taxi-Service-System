<%--
  Created by IntelliJ IDEA.
  User: Igor Gula
  Date: 22.04.2015
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">--%>
<%--<!-- Optional theme -->--%>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">--%>
<%--<!-- MetisMenu CSS -->--%>
<%--<link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"--%>
<%--rel="stylesheet">--%>
<!-- Bootstrap Core CSS -->
<link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
      rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
      rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css"
      rel="stylesheet" type="text/css">

<head>

    <style>
        /*#tableEmailDiv {*/
            /*width: 100%;*/
        /*}*/

        #search, #groupNameInput, #inputEmail, #groupRole {
            width: 20%;
        }

        .modal-dialog {
            width: auto;
        }
    </style>

</head>
<body>

<div id="wrapper" class="container">

    <jsp:include page="adminHeader.jsp"/>

    <div id="page-wrapper">

        <div class="form-group" id="search">
            <input id="input1" type="text" class="form-control" placeholder="Search"
                   oninput="getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_GROUPS'),
          SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), pageNumber)">
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
                <li>
                    <button id="nextPage" type="submit" class="btn btn-default"
                            onclick="getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_GROUPS'),
                 SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), --pageNumber)">PREVIOUS
                    </button>
                </li>
                <li>
                    <button id="previousPage" type="submit" class="btn btn-default"
                            onclick="getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_GROUPS'),
                 SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), ++pageNumber)">NEXT
                    </button>
                </li>
            </ul>
        </nav>

        <button id="addGroup" class="btn btn-primary" data-toggle="modal" data-target="#largeModal"
                onclick="onClickAddGroup()">
            ADD GROUP
        </button>

        <button id="removeGroups" class="btn btn-primary" onclick="removeGroups()">REMOVE</button>

        <div class="container">

            <div id="largeModal" class="modal fade bs-example-modal-lg" data-backdrop="static" data-keyboard="false"
                 role="dialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">

                            <div id="alert-danger" class="alert alert-danger alert-dismissible" role="alert"></div>

                            <h3 id="labelGroupName"></h3>

                            <input id="inputEmail" type="text" class="form-control" placeholder="Search"
                                   oninput="getUsersByInput()"/>

                            <input id="groupNameInput" type="text" class="form-control" placeholder="Name"/>

                            <select id="groupRole" class="form-control" data-style="btn-success">
                                <option value="registered_customer">customer</option>
                                <option value="administrator">administrator</option>
                                <option value="driver">driver</option>
                            </select>

                        </div>
                        <div id="panelUserTable" class="panel panel-default">
                            <div class="modal-body" id="tableEmailDiv">
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

                                <nav>
                                    <ul class="pager">
                                        <li>
                                            <button id="nextPageUsers" type="submit" class="btn btn-default"
                                                    onclick="getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, $('#inputEmail').val(), --pageNumber)">
                                                PREVIOUS
                                            </button>
                                        </li>
                                        <li>
                                            <button id="previousPageUsers" type="submit" class="btn btn-default"
                                                    onclick="getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, $('#inputEmail').val(), ++pageNumber)">
                                                NEXT
                                            </button>
                                        </li>
                                    </ul>
                                </nav>

                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="onCansel()">
                                    CANCEL
                                </button>
                                <button type="button" class="btn btn-primary" onclick="saveChanges()">SAVE</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap3/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/groupEdit.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>

</body>

</html>
