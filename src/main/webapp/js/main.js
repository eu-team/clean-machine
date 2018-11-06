$(document).ready(
    function() {

        // SUBMIT FORM
        $("#submitForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();

        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                "name" : $("#name").val(),
                "username" : $("#username").val(),
                "email": $("#email").val(),
                "password" : $("#password").val(),
                "roleName" : $("#roleName").val()

            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/signup",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status === "success") {
                        $("#postResultDiv").html(
                            "" + result.data.name
                            + "Post Successfully! <br>"
                            + "---> Congrats !!" + "</p>");
                    } else {
                        $("#postResultDiv").html("<strong>error</strong>");
                    }
                    console.log(result);
                    window.location.replace("/view");

                },
                error : function(e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }
            });

        }

    })