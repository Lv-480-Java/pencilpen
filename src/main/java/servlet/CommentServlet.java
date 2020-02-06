package servlet;

import domain.logic.NewPostController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String passwordAtribute = (String) session.getAttribute("password");
        String usernameAtribute = (String) session.getAttribute("username");

        String commentAtr = (String) request.getParameter("comment-add");
        String postAtr = (String) request.getParameter("post-id");

        System.out.println(commentAtr);
        System.out.println(postAtr);

        if (passwordAtribute != null &&
            usernameAtribute != null &&
            commentAtr != null) {
                NewPostController controller = new NewPostController();
                controller.addComment(request, session);
        }else {
            System.out.println("WRONG");
        }
        response.sendRedirect("/post?post-id= "+(String) request.getParameter("post-id"));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}