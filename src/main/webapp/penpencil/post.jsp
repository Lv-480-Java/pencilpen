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

<c:set var="post" value="${post}"/>
<div class="post-block">
    <div class="post-frame">
        <img class="image-post" src="data:image/png;base64, ${ post.getPicUrl() }" alt="images">
        <div class="text-post">
            <p>
                ${post.getPostText()}
            </p>
        </div>
        <div class="username-post">
            <p>
                ${post.getUsername()}
            </p>
        </div>

        <a href="/post?post-id=${post.getId()}&like=true">
            <div class="like-div-post">
                <p id="likee">
                    ${post.getLikeList().size()}
                </p>
                <img height=60px src="penpencil\ui\picture\content-block\like.png" alt="like">
            </div>
        </a>

        <div class="tag-post">
            <p class="post-tag-text">
                ${post.getTag()}
            </p>
        </div>
    </div>
</div>


<c:forEach var="comment" items="${post.getCommentList()}">
    <div class="comment-wrapper">
        <div class="comment">
            <div class="comment-username">
                <p>
                        ${comment.getNickname()}
                </p>
            </div>
            <div class="comment-text">
                <p>
                        ${comment.getCommentText()}
                </p>
            </div>
        </div>
    </div>
</c:forEach>

<div id="comment-add-wrapper">
    <form action="/comment" method="get">
        <div id="comment-add-container">
            <input id="post-id" class="input-field" type="text" value="${post.getId()}" name="post-id" hidden>
            <input id="comment-add" class="input-field" type="text" placeholder="Write comment" name="comment-add"
                   required>
            <button id="comment-add-button" type="submit">F</button>
        </div>
    </form>
</div>

</body>

</html>