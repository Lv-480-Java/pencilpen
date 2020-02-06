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

    <jsp:include page="parts/menu.jsp" />
    <%@ page import="java.util.*"%>
    <%@ page import="domain.entity.*" %>

     <%
        List<Post> postList = (ArrayList<Post>) request.getAttribute("postList");
     %>

     <div id="find-wrapper">
        <form action="action_page.php" method="post">
          <div id="find-container">
            <input id="find-by-tag" class="input-field" type="text" placeholder="find by tag" name="tag" required>
            <button id="find-button" type="submit">F</button>
          </div>
        </form>
      </div>


     <% for(Post post: postList){ %>
     <a href="/post?post-id=<%= post.getId()%>">
        <div class="content-block">
              <div class="content-frame">
                <div class="username-div">
                  <p><%= post.getUsername() %></p>
                </div>
                  <div class="like-block">
                    <img height= 60px src="penpencil\ui\picture\content-block\like.png" alt="like">
                    <div class="like-div">
                      <p><%= post.getLikeList().size() %></p>
                    </div>
                  </div>
                  <div class="text-block">
                    <p><%= post.getPostText() %></p>
                  </div>
                  <div class="tag-container">
                    <div class="tag1">
                        <p><%= post.getTitle() %></p>
                    </div>
                  </div>
                </div>


                  <img class="image" height =90px src="data:image/png;base64, <%= post.getPicUrl() %>" alt="images">

            </div>
            </a>

    <% }
        %>

  </body>
</html>
