<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
<jsp:include page="parts/menu.jsp"/>
<%@ page import="java.util.*" %>
<%@ page import="servlet.entity.*" %>

<div id="find-wrapper">
    <form action="/gallery" method="get">
        <div id="find-container">
            <input id="find-by-tag" class="input-field" type="text" placeholder="find by tag" name="tag" required>
            <button id="find-button" type="submit">F</button>
        </div>
    </form>
</div>

<c:forEach var="post" items="${postList}">
    <div class="content-block">
        <div class="content-frame">
            <a href="/profile?username=${post.getUsername()}">
                <div class="username-div">
                    <p>
                            ${post.getUsername()}
                    </p>
                </div>
            </a>

            <a href="/post?post-id=${post.getId()}">
                <div class="like-block">
                    <img height=60px src="penpencil\ui\picture\content-block\like.png" alt="like">
                    <div class="like-div">
                        <p>
                                ${post.getLikeList().size()}
                        </p>
                    </div>
                </div>
            </a>

            <a href="/post?post-id=${post.getId()}">
                <div class="text-block">
                    <p>
                            ${post.getPostText()}
                    </p>
                </div>

            </a>

            <div class="tag-container">
                <div class="tag1">
                    <p>
                            ${post.getTag()}
                    </p>
                </div>
            </div>
        </div>

        <a href="/post?post-id=${post.getId()}">
            <img id ="imag" style="visibility: hidden;" class="image" height=500px width=500px src="data:image/png;base64, ${post.getPicUrl()}" alt="images">
            <canvas id="can"  class="canvas" width="600" height="180px"></canvas>

        </a>
    </div>
    </a>
</c:forEach>
<script type="text/javascript" src="penpencil/scripts/crop.js"></script>
</body>
</html>