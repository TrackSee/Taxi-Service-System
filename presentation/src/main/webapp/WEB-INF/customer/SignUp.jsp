<%--
  Created by Ruslan Gunavardana.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../parts/head.jsp"%>
    <link href="<%=application.getContextPath()%>/resources/css/signup.css" rel="stylesheet">
</head>

<body>


<div class="container">
    <form id="form-sign-up" class="form-sign-up" method="post" action="javascript:void(null);" onsubmit="sendForm()">
        <h2 class="form-sign-up-heading">Please sign up</h2>
        <label for="email" class="sr-only">Email address</label>
        <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus>

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        <label for="repeat-password" class="sr-only">Repeat password</label>
        <input type="password" id="repeat-password" name="repeat-password" class="form-control" placeholder="Repeat password" required>

        <label for="phone-number" class="sr-only">Phone number</label>
        <input type="text" id="phone-number" name="phone-number" class="form-control" placeholder="Phone number">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    </form>
</div>
<%@include file="../parts/scripts.jsp" %>
<script src="<%=application.getContextPath()%>/resources/js/custom/sign-up.js"></script>
</body>
</html>
