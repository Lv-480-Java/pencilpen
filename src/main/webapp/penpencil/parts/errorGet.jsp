<%@ page contentType= "text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<c:set var="message" value='${text-result}' />
<c:if test="${message != 0}">
<div id="error-window-wrapper">
    <div id="error-window">
        <a href="#" onclick="document.getElementById('error-window-wrapper').style.display='none';return false;" id="close_popup">
            <p id="error-text">${message}</p>
        </a>
    </div>
</div>
</c:if>