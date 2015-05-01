<%--
  Created by IntelliJ IDEA.
  User: Igor Gula
  Date: 22.04.2015
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<html>

<jsp:include page="header.jsp"/>

<head>

    <style>
        #tableEmailDiv {
            width: 100%;
        }

        #search, #groupNameInput, #inputEmail, #groupRole {
            width: 20%;
        }

        .modal-dialog{
            width:90%;
        }
    </style>

</head>
<body class="container">

<%--<jsp:include page="menu.jsp"/>--%>

<%--<div id="alert-danger-group-name" class="alert alert-danger alert-dismissible" role="alert"></div>--%>
<div id="wrapper">

<div class="form-group" id="search">
    <input id="input1" type="text" class="form-control" placeholder="Search"
           oninput="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_GROUPS'),
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
                    onclick="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_GROUPS'),
                 SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), --pageNumber)">PREVIOUS
            </button>
        </li>
        <li>
            <button id="previousPage" type="submit" class="btn btn-default"
                    onclick="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_GROUPS'),
                 SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), ++pageNumber)">NEXT
            </button>
        </li>
    </ul>
</nav>

<button id="addGroup" class="btn btn-primary" data-toggle="modal" data-target="#largeModal" onclick="onClickAddGroup()">
    ADD GROUP
</button>

<button id="removeGroups" class="btn btn-primary" onclick="removeGroups()">REMOVE</button>

<div id="largeModal" class="modal fade bs-example-modal-lg" data-backdrop="static" data-keyboard="false" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">

                <div id="alert-danger" class="alert alert-danger alert-dismissible" role="alert"></div>

                <h3 id="labelGroupName"></h3>

                <input id="inputEmail" type="text" class="form-control" placeholder="Search"
                       oninput="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, $('#inputEmail').val(), 1);"/>

                <input id="groupNameInput" type="text" class="form-control" placeholder="Name"/>

                <select id="groupRole" class="form-control" data-style="btn-success">
                    <option value="registered_customer">customer</option>
                    <option value="administrator">administrator</option>
                    <option value="driver">driver</option>
                </select>

            </div>
            <div class="panel panel-default">
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
                                        onclick="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, $('#inputEmail').val(), --pageNumber)">
                                    PREVIOUS
                                </button>
                            </li>
                            <li>
                                <button id="previousPageUsers" type="submit" class="btn btn-default"
                                        onclick="getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, $('#inputEmail').val(), ++pageNumber)">
                                    NEXT
                                </button>
                            </li>
                        </ul>
                    </nav>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="onCansel()">CANCEL
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveChanges()">SAVE</button>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
<jsp:include page="footer.jsp"/>

</body>

</html>
