<%--
  Created by IntelliJ IDEA.
  User: Олександр
  Date: 30.04.2015
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление | Редактирование</title>
</head>
<body>
    <form action="customer/address/add" method="post">
      <label for="name">Введите имя:
        <input type="text" id="name" value="${user.name}" name="name" />
      </label>  <br />
      <label for="last-name">Введите фамилию:
        <input type="text" id="last-name" value="${user.lastName}" name="lastName" />
      </label>  <br />
      <label for="age">Введите возвраст:
        <input type="text" id="age" value="${user.age}" name="age" />
      </label>  <br />
      <input type="hidden" name="id" value="${user.id}" />
      <input type="submit" value="Сохранить" />
    </form>
</body>
</html>
