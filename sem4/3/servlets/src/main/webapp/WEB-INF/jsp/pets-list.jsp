<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pets List</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2>Pets List</h2>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Age</th>
        <th>Height</th>
        <th>Weight</th>
        <th>Breed</th>
        <th>Color</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="pet" items="${pets}">
        <tr>
            <td>${pet.name}</td>
            <td>${pet.age}</td>
            <td>${pet.height}</td>
            <td>${pet.weight}</td>
            <td>${pet.breed}</td>
            <td>${pet.color}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
