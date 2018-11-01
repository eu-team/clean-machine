<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

    <%--<script>--%>
        <%--$('#submitForm').submit(function(e) {--%>
            <%--// reference to form object--%>
            <%--var form = this;--%>
            <%--// for stopping the default action of element--%>
            <%--e.preventDefault();--%>
            <%--// mapthat will hold form data--%>
            <%--var formData = {}--%>
            <%--//iterate over form elements--%>
            <%--$.each(this, function(i, v){--%>
                <%--var input = $(v);--%>
                <%--// populate form data as key-value pairs--%>
                <%--// with the name of input as key and its value as value--%>
                <%--formData[input.attr("name")] = input.val();--%>
            <%--});--%>
            <%--$.ajax({--%>
                <%--type: form.attr('method'), // method attribute of form--%>
                <%--url: form.attr('action'),  // action attribute of form--%>
                <%--dataType : 'json',--%>
                <%--// convert form data to json format--%>
                <%--data : JSON.stringify(formData),--%>
            <%--});--%>
        <%--});--%>

    <%--</script>--%>

</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Clean Machine</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
                <li><a href="about">About</a></li>
                <li><a href="login">Login</a></li>
                <li class="active"><a href="register">Register</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">


<div class="row">
    <div class="col-md-6 mx-auto">
        <div class="card">
            <div class="card-body">
                <h1 class="text-center pb-4 pt-3">
                    <span class="text-primary"><i class="fa fa-lock"></i> Clean machine</span> Register
                </h1>
                <form class="form-group" id ="submitForm"role="form" method="POST" action="/users">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" name="name" id="name" class ="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" name="email" id="email" class ="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Confirm Password</label>
                        <input type="password" name="password-confirmation" id="password-confirm" class="form-control" required>
                    </div>
                    <input type="submit" value="Register" class="btn btn-primary btn-block">
                    <%--<button type="submit" class="btn btn-success"><i class="fa fa-user-plus"></i> Register</button>--%>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</body>

</html>
