<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr" ng-app="myApp">

<head>
    <meta charset="utf-8">
    <title>Pen'n'Pencil</title>
    <link rel="stylesheet" type="text/css" href="penpencil/css/styles.css">
    <link rel="stylesheet" type="text/css" href="penpencil/css/menu.css">
    <link rel="stylesheet" type="text/css" href="penpencil/css/profile.css">

    <link rel="stylesheet" type="text/css" href="penpencil/css/form.css">
</head>

<body>


<jsp:include page="parts/menu.jsp"/>
<%@ page import="servlet.entity.PostDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static domain.service.LevelService.getUserLevel" %>
<%@ page import="java.util.List" %>


<%
    List<PostDto> postList = (ArrayList<PostDto>) request.getAttribute("postList");
%>


<div id="profile-deck">
    <div class="naming" style="
  display: flex;
  align-items: center;
  justify-content: center;">
        <p>
            <%= (String) request.getParameter("username") %>
        </p>
        <p style="color:#c83e74; margin-left: 50px"> Level: </p>
        <p style=" text-align: center"><%= getUserLevel((String) request.getParameter("username"))%>
        </p>
    </div>
</div>


<% for (PostDto post : postList) { %>

<a href="/post?post-id=<%=post.getId()%>">
    <div class="content-block">
        <div class="content-frame">
            <div class="username-div">
                <p>
                    <%= post.getUsername() %>
                </p>
            </div>
            <div class="like-block">
                <img height=60px src="penpencil\ui\picture\content-block\like.png" alt="like">
                <div class="like-div">
                    <p>
                        <%= post.getLikeList().size() %>
                    </p>
                </div>
            </div>
            <div class="text-block">
                <p>
                    <%= post.getPostText() %>
                </p>
            </div>
            <div class="tag-container">
                <div class="tag1">
                    <p>
                        <%= post.getTag() %>
                    </p>
                </div>
            </div>
        </div>
        <img class="image" height=90px src="data:image/png;base64, <%= post.getPicUrl() %>" alt="images">
        <canvas id="can" class="canvas" width="600" height="180px"></canvas>
    </div>
</a>

<% } %>

<jsp:include page="parts/error.jsp"/>
<script type="text/javascript" src="penpencil/scripts/crop.js"></script>
</body>

</html>