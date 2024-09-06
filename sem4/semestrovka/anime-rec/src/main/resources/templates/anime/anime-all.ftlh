<!DOCTYPE html>
<html>
<head>
    <title>Главная</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/anime/anime-all.css">
    <link rel="stylesheet" href="/static/css/section/header.css">

</head>
<body>

<#include "../section/header.ftlh">

<main>
    <div class="content-container">
        <div>
            <div class="anime-container">
                <#list animes as anime>
                    <div class="anime-item" data-id="${anime.id?c}">
                        <a href="/anime/${anime.id?c}">
                            <img src="${anime.poster}" alt="Anime Image">
                            <div class="anime-info">
                                <h3>${anime.title}</h3>
                                <p>
                                    ${anime.type}
                                    ${anime.score}
                                </p>
                                <span class="rating">
                                    ${anime.rating?string("0.0")}
                                </span>
                            </div>
                        </a>
                    </div>
                </#list>
            </div>
        </div>
        <div class="filter-container">
        </div>
    </div>
</main>
</body>
</html>
