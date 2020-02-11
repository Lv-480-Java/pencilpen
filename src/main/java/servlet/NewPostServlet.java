package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import servlet.entity.PostView;
import servlet.entity.UserView;
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

        UserView user = (UserView) session.getAttribute("user");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        json = br.readLine();
        System.out.println(json);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        String description = jsonObject.get("description").getAsString();
        String tag = jsonObject.get("tag").getAsString();
        String imageData = jsonObject.get("imageData").getAsString();

        if (validateUser(user) &&
                description != null) {

            PostView post = new PostView();
            post.setPicUrl(imageData);
            post.setPostText(description);
            post.setTag(tag);
            post.setUsername(user.getUsername());

            PostService newPostController = new PostService();
            newPostController.addPost(viewToPost(post));
        }
    }
}