$(document).ready(
    function() {

        // SUBMIT FORM
        $("#submitLogin").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                "username" : $("#username").val(),
                "password" : $("#password").val()


            }



            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/auth/user",
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


                     $.post("/auth/user", formData, function(res) {
                        window.localStorage.setItem('token',res.token)
                    });
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