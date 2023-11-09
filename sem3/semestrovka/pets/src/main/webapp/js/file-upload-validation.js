document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('file-upload-form').addEventListener('submit', function (e) {
        const fileInput = document.getElementById('file');

        if (fileInput.files.length === 0) {
            e.preventDefault();
            alert('Add file.');
        }
    });
});