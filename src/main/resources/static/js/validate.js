<script>
    document.querySelector('form').addEventListener('submit', function(event) {
    // prevent form from submitting
    event.preventDefault();

    // check email
    var email = document.getElementById('username').value;
    if (!email.includes('@')) { // very basic validation
    document.getElementById('emailError').style.display = 'block';
} else {
    document.getElementById('emailError').style.display = 'none';
}

    // check password
    var password = document.getElementById('password').value;
    if (password.length < 8) { // very basic validation
    document.getElementById('passwordError').style.display = 'block';
} else {
    document.getElementById('passwordError').style.display = 'none';
}

    // if no errors, submit form
    if (email.includes('@') && password.length >= 8) {
    this.submit();
}
});
</script>
