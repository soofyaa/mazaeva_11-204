<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Post</title>
    <style><%@include file="/css/settings.css"%></style>
</head>
<body>
<h1>Add Your Post</h1>

<form action="${pageContext.request.contextPath}/add-post" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required>
    <br>
    <label for="postText">Post Text:</label>
    <textarea id="postText" name="postText" rows="4" cols="50" required></textarea>
    <br>
    <input type="submit" value="Add">
</form>
</body>
</html>
