<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<h1>User Profile</h1>

<c:if test="${empty avatarData}">
    <img src="${pageContext.request.contextPath}/images/user_base_avatar.jpg" alt="Аватар пользователя">
</c:if>
<c:if test="${not empty avatarData}">
    <img src="data:image/jpeg;base64,${avatarData}" alt="Аватар пользователя">
</c:if>

<p>Name: ${user.username}</p>
<p>Description: ${user.description}</p>

<h2>Your Pets</h2>
<ul>
    <c:forEach items="${userPets}" var="pet">
        <li>
            <a href="/petbook/pet/${pet.id}">
                    ${pet.name}
            </a>
        </li>
    </c:forEach>
</ul>

<form action="/petbook/user/settings" method="get">
    <input type="submit" value="Settings">
</form>

<form action="/petbook/add-pet" method="get">
    <input type="submit" value="Add pet">
</form>

<form action="/petbook/add-post" method="get">
    <input type="submit" value="Add post">
</form>

<form action="/petbook/user" method="post">
    <input type="hidden" name="logout" value="true">
    <input type="submit" value="log out">
</form>


</body>
</html>