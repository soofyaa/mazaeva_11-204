<!DOCTYPE html>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" href="/static/css/user/user-profile.css">
    <link rel="stylesheet" href="/static/css/section/header.css">
    <script src="/static/js/logout.js" defer></script>
</head>
<body>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<#include "../section/header.ftlh">


<div class="main-container">
    <div class="container">
        <div class="profile-container">
            <#if user.avatarBase64??>
                <img src="data:image/jpeg;base64,${user.avatarBase64}" alt="Аватар пользователя" class="avatar">
            <#else>
                <img src="/static/images/default-avatar.jpg" alt="Аватар пользователя" class="avatar">
            </#if>

            <div class="profile-details">
                <p>${user.username}</p>
            </div>
        </div>

        <div class="watchlists">
            <#if watchlists?has_content>
                <div class="watchlists-grid">
                    <#list watchlists as watchlist>
                    <a href="/watchlist/${watchlist.id?c}">
                        <div class="user-list">
                                <img src="data:image/jpeg;base64, ${watchlist.posterBase64}" />
                                <p>${watchlist.name}</p>
                        </div>
                    </a>
                    </#list>
                </div>
            </#if>
        </div>
    </div>

    <div class="button-container">
        <#if isAuthenticated>
            <#if isCurrentUser>
                <form action="/watchlist/create" method="get">
                    <button class="profile-button">СОЗДАТЬ СПИСОК</button>
                </form>

                <form action="/user/settings" method="get">
                    <button type="submit" class="profile-button">НАСТРОЙКИ</button>
                </form>

                <button id="logout-button" class="profile-button">ВЫХОД</button>

            <#else>
                <img src="/static/images/catt.jpg">
            </#if>
        <#else>
            <p>Чтобы написать пользователю, войдите или зарегистрируйтесь.</p>
            <a href="/sign-in">ВХОД</a>
            <a href="/sign-up">РЕГИСТРАЦИЯ</a>
        </#if>

        <#if isAdmin>
            <form action="/admin/user/${user.id?c}/block" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="admin-button">Забанить</button>
            </form>

            <form action="/admin/user/${user.id?c}" method="post">
                <input type="hidden" name="_method" value="delete">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="admin-button">Удалить пользователя</button>
            </form>
        </#if>
    </div>
</div>
</body>
</html>
