<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Post Feed</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

</head>
<body>

<c:forEach var="post" items="${posts}">
  <p><strong>User Name:</strong> ${post.username}</p>
  <p><strong>Title:</strong> ${post.title}</p>
  <p><strong>Post Text:</strong> ${post.postText}</p>
  <hr/>
</c:forEach>

</body>
</html>