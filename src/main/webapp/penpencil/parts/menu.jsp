<%@ page import="domain.entity.UserRegistered" %>

<%

        UserRegistered user =(UserRegistered) session.getAttribute("user");
        String auth;
        String name=null;
        if(user == null){
         auth = "penpencil/ui/picture/menu/login1.png";
        }else{
             name = user.getUsername();
         auth = "penpencil/ui/picture/menu/login.png";
        }

        %>
    <div id="menu">
        <div class='menu-div'>
          <a href='/draw'>
            <img height =90px src="penpencil/ui/picture/menu/add.png" alt="add">
          </a>
        </div>

        <div class='menu-div'>
          <a href='/gallery'>
            <img height =90px src="penpencil/ui/picture/menu/gallery.png" alt="add">
          </a>
        </div>

        <div class='menu-div'>
          <a href='/profile?username=<%=name%>'>
            <img height =90px src="penpencil/ui/picture/menu/profile.png" alt="add">
          </a>
        </div>

        <div class='menu-div'>
          <a href='/login'>
            <img height =90px src="<%= auth %>" alt="add">
          </a>
        </div>
    </div>