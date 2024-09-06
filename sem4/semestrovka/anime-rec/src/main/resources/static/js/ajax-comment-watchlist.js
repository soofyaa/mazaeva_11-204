document.addEventListener("DOMContentLoaded", function() {
    const commentForm = document.querySelector('.comment-input');
    const commentInput = commentForm.querySelector('textarea');
    const csrfToken = document.querySelector('input[name="_csrf"]').value;
    const watchlistId = document.querySelector('input[name="watchlistId"]').value;
    const commentContainer = document.querySelector('.comments-list');

    commentForm.addEventListener('submit', function(event) {
        event.preventDefault();

        console.log("Отправка запроса на сервер...");

        const content = commentInput.value;

        fetch('/watchlist/comment/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-CSRF-TOKEN': csrfToken
            },
            body: new URLSearchParams({
                'watchlistId': watchlistId,
                'content': content,
                '_csrf': csrfToken
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Создаем новый элемент комментария
                const newComment = document.createElement('div');
                newComment.classList.add('comment');

                var avatarHTML = data.userAvatarBase64 ?
                    `<img src="data:image/jpeg;base64,${data.userAvatarBase64}" alt="Avatar" class="avatar"/>` :
                    `<img src="/static/images/default-avatar.jpg" alt="Avatar" class="avatar"/>`;

                // Заполняем данными комментария
                newComment.innerHTML = `
                    <div class="user-avatar-container">
                        ${avatarHTML}
                    </div>
                    <div class="comment-content">
                        <strong><a href="/user/${data.userId}"> ${data.username} </a></strong>
                        <p>${data.content}</p>
                    </div>
                `;

                commentContainer.prepend(newComment);
                commentInput.value = "";
            })
    });
});
