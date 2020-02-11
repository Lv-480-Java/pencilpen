package servlet;


import domain.EntityMapper;
import domain.service.PostService;
import domain.service.SearchService;
import servlet.entity.PostView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static domain.EntityMapper.postToView;

@WebServlet("/gallery")
public class GalleryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String tag = request.getParameter("tag");
        List<PostView> posts;

        if (tag != null) {
            SearchService searchService = new SearchService();
            posts = searchService
                    .findByTag(tag)
                    .stream()
                    .map(EntityMapper::postToView)
                    .collect(Collectors.toList());
        } else {
            PostService postService = new PostService();
            posts = postService
                    .getAllPosts()
                    .stream()
                    .map(EntityMapper::postToView)
                    .collect(Collectors.toList());
        }
        request.setAttribute("postList", posts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/gallery.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
