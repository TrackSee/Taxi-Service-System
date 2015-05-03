<%--
  Created by IntelliJ IDEA.
  User: Oleksandr Kozin
  Date: 03.05.2015
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New orders per period</title>
</head>
<body>
    <form method="post" action="reportCount">
        <h1>Result = ${requestScope.count}</h1>
        <a href="report">New Report</a>
    </form>
</body>
</html>
