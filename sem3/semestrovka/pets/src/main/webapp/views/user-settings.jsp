<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Settings</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>

<body>

<h1>Настройки пользователя</h1>

<form action="/petbook/file-upload" method="post" enctype="multipart/form-data">
  <input type="hidden" name="avatar" value="true">
  <input type="hidden" name="cameFromUser" value="true">
  <label for="avatar">Аватар:</label>
  <input type="file" name="file" id="avatar"><br>
  <input type="submit" value="Сохранить фото">
</form>

<form action="/petbook/user/settings" method="post">
  <!-- Поле для добавления описания -->
  <label for="description">Описание:</label>
  <textarea name="description" id="description">${user.description}</textarea><br>
  <!-- Поле для отображения почты пользователя (не редактируемое) -->
  <label for="email">Почта:</label>
  <input type="text" name="email" id="email" value="${user.email}" readonly><br>
  <input type="submit" value="Сохранить">
</form>

</body>
</html>
