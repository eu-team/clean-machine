$(document).ready(
    function () {

        // SUBMIT FORM
        $("#submitForm").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                "id": $("#id").val()
            }

            // DO POST
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/linkcard",
                data: JSON.stringify(formData),
                dataType: 'json',
                // headers: {
                //     "Authorization": "Bearer " + window.localStorage.getItem('token')
                // },
                success: function (result) {
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
                error: function (e) {
                    alert("Error!")
                    console.log("ERROR: card not linked ", e);
                }
            });

        }

    })