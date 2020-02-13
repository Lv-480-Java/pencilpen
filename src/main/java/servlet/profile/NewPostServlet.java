package servlet.profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import servlet.entity.PostDto;
import servlet.entity.UserDto;
import domain.service.PostService;

import static domain.EntityMapper.*;
import static domain.service.AuthenticationService.validateUser;


@WebServlet("/add")
public class NewPostServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserDto user = (UserDto) session.getAttribute("user");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        json = br.readLine();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        String description = jsonObject.get("description").getAsString();
        String tag = jsonObject.get("tag").getAsString();
        String imageData = jsonObject.get("imageData").getAsString();

        if (validateUser(viewToUser(user)) &&
                description != null) {

            PostDto post = new PostDto();
            post.setPicUrl(imageData);
            post.setPostText(description);
            post.setTag(tag);
            post.setUsername(user.getUsername());

            PostService newPostController = new PostService();
            newPostController.addPost(viewToPost(post));
        }
    }
}