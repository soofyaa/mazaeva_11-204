<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Settings</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <script type="text/javascript"><%@include file="/js/photo-upload-validation.js"%></script>
  <style><%@include file="/css/settings.css"%></style>


</head>

<body>

<h1>User Settings</h1>

<form action="${pageContext.request.contextPath}/photo-upload" method="post" enctype="multipart/form-data" id="photo-upload-form">
  <input type="hidden" name="avatar" value="true">
  <input type="hidden" name="cameFromUser" value="true">
  <label for="photo">Avatar:</label>
  <input type="file" name="photo" id="photo"><br>
  <input type="submit" value="Save avatar">
</form>

<form action="${pageContext.request.contextPath}/user/settings" method="post">
  <label for="description">Description:</label>
  <textarea name="description" id="description">${user.description}</textarea><br>
  <label for="email">Email:</label>
  <input type="text" name="email" id="email" value="${user.email}" readonly><br><br>
  <input type="submit" value="Save">
</form>

</body>
</html>
