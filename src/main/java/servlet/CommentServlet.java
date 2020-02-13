package servlet;

import servlet.entity.CommentDto;
import servlet.entity.UserDto;
import domain.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static domain.entity.EntityMapper.viewToComment;
import static domain.entity.EntityMapper.viewToUser;
import static domain.service.AuthenticationService.validateUser;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        UserDto user = (UserDto)session.getAttribute("user");

        String commentAtr = (String) request.getParameter("comment-add");
        String postAtr = (String) request.getParameter("post-id");


        System.out.println(commentAtr);
        System.out.println(postAtr);

        if (validateUser(viewToUser(user)) &&
                commentAtr != null) {

            CommentDto comment = new CommentDto();
            comment.setCommentText(commentAtr);
            comment.setPostId(postAtr);
            comment.setUsername(user.getUsername());

            PostService controller = new PostService();
            controller.addComment(viewToComment(comment));

        } else {
            System.out.println("WRONG");
        }
        response.sendRedirect("/post?post-id= " + (String) request.getParameter("post-id"));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}