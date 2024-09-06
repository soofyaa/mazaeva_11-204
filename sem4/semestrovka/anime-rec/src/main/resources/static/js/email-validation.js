function validateForm() {
    var email = document.getElementById("email").value;
    var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    if (!emailRegex.test(email)) {
        document.getElementById("emailError").style.display = "inline";
        return false;
    } else {
        document.getElementById("emailError").style.display = "none";
        return true;
    }
}