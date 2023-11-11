<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript"><%@include file="/js/email-validation.js" %></script>
    <style><%@include file="/css/sign-up-in.css"%></style>


</head>
<body>

<c:if test="${not empty errorMessage}">
    <script type="text/javascript">
        alert("${errorMessage}");
    </script>
</c:if>
<form action="${pageContext.request.contextPath}/sign-up" method="post" onsubmit="return emailValidation()">
    <label for="username">Username:</label>
    <input id="username" type="text" name="username" required><br>

    <label for="password">Password:</label>
    <input id="password" type="password" name="password" required><br>

    <label for="email">Email:</label>
    <input id="email" type="email" name="email" required><br>

    <input type="submit" value="Sign Up">

    <a href="${pageContext.request.contextPath}/sign-in">Already registered? Sign in here</a>

</form>
</body>
</html>
