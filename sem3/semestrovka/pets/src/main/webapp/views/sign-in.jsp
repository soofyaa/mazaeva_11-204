<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<h1>Вход пользователя</h1>
<c:if test="${not empty errorMessage}">
    <div class="error-message">${errorMessage}</div>
</c:if>
<form action="/petbook/sign-in" method="POST">
    <label for="username">Имя пользователя:</label>
    <input id="username" type="text" name="username" required><br>

    <label for="password">Пароль:</label>
    <input id="password" type="password" name="password" required><br>

    <input type="submit" value="Войти">
</form>
</body>
</html>
