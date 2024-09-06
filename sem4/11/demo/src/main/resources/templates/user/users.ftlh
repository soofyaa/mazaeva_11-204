<!DOCTYPE html>
<html>
<head>
    <title>Users List</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<h1>Список пользователей</h1>
<table>
    <thead>
    <tr>
        <th>Email</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Статус</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.state}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
