<%--
  Created by Katia Stetsiuk
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
    <jsp:include page="adminHeader.jsp"/>
    <div id="page-wrapper">
        <div>
            <input type="text" id="search" name="search" class="form-control" placeholder="search">
         </div>
        <table class="table">
            <thead>
            <tr>
                <th>Email</th>
                <th>Phone</th>
                <th>Sex</th>
                <th>Group Name</th>
            </tr>
            </thead>
            <tbody id="table-body">
            <c:forEach items="${requestScope.drivers}" var="driver">
                <tr>
                        <%--TODO add reference on DASHBOARD --%>
                    <td><a href="driver/driver-profile?id=${driver.userId}">${driver.email}</a></td>
                    <td>${driver.phone != null? driver.phone : "-"}</td>
                    <td>${driver.sex}</td>
                    <td>${driver.groupName? driver.groupName : "-"}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
</div>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/rec.js"></script>
</body>
</html>
