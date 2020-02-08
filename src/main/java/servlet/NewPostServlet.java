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
import domain.entity.Post;
import domain.service.PostService;


@WebServlet("/add")
public class NewPostServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String passwordAtribute = (String) session.getAttribute("password");
        String usernameAtribute = (String) session.getAttribute("username");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println(json);
        }
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        String description = jsonObject.get("description").getAsString();
        String tag = jsonObject.get("tag").getAsString();
        String imageData = jsonObject.get("imageData").getAsString();

        if(passwordAtribute!=null &&
           usernameAtribute!=null &&
           description!=null){

                Post post = new Post();
                post.setPicUrl(imageData);
                post.setPostText(description);
                post.setTitle(tag);
                post.setUsername(usernameAtribute);

                PostService newPostController = new PostService();
                newPostController.addPost(post);
        }
    }
}