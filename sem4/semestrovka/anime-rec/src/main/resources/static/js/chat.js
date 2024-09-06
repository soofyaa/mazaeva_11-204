var stompClient = null;

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/topic/history', function (message) {
            var messages = JSON.parse(message.body);
            messages.forEach(showMessage);
        });
        loadMessages();
    });
}

function loadMessages() {
    stompClient.send("/app/loadMessages", {}, {});
}

function sendMessage() {
    var messageContent = document.getElementById('messageInput').value;
    if (messageContent && stompClient) {
        var message = {
            content: messageContent,
            timestamp: new Date()
        };
        stompClient.send("/app/sendMessage", {}, JSON.stringify(message));
        document.getElementById('messageInput').value = '';
    }
}

function showMessage(message) {
    var messageArea = document.getElementById('messageArea');
    var messageElement = document.createElement('div');
    messageElement.classList.add('message');

    var avatarHTML = message.userAvatarBase64 ?
        `<img src="data:image/jpeg;base64,${message.userAvatarBase64}" alt="Avatar" class="avatar"/>` :
        `<img src="/static/images/default-avatar.jpg" alt="Avatar" class="avatar"/>`;

    messageElement.innerHTML = `
        <div class="avatar">
            ${avatarHTML}
        </div>
        <div class="message-content">
            <div class="username">
                <p>
                    <a href="/user/${message.userId}">${message.username}</a> 
                    <span class="timestamp">${formatDate(message.timestamp)}</span>
                </p>
            </div>
            <div class="text">${message.content}</div>
        </div>
    `;
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}



function formatDate(timestamp) {
    var date = new Date(timestamp);
    var hours = date.getHours().toString().padStart(2, '0'); // Добавляем ведущий ноль для однозначных чисел
    var minutes = date.getMinutes().toString().padStart(2, '0');
    return hours + ':' + minutes;
}



connect();

