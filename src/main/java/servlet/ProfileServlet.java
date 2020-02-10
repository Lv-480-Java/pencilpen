package servlet;

import domain.entity.Post;
import domain.entity.UserRegistered;
import domain.service.AuthenticationService;
import domain.service.ProfileService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static domain.service.AuthenticationService.validate;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (validate((UserRegistered) session.getAttribute("user"))) {

            String postUsername = (String) request.getParameter("username");

            ProfileService profileService = new ProfileService();
            List<Post> postList;

            postList = profileService.getUsersPosts(postUsername);
            Collections.reverse(postList);

            request.setAttribute("postList", postList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/profile.jsp");
            dispatcher.forward(request, response);

        } else {
            request.setAttribute("text-result", "Login First, to watch profile page");

            RequestDispatcher dispatcher = request.getRequestDispatcher("penpencil/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
