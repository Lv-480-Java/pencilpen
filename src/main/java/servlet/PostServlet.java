package servlet;

import servlet.entity.PleasantDto;
import servlet.entity.UserDto;
import domain.service.PostService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static domain.EntityMapper.*;
import static domain.service.AuthenticationService.validateUser;


@WebServlet("/post")
public class PostServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String postId = (String) request.getParameter("post-id");

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("user");

        if (postId != null) {
            PostService postService = new PostService();
            request.setAttribute("post", postToView(postService.getPost(postId)));

            if (request.getParameter("like") != null && validateUser(viewToUser(userDto))) {

                PleasantDto pleasant = new PleasantDto();
                pleasant.setUsername(userDto.getUsername());
                pleasant.setPostId(postId);

                postService.addLike(viewToPleasant(pleasant));
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/post.jsp");
            dispatcher.forward(request, response);

        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}