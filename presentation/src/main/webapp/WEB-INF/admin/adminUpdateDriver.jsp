<%--
  Created by IntelliJ IDEA.
  User: Katia Stetsiuk
  Date: 25-Apr-15
  Time: 23:46
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

    <!-- DataTables CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
          rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/datatables-responsive/css/dataTables.responsive.css"
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

    <form class="update-driver" id="updateItDriver" action="<c:url value="/admin/updatedriver"/>">
        <p class="form-sign-up-heading">Update Profile</p>

        <div class="form-group">
            <label for="email" class="sr-only">Email </label>
            <input type="email" placeholder="email" id="email" name="email" class="form-control"
                   value="${driver.email}">
        </div>
        <%--<label for="password" class="sr-only">Password</label>--%>
        <%--<input type="password" placeholder="password" id="password" name="password" class="form-control"--%>
        <%--value="${driver.password}">--%>

        <%--<label for="confirmpassword" class="sr-only">Password</label>--%>
        <%--<input type="password" placeholder="confirm password" id="confirmpassword" name="confirmpassword"--%>
        <%--class="form-control" value="${driver.password}">--%>
        <div class="form-group">
            <label for="phone" class="sr-only">Phone number</label>
            <input type="text" placeholder="phone number" id="phone" name="phone" class="form-control"
                   value="${driver.phone}">
        </div>
        <div class="form-group">
            <label for="driverLicense" class="sr-only">Driver License</label>
            <input type="text" id="driverLicense" name="driverLicense" class="form-control"
                   value="${driver.driverLicense}" placeholder="Driver License" required>
        </div>

        <div id="updateDriver">
            <button type="button" class="btn btn-primary">Update</button>
        </div>
    </form>
</div>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin//bower_components/jquery/dist/jquery.validate.min.js"></script>


<script src="<%=application.getContextPath()%>/resources/admin/js/updateForDriver.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/hsps.js"></script>

</body>
</html>
