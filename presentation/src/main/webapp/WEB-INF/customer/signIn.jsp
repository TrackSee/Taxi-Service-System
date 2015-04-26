<%-- Created by Ruslan Gunavardana.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../parts/head.jsp" %>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/bootstrap3/bootstrap.min.css">
    <link href="<%=application.getContextPath()%>/resources/customer/css/signin.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <form class="form-sign-in" action="<c:url value="/signin"/>" method="post">
        <h2 class="form-sign-in-heading">Please sign in</h2>
        <label for="email" class="sr-only">Email address</label>
        <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus
               maxlength="">

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    </form>
</div>

<%@include file="../parts/scripts-sign.jsp" %>
<script>$('.form-sign-in').validate();</script>
<c:if test="${param.error}">
    <script>$.notify('Sign up failed!', 'error');</script>
</c:if>
</body>
</html>
