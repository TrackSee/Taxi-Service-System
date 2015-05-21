<%--
  Created by IntelliJ IDEA.
  User: Katia Stetsiuk
  Date: 24-Apr-15
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>

<head>
    <%@include file="../parts/meta.jsp" %>

    <!-- Bootstrap Core CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/css/formStyle.css" rel="stylesheet">

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
</div>
<div id="page-wrapper">

    <form class="create-driver" id="createDriver" action="<c:url value="/admin/createdriver"/>" method="post">
        <h2 class="form-sign-up-heading">Please enter data</h2>

        <div class="form-group">
            <label for="email" class="sr-only">Email</label>
            <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required>
        </div>
        <div class="form-group">
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </div>
        <div class="form-group">
            <label for="confirmpassword" class="sr-only">Password</label>
            <input type="password" id="confirmpassword" name="confirmpassword" class="form-control"
                   placeholder="confirm password" required>
        </div>
        <div class="form-group">
            <label for="phone" class="sr-only">Phone number</label>
            <input type="text" id="phone" name="phone" class="form-control" placeholder="Phone" required>
        </div>
        <div class="form-group">
            <div>
                <label>Sex</label>
            </div>
            <select id="sex" class="sex form-control" name="sex">
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
            </select>
        </div>
        <div>
            <button id="addDriver" type="button" class="btn btn-primary">Create</button>
        </div>

    </form>
</div>

<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin//bower_components/jquery/dist/jquery.validate.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/createdriver.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/hsps.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>


</body>
</html>
