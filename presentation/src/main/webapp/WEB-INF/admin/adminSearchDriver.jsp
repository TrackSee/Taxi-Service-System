<%--
  Created by IntelliJ IDEA.
  User: kstes_000
  Date: 02-May-15
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">
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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
       <![endif]-->

</head>
<body>
<div id="wrapper">
    <jsp:include page="adminHeader.jsp"/>
</div>
<div class="container" style=" width : 500px; ">
    <form class="update-car" id="updateItCar" action="<c:url value="/test"/>" method="post">
    <label for="searching" class="sr-only">Search</label>
    <input type="searching" id="searching" name="searching" class="form-control">

    <div>
        <button id="search" type="button" class="btn btn-primary">Create</button>
    </div>
    </form>
    <div id="page-wrapper">
        <table class="table">
            <thead>
            <tr>
                <th>Email</th>
                <th>Phone</th>
            </tr>
            </thead>
            <tbody id="table-body">
            <c:forEach items="${requestScope.drivers}" var="driver">
                <tr>
                    <td><a href="driver?id=${driver.userId}">${driver.email}</a></td>
                    <td>${driver.phone != null? driver.phone : "-"}</td>
                  </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <ul class="pagination">
                <%--<li class="active"><a href="1">1</a></li>--%>
                <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
                    <li class="pageLi${i}"><a class="pageButton" href="#">${i}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>

<script src="<%=application.getContextPath()%>/resources/admin/js/pagination.js"></script>

</body>
</html>
