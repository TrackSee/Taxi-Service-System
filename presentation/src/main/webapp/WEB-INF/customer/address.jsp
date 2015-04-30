<%--
  @author Oleksandr Kozin
  Date: 30.04.2015
  Time: 13:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer address</title>
</head>
<body>
  <h3>All addresses:</h3>(<a href="customer/address/add">Add</a>)
  <ol>
    <c:forEach items="${requestScope.allAddresses}" var="address">
      <li>
          ${address.name} - ${address.stringRepresentation}
        <a href="customer/address/add?edit=${address.name}">Edit</a>
            | <a href="customer/address/delete?id=${address.name}">Delete</a>
      </li>
    </c:forEach>
</ol>
</body>
</html>
