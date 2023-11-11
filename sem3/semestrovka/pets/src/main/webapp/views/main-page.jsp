<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style><%@include file="/css/pages-style.css"%></style>
</head>
<body>
<div id="navigation">
</div>

<div id="content">
    <div id="left-sidebar">
    </div>

    <div id="center-content">
        <h1>Welcome to the Petbook!</h1>

        <form action="${pageContext.request.contextPath}/main/photos" method="get">
            <button type="submit">START</button>
        </form>
    </div>

    <div id="right-sidebar">
    </div>

</div>
</body>
</html>

