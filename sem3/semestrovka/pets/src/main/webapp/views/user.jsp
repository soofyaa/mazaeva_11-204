<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style><%@include file="/css/user.css"%></style>
</head>
<body>

<div class="profile-container">
    <h1>Profile</h1>

    <c:if test="${empty avatarData}">
        <img src="${pageContext.request.contextPath}/images/user_base_avatar.jpg" alt="Аватар пользователя">
    </c:if>
    <c:if test="${not empty avatarData}">
        <img src="data:image/jpeg;base64,${avatarData}" alt="Аватар пользователя">
    </c:if>

    <p>${user.username}</p>
    <p>${user.description}</p>

    <form action="${pageContext.request.contextPath}/add-pet" method="get">
        <input type="submit" value="Add pet">
    </form>

    <form action="${pageContext.request.contextPath}/add-post" method="get">
        <input type="submit" value="Add post">
    </form>

    <h2>Your Pets</h2>
    <ul>
        <c:forEach items="${userPets}" var="pet">
            <p>
                <a href="${pageContext.request.contextPath}/pet/${pet.id}">
                        ${pet.name}
                </a>
            </p>
        </c:forEach>
    </ul>
</div>

</body>
</html>
