<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Настройки пользователя</title>
    <link rel="stylesheet" href="/static/css/section/header.css">
    <link rel="stylesheet" href="/static/css/user/user-settings.css">
</head>
<body>

<#include "../section/header.ftlh">

<div class="container">
    <h1>Настройки пользователя</h1>

    <!-- Форма для обновления имени пользователя -->
    <form action="/user/settings/updateName" method="post">
        <div>
            <label for="newName">Новое имя:</label>
            <input type="text" id="newName" name="newName" required>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button class="settings-button" type="submit">Обновить имя</button>
    </form>

    <!-- Форма для обновления аватарки пользователя -->
    <form action="/user/settings/updateAvatar" method="post" enctype="multipart/form-data">
        <div>
            <label for="avatar">Новый аватар:</label>
            <input type="file" id="avatar" name="avatar" accept="image/*" required>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button class="settings-button" type="submit">Обновить аватар</button>
    </form>
</div>

</body>
</html>
