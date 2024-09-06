<!DOCTYPE html>
<html>
<head>
    <title>Чат</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.0/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
    <link rel="stylesheet" href="/static/css/chat/chat.css">
    <link rel="stylesheet" href="/static/css/section/header.css">
    <script src="/static/js/chat.js"></script>
</head>
<body>

<#include "../section/header.ftlh">

<div id="chat">
    <div id="messageArea"></div>
    <div id="inputArea">
    <input type="text" id="messageInput" placeholder="Введите сообщение тут" />
    <button onclick="sendMessage()">ОТПРАВИТЬ</button>
</div>
</div>
</body>
</html>
