<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style><%@include file="/css/main-page-style.css"%></style>
</head>
<body>
<div id="navigation">
    <form action="${pageContext.request.contextPath}/main/posts" method="get">
        <button type="submit">POSTS</button>
    </form>

    <form action="${pageContext.request.contextPath}/main/photos" method="get">
        <button type="submit">PHOTOS</button>
    </form>

</div>

<div id="content">
    <div id="left-sidebar">
        <%@include file="user.jsp" %>
    </div>

    <div id="center-content">
        <%@include file="/views/feed-photos.jsp" %>
    </div>

    <div id="right-sidebar">
        <form action="${pageContext.request.contextPath}/user/settings" method="get">
            <input type="hidden" name="fromPhotos" value="true">
            <input type="submit" value="Settings">
        </form>
        <form action="${pageContext.request.contextPath}/sign-out" method="post">
            <input type="hidden" name="logout" value="true">
            <input type="submit" value="Log out">
        </form>
    </div>

</div>
</body>
</html>