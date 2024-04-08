<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Pet</title>
</head>
<body>
<h2>Create Pet</h2>
<form action="${pageContext.request.contextPath}/createPet" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="age">Age:</label>
    <input type="number" id="age" name="age" required><br><br>

    <label for="height">Height:</label>
    <input type="number" step="0.01" id="height" name="height" required><br><br>

    <label for="weight">Weight:</label>
    <input type="number" step="0.01" id="weight" name="weight" required><br><br>

    <label for="breed">Breed:</label>
    <input type="text" id="breed" name="breed" required><br><br>

    <label for="color">Color:</label>
    <input type="text" id="color" name="color" required><br><br>

    <input type="submit" value="Create">
</form>
</body>
</html>
