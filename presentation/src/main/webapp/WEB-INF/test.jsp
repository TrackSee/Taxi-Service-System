<%--
  Created by IntelliJ IDEA.
  User: kstes_000
  Date: 02-May-15
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<form class="update-car" id="updateItCar" action="<c:url value="/test"/>" method="post">
    <label for="searching" class="sr-only">Search</label>
    <input type="searching" id="searching" name="searching" class="form-control">
  <div>
        <button id="search" type="button" class="btn btn-primary">Create</button>
    </div>
</form>
<script language="javascript" type="text/javascript">
    //alert('This is what an alert message looks like.');

        alert("lol")
        var i = 3;
        $("#search").keyup(function () {
            i++;
            alert(i);
        });

 
</script>


</body>
</html>
