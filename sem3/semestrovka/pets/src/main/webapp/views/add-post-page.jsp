<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User Profile</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <style><%@include file="/css/pages-style.css"%></style>
</head>
<body>
<div id="navigation">


</div>

<div id="content">
  <div id="left-sidebar">
    <form action="${pageContext.request.contextPath}/main/posts" method="get">
      <input type="submit" value="Main Page">
    </form>

  </div>

  <div id="center-content">

    <%@include file="/views/add-post.jsp" %>

  </div>

  <div id="right-sidebar">

  </div>

</div>
</body>
</html>