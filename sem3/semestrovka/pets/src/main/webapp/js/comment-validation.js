document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('comment-form').addEventListener('submit', function (e) {
        const commentText = document.getElementById('commentText').value;

        if (commentText === '') {
            e.preventDefault(); // Предотвратите отправку формы
            alert('Please enter a comment.');
        }
    });
});