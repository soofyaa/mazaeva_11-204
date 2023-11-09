<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Photos Feed</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <style><%@include file="/css/styles.css"%></style>
</head>
<body>

<h1>Photos Feed</h1>

<div class="file-grid">
  <c:forEach var="photo" items="${photos}">
    <div class="file-item">
      <div class="username-container">
        <p class="username"><strong>User Name:</strong> ${photo.username}</p>

        <c:if test="${isAdmin}">
          <form action="/petbook/delete-photo" method="post">
            <input type="hidden" name="photoId" value="${photo.id}">
            <input type="submit" value="Delete Photo">
          </form>
        </c:if>

      </div>
      <div>
        <img src="data:image/jpeg;base64,${photo.photoData}" alt="Photo">
        <hr/>
      </div>
    </div>
  </c:forEach>
</div>

</body>
</html>
