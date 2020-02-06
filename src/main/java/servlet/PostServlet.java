package servlet;

import dao.Mapper;
import domain.entity.Comment;
import domain.entity.Post;
import domain.entity.User;
import domain.logic.NewPostController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/post")
public class PostServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String postId = (String) request.getParameter("post-id");

        if(postId!=null) {
            Mapper<Comment> commentMapper = new Mapper<>(Comment.class); //FIXME винести маппер на леєр пофіксити севрелт
            List<Comment> comments = commentMapper.getBy("postId",""+postId );
            NewPostController controller = new NewPostController();

            request.setAttribute("commentList", comments);
            request.setAttribute("post", controller.getPost(postId));

            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/post.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String passwordAtribute = (String) session.getAttribute("password");
        String usernameAtribute = (String) session.getAttribute("username");


        String commentAtr = (String) request.getAttribute("comment-add");
        if(passwordAtribute!=null &&
           usernameAtribute!=null &&
           commentAtr!=null){
                NewPostController controller = new NewPostController();
                controller.addComment(request, session);
        }
    }
}