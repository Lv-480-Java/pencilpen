package servlet;


import dao.Mapper;
import domain.entity.Post;
import domain.logic.AuthenticationController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet("/gallery")
public class GalleryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Mapper<Post> postMapper = new Mapper<>(Post.class);   // FIXME  винести маппер на леєр вище і зробити рандомний підбор постів
        List<Post> posts = postMapper.getBy("userId",""+2 );
        Collections.reverse(posts);

        request.setAttribute("postList", posts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/gallery.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
