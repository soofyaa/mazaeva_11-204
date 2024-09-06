<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Создание списка</title>
    <link rel="stylesheet" href="/static/css/watchlist/watchlist-create.css">
    <link rel="stylesheet" href="/static/css/section/header.css">

</head>
<body>

<#include "../section/header.ftlh">

<div class="container">
    <h2>Создание списка</h2>

    <form action="/watchlist/create" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">Название:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="description">Описание:</label>
            <textarea id="description" name="description" rows="4" required></textarea>
        </div>
        <div>
            <label for="poster">Изображение постера:</label>
            <label class="custom-file-upload">
                <input type="file" id="poster" name="poster">
            </label>
        </div>
        <div>
            <button class="button-create" type="submit">Создать</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</div>
</body>
</html>