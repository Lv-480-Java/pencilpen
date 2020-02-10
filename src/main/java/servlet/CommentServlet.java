package servlet;

import domain.entity.Comment;
import domain.entity.UserRegistered;
import domain.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static domain.service.AuthenticationService.validate;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        UserRegistered user = (UserRegistered)session.getAttribute("user");

        String commentAtr = (String) request.getParameter("comment-add");
        String postAtr = (String) request.getParameter("post-id");

        Comment comment = new Comment();
        comment.setCommentText(commentAtr);
        comment.setPostId(postAtr);
        comment.setNickname(user.getUsername());

        System.out.println(commentAtr);
        System.out.println(postAtr);

        if (validate(user) &&
                commentAtr != null) {
            PostService controller = new PostService();
            controller.addComment(comment);
        } else {
            System.out.println("WRONG");
        }
        response.sendRedirect("/post?post-id= " + (String) request.getParameter("post-id"));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}