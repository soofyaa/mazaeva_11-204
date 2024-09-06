<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список</title>
    <link rel="stylesheet" href="/static/css/watchlist/watchlist-details.css">
    <link rel="stylesheet" href="/static/css/section/header.css">
    <script src="/static/js/ajax-comment-watchlist.js" defer></script>

</head>
<body>

<input type="hidden" id="csrfToken" name="_csrf" value="${_csrf.token}" />

<#include "../section/header.ftlh">

<div class="container">
    <div class="watchlist-header">
        <#if watchlist.posterBase64?? && (watchlist.posterBase64?length > 0)>
            <img src="data:image/png;base64,${watchlist.posterBase64}" alt="${watchlist.name} poster">
        </#if>
        <h1>${watchlist.name}</h1>
        <p> Автор: <a href="/user/${watchlist.userId?c}">${username}</a></p>
    </div>
    <div class="watchlist-details">
        <p>${watchlist.description}</p>
        <#if isAdmin>
            <form action="/admin/user/${watchlist.userId}/watchlist/${watchlist.id?c}" method="post">
                <input type="hidden" name="_method" value="delete">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="admin-button">Удалить список</button>
            </form>
        </#if>
    </div>
    <ul class="anime-list">
        <#list watchlist.animes as anime>
            <li class="anime-item">
                <div class="anime-details">
                    <#if anime.poster??>
                        <div class="poster-container">
                            <img src="${anime.poster}" alt="Poster" class="poster"/>
                        </div>
                    </#if>
                    <div class="description-container">
                        <h2 class="anime-title">${anime.title}</h2>
                        <#if anime.description??>
                            <p class="anime-description">${anime.description}</p>
                        </#if>
                    </div>
                </div>
            </li>
        </#list>
    </ul>
</div>

<div class="comment-container">
    <h1>Комментарии</h1>
    <div class="comment-form">
        <#if isAuthenticated>
            <form class="comment-input">
                <h3>Добавить комментарий</h3>
                <input type="hidden" name="watchlistId" value="${watchlist.id?c}"/>
                <textarea id="commentContent" name="content" placeholder="Напишите ваш комментарий тут :)" required></textarea>
                <input type="hidden" id="csrfToken" name="_csrf" value="${_csrf.token}" />
                <button type="submit">ОТПРАВИТЬ</button>
            </form>
        <#else>
            <h4>Чтобы писать комментарии, войдите или зарегистрируйтесь.</h4>
        </#if>
    </div>
    <div class="comments-list">
        <#list comments as comment>
            <div class="comment">
                <div class="user-avatar-container">
                    <#if comment.userAvatarBase64??>
                        <img src="data:image/jpeg;base64,${comment.userAvatarBase64}" alt="Avatar" class="avatar"/>
                    <#else>
                        <img src="/static/images/default-avatar.jpg" alt="Avatar" class="avatar"/>
                    </#if>
                </div>
                <div class="comment-content">
                    <strong><a href="/user/${comment.userId?c}"> ${comment.username} </a></strong>
                    <p>${comment.content}</p>
                    <#if isAdmin>
                        <form action="/admin/watchlist/${watchlist.id?c}/comment/${comment.id?c}" method="post">
                            <input type="hidden" name="_method" value="delete">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <button type="submit" class="admin-button">Удалить</button>
                        </form>
                    </#if>
                </div>
            </div>
        </#list>
    </div>
</div>

</body>
</html>