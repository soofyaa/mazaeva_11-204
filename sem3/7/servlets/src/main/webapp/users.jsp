<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Список пользователей</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>
<h1>Список пользователей</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Возраст</th>
        <th>Пол</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.surname}" /></td>
            <td><c:out value="${user.age}" /></td>
            <td><c:out value="${user.sex}" /></td>
            <td><c:out value="${user.email}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>