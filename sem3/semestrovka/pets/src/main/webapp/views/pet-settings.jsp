<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet Settings</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <script type="text/javascript"><%@include file="/js/photo-upload-validation.js" %></script>
    <style><%@include file="/css/settings.css"%></style>
</head>

<body>

<h1>Pet Settings</h1>

<form action="${pageContext.request.contextPath}/photo-upload" method="post" enctype="multipart/form-data" id="photo-upload-form">
    <input type="hidden" name="avatar" value="true">
    <label for="photo">Аватар:</label>
    <input type="file" name="photo" id="photo"><br>
    <input type="submit" value="Save photo">
</form>

<form action="${pageContext.request.contextPath}/pet-settings" method="post">
    <label for="name">Name:</label>
    <textarea name="name" id="name">${pet.name}</textarea><br>
    <label for="description">Description:</label>
    <textarea name="description" id="description">${pet.description}</textarea><br>

    <label for="username">Add owner:</label>
    <input type="text" name="username" id="username"><br>
    <input type="hidden" name="petId" value="${pet.id}"><br>

    <input type="submit" value="Save">
</form>
</body>
</html>
