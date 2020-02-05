<%@ page contentType= "text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr" ng-app="myApp">
  <head>
    <meta charset="utf-8">
    <title>Pen'n'Pencil</title>
    <link rel="stylesheet" type="text/css" href="penpencil/css/styles.css">
    <link rel="stylesheet" type="text/css" href="penpencil/css/menu.css">
    <link rel="stylesheet" type="text/css" href="penpencil/css/form.css">
  </head>
  <body>

    <jsp:include page="penpencil/parts/menu.jsp" />
    <%@ page import="java.util.*"%>


    <div class="content-block">
      <div class="content-frame">
        <div class="username-div">
          <p>Usdsdasefesefme</p>
        </div>
          <div class="like-block">
            <img height= 60px src="penpencil\ui\picture\content-block\like.png" alt="like">
            <div class="like-div">
              <p>NaN</p>
            </div>
          </div>
          <div class="text-block">
            <p>some part of very interesting text for a filler</p>
          </div>
          <div class="tag-container">
            <div class="tag1">
                <p>very col tag fo a life</p>
            </div>
          </div>
        </div>
          <img class="image" height =90px src="penpencil/ui/picture/content-block/test-image.png" alt="images">
    </div>

    <div class="comment-wrapper">
    <div class="comment">
      <div class="comment-username"><p>boriskontent</p></div>
      <div class="comment-text"><p>f vmlasmp feboriskoery iomment-nteresting </p></div>
    </div>
    </div>

    <div class="comment-wrapper">
      <div class="comment">
        <div class="comment-username"><p>boriskontent</p></div>
        <div class="comment-text"><p>f vmlasmp feboriskoery iomment-nteresting </p></div>
      </div>
      </div>

      <div id="comment-add-wrapper">
        <form action="action_page.php" method="post">
          <div id="comment-add-container">
            <input id="comment-add" class="input-field" type="text" placeholder="Write comment" name="tag" required>
            <button id="comment-add-button" type="submit">F</button>
            </div>
         </form>
      </div>


      <div id="error-window-wrapper">
        <div id="error-window">
        <a href="#" onclick="document.getElementById('error-window-wrapper').style.display='none';return false;" id="close_popup"><p id="error-text">Very bad message make it beter</p></a>
        </div>
      </div>





  </body>
</html>
