<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Upload Photo</title>
  <script type="text/javascript"><%@include file="/js/photo-upload-validation.js"%></script>
  <style><%@include file="/css/settings.css"%></style>
</head>
<body>
<h1>Upload Photo</h1>
<form action="${pageContext.request.contextPath}/photo-upload" method="post" enctype="multipart/form-data" id="photo-upload-form">
  <label for="photo">Choose photo:</label>
  <input type="file" name="photo" id="photo">
  <br>
  <input type="submit" value="Upload">
</form>
</body>
</html>
