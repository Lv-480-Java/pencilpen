package servlet;


import dao.Mapper;
import domain.entity.Post;
import domain.service.PostService;
import domain.service.SearchService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/gallery")
public class GalleryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String tag = request.getParameter("tag");
        List<Post> posts;

        if (tag != null) {
            SearchService searchService = new SearchService();
            posts = searchService.findByTag(tag);
        } else {
            PostService postService = new PostService();
            posts = postService.getAllPosts();
        }
        request.setAttribute("postList", posts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/gallery.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
