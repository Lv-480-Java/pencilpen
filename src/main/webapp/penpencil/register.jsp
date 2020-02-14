<!DOCTYPE html>
<html lang="en" dir="ltr" ng-app="myApp">

<head>
    <meta charset="utf-8">
    <title>Pen'n'Pencil</title>
    <link rel="stylesheet" type="text/css" href="penpencil/css/styles.css">
    <link rel="stylesheet" type="text/css" href="penpencil/css/menu.css">

    <link rel="stylesheet" type="text/css" href="penpencil/css/register.css">
</head>

<body>

<jsp:include page="parts/menu.jsp"/>


<div id="register">
    <form action="/register" method="post">
        <div class="container">
            <div><input id="register-email" class="input-field" type="text" placeholder="email" name="email" required>
            </div>
            <div><input id="register-username" class="input-field" type="text" placeholder="username" name="username"
                        required></div>
            <div><input id="register-password" class="password" type="password" placeholder="new password"
                        name="password" required></div>
            <div><input id="register-repeat-password" type="password" placeholder="repeat password"
                        name="password-repeat" required></div>
            <div id="happy-checkbox">
                <label class="checkbox-container">
                    <input type="checkbox" checked>
                    <span class="checkmark"></span>
                </label> Make me happy with mailing
            </div>
            <div id="register-wrapper">
                <button id="register-button" type="submit"><img height=58px
                                                                src="penpencil/ui/picture/registration/register_button.png"
                                                                alt="add"></button>
            </div>
        </div>
    </form>
</div>

</div>

<jsp:include page="parts/error.jsp"/>

</body>

</html>