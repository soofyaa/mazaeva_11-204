<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.model.Pet" %>

<!DOCTYPE html>
<html>
<head>
    <title>Remove Pet</title>
</head>
<body>
<h1>Remove Pet</h1>
<form method="post">
    <label for="petNameToRemove">Select Pet to Remove:</label>
    <select name="petNameToRemove" id="petNameToRemove">
        <% List<Pet> pets = (List<Pet>) request.getAttribute("pets"); %>
        <% for (Pet pet : pets) { %>
        <option value="<%= pet.getPetName() %>"><%= pet.getPetName() %></option>
        <% } %>
    </select>
    <br>
    <input type="submit" value="Remove Pet">
</form>
</body>
</html>