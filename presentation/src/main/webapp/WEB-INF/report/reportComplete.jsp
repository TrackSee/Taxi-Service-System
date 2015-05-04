<%--
  Created by IntelliJ IDEA.
  User: Oleksandr Kozin
  Date: 02.05.2015
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New orders per period</title>
</head>
<body>
    <form method="post" action="report">
      <label>Date from:
        <input type="text" name="startDate" />
      </label>  <br />
      <label>Date to:
        <input type="text" name="endDate" />
      </label>  <br />
      <input type="submit" value="Show" />
    </form>
</body>
</html>
