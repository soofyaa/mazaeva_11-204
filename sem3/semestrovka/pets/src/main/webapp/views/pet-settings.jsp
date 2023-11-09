<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet Settings</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <script type="text/javascript"><%@include file="/js/file-upload-validation.js"%></script>

</head>

<body>

<h1>Pet Settings</h1>

<form action="/petbook/file-upload" method="post" enctype="multipart/form-data" id="file-upload-form">
    <input type="hidden" name="avatar" value="true">
    <label for="file">Аватар:</label>
    <input type="file" name="file" id="file"><br>
    <input type="submit" value="Сохранить фото">
</form>

<form action="/petbook/pet/settings" method="post">
    <label for="name">Name:</label>
    <textarea name="name" id="name">${pet.name}</textarea><br>
    <!-- Поле для добавления описания -->
    <label for="description">Description:</label>
    <textarea name="description" id="description">${pet.description}</textarea><br>

    <input type="submit" value="Сохранить">
</form>

</body>
</html>
