<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${anime.title}</title>
    <link rel="stylesheet" href="/static/css/anime/anime-details.css">
    <link rel="stylesheet" href="/static/css/section/header.css">
    <script src="/static/js/modal.js" defer></script>
    <script src="/static/js/ajax-comment-anime.js" defer></script>

</head>
<body>
<input type="hidden" id="csrfToken" name="_csrf" value="${_csrf.token}" />
<#include "../section/header.ftlh">
<div class="main-container">
    <div class="container">

        <div class="anime-container">

            <h1>${anime.title}</h1>
            <div class="anime-details">
                <#if anime.poster??>
                    <div class="poster-container">
                        <img src="${anime.poster}" alt="Poster" class="poster"/>
                    </div>
                </#if>
                <div class="description-container">
                    <#if anime.description??>
                        <p>${anime.description}</p>
                    </#if>
                    <#if anime.minAge??>
                        <p><strong>Возрастной рейтинг:</strong> ${anime.minAge}</p>
                    </#if>
                    <#if anime.animeStatus??>
                        <p><strong>Статус:</strong> ${anime.animeStatus}</p>
                    </#if>
                    <#if anime.type??>
                        <p><strong>Тип:</strong> ${anime.type}</p>
                    </#if>
                    <#if anime.episodes??>
                        <p><strong>Эпизоды:</strong> ${anime.episodes}</p>
                    </#if>
                    <#if anime.year??>
                        <p><strong>Год выхода:</strong> ${anime.year?c}</p>
                    </#if>
                    <#if anime.views??>
                        <p><strong>Просмотры:</strong> ${anime.views}</p>
                    </#if>
                    <#if anime.season??>
                        <p><strong>Количество сезонов:</strong> ${anime.season}</p>
                    </#if>
                    <#if anime.original??>
                        <p><strong>Первоисточник:</strong> ${anime.original}</p>
                    </#if>
                    <#if anime.rating??>
                        <p><strong>Рейтинг:</strong> ${anime.rating?string("0.0")}</p>
                    </#if>
                    <#if anime.genres?? && (anime.genres?size > 0)>
                        <div>
                            <strong>Жанры:</strong>
                            <ul>
                                <#list anime.genres as genre>
                                    <li>${genre.title}</li>
                                </#list>
                            </ul>
                        </div>
                    </#if>
                    <#if anime.creators?? && (anime.creators?size > 0)>
                        <div>
                            <strong>Режиссеры:</strong>
                            <ul>
                                <#list anime.creators as creator>
                                    <li>${creator.title}</li>
                                </#list>
                            </ul>
                        </div>
                    </#if>
                    <#if anime.studios?? && (anime.studios?size > 0)>
                        <div>
                            <strong>Студии:</strong>
                            <ul>
                                <#list anime.studios as studio>
                                    <li>${studio.title}</li>
                                </#list>
                            </ul>
                        </div>
                    </#if>
                </div>
            </div>
        </div>

        <div class="comment-container">
            <h1>Комментарии</h1>
            <div class="comment-form">
                <#if isAuthenticated>
                    <form class="comment-input">
                        <h3>Добавить комментарий</h3>
                        <input type="hidden" name="animeId" value="${anime.id?c}"/>
                        <textarea name="content" placeholder="Напишите ваш комментарий тут :)" required></textarea>
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
                            <strong><a href="/user/${comment.userId}"> ${comment.username} </a></strong>
                            <p>${comment.content}</p>
                            <#if isAdmin>
                                  <form action="/admin/anime/${anime.id}/comment/${comment.id}" method="post">
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
    </div>

    <div class="button-container">
        <#if isAuthenticated>
            <form action="/feedback/like/${anime.id?c}" method="post" style="display: inline;">
                <button type="submit" class="feedback-button <#if anime.liked>selected</#if>" <#if anime.liked>disabled</#if>>НРАВИТСЯ</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>

            <form action="/feedback/dislike/${anime.id?c}" method="post" style="display: inline;">
                <button type="submit" class="feedback-button <#if anime.disliked>selected</#if>" <#if anime.disliked>disabled</#if>>НЕ НРАВИТСЯ</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>

            <button class="feedback-button" onclick="openModal()">ДОБАВИТЬ В СПИСОК</button>
            <div id="modal" class="modal">
                <div class="modal-content">
                    <span class="close" onclick="closeModal()">&times;</span>
                    <form action="/feedback/save/${anime.id?c}" method="post">
                        <label for="userList">Выберите список:</label>
                        <select name="userListId" id="userList" required>
                            <#list userLists as list>
                                <option value="${list.id?c}">${list.name}</option>
                            </#list>
                        </select>
                        <button type="submit" class="feedback-button">Добавить</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
            </div>
        <#else>
            <p>Чтобы добавить аниме в список, войдите или зарегистрируйтесь.</p>
            <a href="/sign-in">ВХОД</a>
            <a href="/sign-up">РЕГИСТРАЦИЯ</a>
        </#if>
    </div>
</div>
</body>
</html>
