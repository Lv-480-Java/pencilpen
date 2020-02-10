package servlet;

import dao.Mapper;
import domain.entity.Comment;
import domain.entity.UserRegistered;
import domain.service.AuthenticationService;
import domain.service.PostService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static domain.service.AuthenticationService.validate;


@WebServlet("/post")
public class PostServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String postId = (String) request.getParameter("post-id");


        HttpSession session = request.getSession();
        UserRegistered userRegistered = (UserRegistered) session.getAttribute("user");

        if (postId != null) {
            PostService controller = new PostService();
            request.setAttribute("post", controller.getPost(postId));

            String like = (String) request.getParameter("like");
            if (like != null && validate(userRegistered)) {
                controller.addLike(postId, userRegistered.getUsername());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/post.jsp");
            dispatcher.forward(request, response);

        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}