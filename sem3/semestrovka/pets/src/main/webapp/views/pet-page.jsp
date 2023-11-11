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
    <form action="${pageContext.request.contextPath}/main/photos" method="get">
      <input type="submit" value="Main Page">
    </form>

  </div>

  <div id="center-content">

    <%@include file="/views/pet.jsp" %>

  </div>

  <div id="right-sidebar">
    <c:if test="${isPetOwner}">
      <form action="${pageContext.request.contextPath}/pet-settings" method="get">
        <input type="hidden" name="petId" value="${pet.id}">
        <input type="submit" value="Settings">
      </form>
      <br>
      <form action="${pageContext.request.contextPath}/photo-upload" method="get">
        <input type="submit" value="Add pet photo">
      </form>
    </c:if>
  </div>

</div>
</body>
</html>