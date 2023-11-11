document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('photo-upload-form').addEventListener('submit', function (e) {
        const fileInput = document.getElementById('photo');
        if (fileInput.files.length === 0) {
            e.preventDefault();
            alert('Add file.');
        }
    });
});