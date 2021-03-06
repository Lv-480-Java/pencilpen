<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" dir="ltr" ng-app="myApp">

<head>
    <meta charset="utf-8">
    <title>Pen'n'Pencil</title>
    <link rel="stylesheet" type="text/css" href="penpencil/css/styles.css">
    <link rel="stylesheet" type="text/css" href="penpencil/css/menu.css">
    <link rel="stylesheet" type="text/css" href="penpencil/css/login.css">
</head>

<body>

<jsp:include page="parts/menu.jsp"/>


<div id="login">
    <form action="/login" method="post">
        <div class="container">
            <div><input id="username" class="input-field" type="text" placeholder="email" name="username" required>
            </div>
            <div><input id="password" type="password" placeholder="password" name="password" required></div>
            <div id="login_wrapper">
                <button id="login-button" type="submit"><img height=50px
                                                             src="penpencil/ui/picture/login/login button.png"
                                                             alt="add"></button>
            </div>
            <div id="hrefs"><a href="/register">Register me</a><a href=""> Forgot password?</a></div>
        </div>
    </form>
</div>

<jsp:include page="parts/error.jsp"/>

</body>
</html>