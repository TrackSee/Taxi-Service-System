<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../parts/meta.jsp"%>

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
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                        <%--TODO add reference on DASHBOARD --%>
                    <td><button class="btn btn-default" value="${user.userId}" onclick="goToUserDash(this)" name="${user.email}"></button></td>
                        <%--reference on dashboard--%>
                    <td>${user.phone != null? user.phone : "-"}</td>
                    <td>${user.sex}</td>
                    <td>${user.groupName? user.groupName : "-"}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<%--for pagination--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/js/search.js"></script>--%>
<script src="<%=application.getContextPath()%>/resources/admin/js/searchCustomer.js"></script>
<%--<script src="<%=application.getContextPath()%>/resources/admin/js/changeId.js"></script>--%>


</body>

</html>
