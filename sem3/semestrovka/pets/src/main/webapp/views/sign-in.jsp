<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style><%@include file="/css/sign-up-in.css"%></style>

</head>
<body>

<c:if test="${not empty errorMessage}">
    <script type="text/javascript">
        alert("${errorMessage}");
    </script>
</c:if>
<form action="${pageContext.request.contextPath}/sign-in" method="post">
    <label for="username">Username:</label>
    <input id="username" type="text" name="username" required><br>

    <label for="password">Password:</label>
    <input id="password" type="password" name="password" required><br>

    <input type="submit" value="Sign In">
</form>
</body>
</html>
