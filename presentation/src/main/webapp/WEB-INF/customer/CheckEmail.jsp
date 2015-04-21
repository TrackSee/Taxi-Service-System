<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <%@include file="../parts/head.jsp"%>
</head>
<body>

<div class="jumbotron">
    <div class="container">
        <h1>Sign up succeeded.</h1>

        <p>To approve your email address, follow the link in the email.</p>

        <p><a class="btn btn-primary btn-lg" href="<c:url value="/"/>" role="button">Home &raquo;</a></p>
    </div>
</div>

<div class="container">
    <%@include file="../parts/footer.jsp" %>
</div>

<%@include file="../parts/scripts.jsp" %>

</body>
</html>
