<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet Profile</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pet.css">
</head>
<body>
<h1>Pet Profile</h1>

<c:if test="${empty avatarData}">
    <img src="${pageContext.request.contextPath}/images/pet_base_avatar.jpg" alt="Аватар питомца">
</c:if>
<c:if test="${not empty avatarData}">
    <img src="data:image/jpeg;base64,${avatarData}" alt="Аватар питомца">
</c:if>

<p>Name: ${pet.name}</p>
<p>Description: ${pet.description}</p>

<form action="/petbook/pet/settings" method="get">
    <input type="submit" value="Settings">
</form>

<form action="/petbook/file-upload" method="get">
    <input type="submit" value="Add pet photo">
</form>

<c:if test="${not empty petPhotos}">
    <div class="photo-grid">
        <c:forEach items="${petPhotos}" var="photoData">
            <div class="photo-item">
                <img src="data:image/jpeg;base64,${photoData}" alt="Pet Photo">
            </div>
        </c:forEach>
    </div>
</c:if>


</body>
</html>