package servlet.profile;

import domain.EntityMapper;
import domain.service.LevelService;
import servlet.entity.PostDto;
import servlet.entity.UserDto;
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
import java.util.stream.Collectors;


import static domain.EntityMapper.viewToUser;
import static domain.service.AuthenticationService.validateUser;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        LevelService levelService = new LevelService();

        if (validateUser(viewToUser(user))) {
            String postUsername = (String) request.getParameter("username");

            levelService.calculateLevel(postUsername);
            if(postUsername==null){
                postUsername = user.getUsername();
            }

            ProfileService profileService = new ProfileService();
            List<PostDto> postList;

            postList = profileService
                            .getUsersPosts(postUsername)
                            .stream()
                            .map(EntityMapper::postToView)
                            .collect(Collectors.toList());

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