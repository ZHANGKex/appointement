$(document).ready(function (){
    $( "#send" ).click(function( event ) {
        event.preventDefault();
        var formData = $("#form").serialize();
        $.post("LoginServlet",formData , function(response, status) {
            if (response==1)
                alert("Correct");
            else
                alert("Login or Password not correct");
        });
    });
});

/* Result in HTML page */

$(document).ready(function() {
    $("#send").click(function(event){
        event.preventDefault();
        var formData = $("#form").serialize();
        $.post("LoginServlet",formData , function(response, status) {
            if (response==1)
                $('#logText').text("Success");
            else
                $('#logText').html('<font color = red>Login or Password not correct</font>');
        });
    });
});