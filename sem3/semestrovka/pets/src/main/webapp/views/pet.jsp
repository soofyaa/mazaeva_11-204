<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style><%@include file="/css/pet-styles.css"%></style>
</head>
<body>
<h1>Pet Profile</h1>

<c:if test="${empty pet.avatarData}">
    <img class="avatar" src="${pageContext.request.contextPath}/images/pet_base_avatar.jpg" alt="Аватар питомца">
</c:if>
<c:if test="${not empty pet.avatarData}">
    <img class="avatar" src="data:image/jpeg;base64,${pet.avatarData}" alt="Аватар питомца">
</c:if>

<p>Name: ${pet.name}</p>
<p>Owners:</p>
<c:forEach items="${pet.owners}" var="owner">
    <p>${owner}</p>
</c:forEach>
<p>Description: ${pet.description}</p>

<c:if test="${not empty pet.photosData}">
    <div class="photo-grid">
        <c:forEach items="${pet.photosData}" var="photoData">
            <div class="photo-item">
                <img src="data:image/jpeg;base64,${photoData}" alt="Pet Photo">
            </div>
        </c:forEach>
    </div>
</c:if>

</body>
</html>