<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Загрузка фото</title>
  <script type="text/javascript"><%@include file="/js/file-upload-validation.js"%></script>
</head>
<body>
<h1>Загрузка фото</h1>
<form action="/petbook/file-upload" method="post" enctype="multipart/form-data" id="file-upload-form">
  <label for="file">Выберите файл:</label>
  <input type="file" name="file" id="file">
  <br>
  <input type="submit" value="Загрузить">
</form>
</body>
</html>
