<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 03.05.2015
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="header.jsp"/>

<head>
    <style>

    </style>
</head>
<body class="container">

    <div id="tableDivOrder" class="panel panel-default">
        <div class="panel-heading">Order modification</div>

        <table cellspacing="0" id="OrderTable" class="table table-hover">
            <tr>
                <th scope="col">Tack number</th>
                <th scope="col">Status</th>
                <th scope="col">Price</th>
                <th scope="col">Service</th>
                <th scope="col">Car category</th>
                <th scope="col">Payment</th>
                <th scope="col">Sex of driver</th>
                <th scope="col">music</th>
                <th scope="col">animal</th>
                <th scope="col">wifi</th>
                <th scope="col">smoking driver</th>
                <th scope="col">conditioner</th>
                <th scope="col">comment</th>
            </tr>
        </table>
    </div>

    <div id="tableDivItems" class="panel panel-default">
        <div class="panel-heading">Order modification</div>

        <table cellspacing="0" id="itemsTable" class="table table-hover">
            <tr>
                <th scope="col">Item</th>
                <th scope="col">Path</th>
            </tr>
        </table>
    </div>
    <div class="center-block">
        <button id="modifyBtn" type="button" class="btn btn-success">MODIFY</button>
        <button id="refuseBtn" type="button" class="btn btn-danger">REFUSE</button>
    </div>
    <div class="panel-heading">Leave comment, please...
        <div class="panel panel-default"></div>
        <textarea id="textArea" class="form-control" rows="3"></textarea>
    </div>
    <jsp:include page="footer.jsp"/>

</body>
</html>