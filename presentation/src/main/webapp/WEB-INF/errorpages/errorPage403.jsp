<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 04.05.15
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%response.setStatus(403);%>
<html>
<head>
    <title>403</title>
  <%--<script src="<%=application.getContextPath()%>/resources/error/js/redirect.js" type="application/javascript"/>--%>
</head>
<body>
<p>Error: 403 Forbidden</p>
<p>Тут должна быть прикольная страница или редирект на страницу аудентификации</p>
<%@include file="../parts/scripts.jsp" %>
</body>
</html>
