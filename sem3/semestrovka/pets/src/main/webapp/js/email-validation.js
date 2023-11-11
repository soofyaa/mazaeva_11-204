function emailValidation() {
    var emailInput = document.getElementById('email');
    var email = emailInput.value;

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(email)) {
        alert('Invalid email address.');
        return false;
    }

    return true;
}