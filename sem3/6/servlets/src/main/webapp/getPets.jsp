<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.model.Pet" %>

<html>
<head>
    <title>Pets List</title>
</head>
<body>
    <h1>Pets List</h1>
    <table border="1">
        <tr>
            <th>Owner Name</th>
            <th>Name</th>
            <th>Height</th>
            <th>Weight</th>
            <th>Sex</th>
            <th>Color</th>
        </tr>
        <%
            List<Pet> pets = (List<Pet>) request.getAttribute("pets");
            for (Pet pet : pets) {
        %>
        <tr>
            <td><%= pet.getOwnerName() %></td>
            <td><%= pet.getPetName() %></td>
            <td><%= pet.getType() %></td>
            <td><%= pet.getAge() %></td>
            <td><%= pet.getSex() %></td>
            <td><%= pet.getColor() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>