<%--
  Created by Ruslan Gunavardana.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@include file="../parts/head.jsp"%>
  <link href="<%=application.getContextPath()%>/resources/css/signin.css" rel="stylesheet">
</head>

<body>


<div class="container">
  <form class="form-signin" action="<c:url value="/signin"/>" method="post">
    <h2 class="form-signin-heading">Please sign up</h2>
    <label for="email" class="sr-only">Email address</label>
    <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus>

    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
  </form>
</div>
<%@include file="../parts/scripts.jsp" %>
<c:if test="${param.error}">
  <script>$.notify("Sign up failed!", "error");</script>
</c:if>
</body>
</html>
