<!DOCTYPE html>
<html lang="en" dir="ltr" ng-app="myApp">

<jsp:include page="pencilpen/parts/head.jsp" />

<body>

  <jsp:include page="pencilpen/parts/menu.jsp" />

  <div id="login">
    <form action="action_page.php" method="post">
      <div class="container">
        <div><input id="username" class="input-field" type="text" placeholder="email" name="username" required></div>
        <div><input id="password" type="password" placeholder="password" name="psw" required></div>
        <div id="login_wrapper"><button id="login-button" type="submit"><img height=50px
              src="ui/picture/login/login button.png" alt="add"></button></div>
        <div id="hrefs"><a href="">Register me</a><a href=""> Forgot password?</a></div>
      </div>
    </form>
  </div>

</body>

</html>