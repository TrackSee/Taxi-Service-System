<%--
  Created by IntelliJ IDEA.
  User: kstes_000
  Date: 29-Apr-15
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Comment</title>
    <link href="<%=application.getContextPath()%>/resources/css/bootstrap2/bootstrap.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/css/bootstrap2/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>

<form class="form-inline" role="form">
    <div class="form-group">
        <input class="form-control" type="text" placeholder="Your comments" />
    </div>
    <div class="form-group">
        <button class="btn btn-default">Add</button>
    </div>
</form>

</body>
</html>
