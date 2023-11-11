<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Post Feed</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <style><%@include file="/css/posts-styles.css"%></style>

</head>
<body>

<c:forEach var="post" items="${posts}">
  <div class="post-container">
    <p class="text-container">${post.username}</p>
    <hr/>
    <a href="${pageContext.request.contextPath}/post/${post.id}">${post.title}</a>
    <p class="text-container">${post.postText}</p>
  </div>
</c:forEach>

</body>
</html>
