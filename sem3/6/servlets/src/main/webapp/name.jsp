<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Name Page</title>
</head>
<body>
    <h1>Enter Owner Name</h1>
    <form action="/servlets/name" method="post">
    <input type="text" name="name" required>
    <input type="submit" value="Submit">
    </form>
</body>
</html>