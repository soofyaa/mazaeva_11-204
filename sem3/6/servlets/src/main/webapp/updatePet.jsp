<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.model.Pet" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Pet</title>
</head>
<body>
    <h1>Update Pet</h1>

    <form action="/servlets/updatePet" method="post">
        <label for="petNameToUpdate">Select Pet to Update:</label>
        <select name="petNameToUpdate" id="petNameToUpdate">
            <%
                List<Pet> pets = (List<Pet>) request.getAttribute("pets");
                for (Pet pet : pets) {
            %>
            <option value="<%= pet.getPetName() %>"><%= pet.getPetName() %></option>
            <%
                }
            %>
        </select>
        <br>
        <label for="updatedPetName">Updated Pet Name:</label>
        <input type="text" name="updatedPetName" id="updatedPetName">
        <br>
        <label for="updatedType">Updated Type:</label>
        <input type="text" name="updatedType" id="updatedType">
        <br>
        <label for "updatedAge">Updated Age:</label>
        <input type="text" name="updatedAge" id="updatedAge">
        <br>
        <label for="updatedSex">Updated Sex:</label>
        <input type="text" name="updatedSex" id="updatedSex">
        <br>
        <label for="updatedColor">Updated Color:</label>
        <input type="text" name="updatedColor" id="updatedColor">
        <br>
        <input type="submit" value="Update Pet">
    </form>
</body>
</html>