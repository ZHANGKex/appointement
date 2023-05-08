function validateMyForm() {
    var fname = document.forms["form1"]["fname"].value;
    var lname = document.forms["form1"]["lname"].value;
    var birth = document.forms["form1"]["birth"].value;

    if (fname == null || fname == "") {
        alert("First name must be filled ");
        return false;
    }
    if (lname == null || lname == "") {
        alert("Last name must be filled");
        return false;
    }
    if (birth == null || birth == "") {
        alert("Birth day must be filled");
        return false;
    }
}
function toogleFM(){
    var sex = document.querySelector('input[name="sex"]:checked').value;
    if (sex == "Male") {
        document.getElementsByTagName("fieldset")[0].style.borderColor = "red";
        document.getElementsByTagName("fieldset")[1].style.borderColor = "red";
        document.getElementsByTagName("fieldset")[2].style.borderColor = "red";

        document.getElementsByTagName("legend")[0].style.color = "red";
        document.getElementsByTagName("legend")[1].style.color = "red";
        document.getElementsByTagName("legend")[2].style.color = "red";

        document.getElementById("fname").style.borderColor = "red";
        document.getElementById("lname").style.borderColor = "red";
        document.getElementById("birth").style.borderColor = "red";

        document.getElementById("send").style.borderColor = "red";

        document.getElementById("hello").innerHTML = "Hello Sir";
    }
    else if (sex == "Female") {
        document.getElementsByTagName("fieldset")[0].style.borderColor = "blue";
        document.getElementsByTagName("fieldset")[1].style.borderColor = "blue";
        document.getElementsByTagName("fieldset")[2].style.borderColor = "blue";

        document.getElementsByTagName("legend")[0].style.color = "blue";
        document.getElementsByTagName("legend")[1].style.color = "blue";
        document.getElementsByTagName("legend")[2].style.color = "blue";

        document.getElementById("fname").style.borderColor = "blue";
        document.getElementById("lname").style.borderColor = "blue";
        document.getElementById("birth").style.borderColor = "blue";

        document.getElementById("send").style.borderColor = "blue";

        document.getElementById("hello").innerHTML = "Hello Madame";
    }
}