<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>


    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss"/>
    <link href="${jstlCss}" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Clean Machine</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="view">Home</a></li>
                <li><a href="user">User</a></li>
                <li><a href="about">About</a></li>
                <li><a href="login">Login</a></li>
                <li class="active"><a href="signup">Signup</a></li>
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
                    <form class="form-group" id="submitForm" role="form" method="POST" action="/signup">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" name="name" id="name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="username">User Name</label>
                            <input type="text" name="username" id="username" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="roleName">Select Role:</label>
                            <select class="form-control" id="roleName">
                                <option>ROLE_CUSTOMER</option>
                                <option>ROLE_ADMINISTRATOR</option>
                                <option>ROLE_MAINTAINER</option>
                            </select>
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


<script type="text/javascript" src="js/main.js"></script>


</body>

</html>
