<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Post</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <script type="text/javascript"><%@include file="/js/comment-validation.js"%></script>
  <style><%@include file="/css/post.css"%></style>

</head>
<body>

<h2>Post ${post.id}</h2>

<div id="admin">
  <c:if test="${isAdmin}">
    <form action="${pageContext.request.contextPath}/delete-post" method="post">
      <input type="hidden" name="postId" value="${post.id}">
      <input type="submit" value="Delete Post">
    </form>
  </c:if>
</div>



<p>${post.username}</p>
<hr>
<p>${post.title}</p>
<p>${post.postText}</p>
<br>

<!-- Форма для ввода комментария -->
<form id="comment-form" action="${pageContext.request.contextPath}/post/${post.id}" method="post">
  <label for="commentText">Your Comment:</label>
  <textarea id="commentText" name="commentText" rows="4" cols="50"></textarea>
  <br>
  <input type="submit" value="Submit Comment">
</form>

<!-- Отображение списка комментариев -->
<c:if test="${not empty post.comments}">
  <h3>Comments:</h3>
  <ul>
    <c:forEach var="comment" items="${post.comments}">
      <p>
          ${comment.username}: ${comment.text}
        <!-- Покажите кнопку удаления только администратору -->
        <c:if test="${isAdmin}">
          <form action="${pageContext.request.contextPath}/delete-comment" method="post">
            <input type="hidden" name="commentId" value="${comment.id}">
            <input type="hidden" name="postId" value="${post.id}">
            <input type="submit" value="Delete Comment">
          </form>
        </c:if>
      </p>
    </c:forEach>
  </ul>
</c:if>

</body>
</html>
