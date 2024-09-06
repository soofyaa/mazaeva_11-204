document.getElementById('logout-button').addEventListener('click', function() {
    fetch('/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').content
        },
        body: JSON.stringify({})
    }).then(response => {
        if (response.ok) {
            window.location.href = '/sign-in';
        } else {
            response.text().then(text => {
                console.error('Logout failed:', text);
                alert('Logout failed: ' + text);
            });
        }
    }).catch(error => {
        console.error('Logout failed:', error);
        alert('Logout failed: ' + error);
    });
});
