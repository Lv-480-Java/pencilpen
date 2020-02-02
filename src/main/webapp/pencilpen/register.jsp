<!DOCTYPE html>
<html lang="en" dir="ltr" ng-app="myApp">
  <jsp:include page = "pencilpen/parts/head.jsp" />

<body>


  <jsp:include page = "pencilpen/parts/menu.jsp" />

  <%@ page import="domain.logic.*" %>

  <%
  if(request.getParameter("username")!=null){
  AuthenticationController authenticationController = new AuthenticationController();
  try{
    authenticationController.register(request);
  }catch (Exception e) {
  %>
<p>ERRORS MANY ERRORS<p>
  <%
  }
  };
  %>

  <div id="register">
    <form action="/register" method="get">
      <div class="container">
        <div><input id="register-email" class="input-field" type="text" placeholder="email" name="email" required>
        </div>

        <div><input id="register-username" class="input-field" type="text" placeholder="username" name="username"
            required></div>

        <div><input id="register-password" class="password" type="password" placeholder="new password" name="password"
            required></div>

        <div><input id="register-repeat-password" type="password" placeholder="repeat password" name="password-repeat" required>
        </div>

        <div id="happy-checkbox">
          <label class="checkbox-container">
            <input type="checkbox" checked>
            <span class="checkmark"></span>
          </label>
          Make me happy with mailing
        </div>

        <div id="register-wrapper"><button id="register-button" type="submit"><img height=58px
              src="pencilpen/ui/picture/registration/register_button.png" alt="add"></button></div>
      </div>
    </form>
  </div>
</body>
</html>
