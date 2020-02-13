package servlet.profile;

import domain.service.PostService;
import servlet.entity.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static domain.EntityMapper.viewToUser;

@WebServlet("/remove")
public class DeletePostServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String postId = request.getParameter("post-id");
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        PostService service = new PostService();

        try {
            service.removePost(postId, viewToUser(user));
            response.sendRedirect("/profile?username="+user.getUsername());
        } catch (IllegalAccessException e) {
            request.setAttribute("text-result",  e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/gallery.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
