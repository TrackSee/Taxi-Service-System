<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <%@include file="WEB-INF/head.jsp"%>
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h1>Регистрация прошла успешно</h1>

        <p>Чтобы мы вас не потеряли, подтвердите свой адрес електронной почты,
            перейдя по ссылке, указаной в отправленном письме.</p>

        <p><a class="btn btn-primary btn-lg" href="<c:url value="/index.jsp"/>" role="button">Вернуться на главную &raquo;</a></p>
    </div>
</div>

<div class="container">
    <%@include file="WEB-INF/footer.jsp" %>
</div>

<%@include file="WEB-INF/scripts.jsp" %>

</body>
</html>
