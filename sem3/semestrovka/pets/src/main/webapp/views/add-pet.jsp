<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Добавить питомца</title>
</head>
<body>
<h1>Добавить нового питомца</h1>

<form action="/petbook/add-pet" method="post">
  <label for="petName">Имя питомца:</label>
  <input type="text" id="petName" name="petName" required>
  <br>
  <label for="petDescription">Описание:</label>
  <textarea id="petDescription" name="petDescription" rows="4" cols="50" required></textarea>
  <br>
  <input type="submit" value="Добавить">
</form>
</body>
</html>
