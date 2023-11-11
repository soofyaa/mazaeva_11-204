<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Pet</title>
  <style><%@include file="/css/settings.css"%></style>
</head>
<body>
<h1>Add Your Pet</h1>

<form action="${pageContext.request.contextPath}/add-pet" method="post">
  <label for="petName">Pet Name:</label>
  <input type="text" id="petName" name="petName" required>
  <br>
  <label for="petDescription">Description:</label>
  <textarea id="petDescription" name="petDescription" rows="4" cols="50" required></textarea>
  <br>
  <input type="submit" value="Add">
</form>
</body>
</html>
