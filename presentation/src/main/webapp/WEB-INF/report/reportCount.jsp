<%--
  Created by IntelliJ IDEA.
  User: Oleksandr Kozin
  Date: 03.05.2015
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New orders per period</title>
</head>
<body>
<form method="post" action="reportCount">
    <h3>New orders per period:</h3>

    <h3>${requestScope.count}</h3>

    <h3>Service profitability by month:</h3>
    <ol>
        <c:forEach items="${requestScope.profit}" var="profit">
            <li>${profit.key} - ${profit.value}</li>
        </c:forEach>
    </ol>
    <a href="report">New Report</a>
</form>
</body>
</html>
