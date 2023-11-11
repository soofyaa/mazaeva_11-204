<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Photos Feed</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<h1>Photos Feed</h1>

<div class="photo-grid">
  <c:forEach var="photo" items="${photos}">
    <div class="photo-item">
      <div class="petname-container">
        <a href="${pageContext.request.contextPath}/pet/${photo.petId}">${photo.petName}</a>

        <c:if test="${isAdmin}">
          <form action="${pageContext.request.contextPath}/delete-photo" method="post">
            <input type="hidden" name="photoId" value="${photo.id}">
            <input type="submit" class="delete-button" value="Delete Photo">
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
