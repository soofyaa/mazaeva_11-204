<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Pet Page</title>
</head>
<body>
    <h1>Create Your Pet</h1>
    <form action="/servlets/createPet" method="post">
    <input type="text" name="petName" placeholder="petName" required>
    <input type="text" name="type" placeholder="type" required>
    <input type="text" name="age" placeholder="age" required>
    <input type="text" name="sex" placeholder="sex" required>
    <input type="text" name="color" placeholder="color" required>
    <input type="submit" value="Create Pet">
    </form>
</body>
</html>