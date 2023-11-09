<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Post</title>
</head>
<body>
<h1>Add Post</h1>

<form action="/petbook/add-post" method="post">
    <label for="title">Заголовок поста:</label>
    <input type="text" id="title" name="title" required>
    <br>
    <label for="postText">Содержание поста:</label>
    <textarea id="postText" name="postText" rows="4" cols="50" required></textarea>
    <br>
    <input type="submit" value="Добавить">
</form>
</body>
</html>
