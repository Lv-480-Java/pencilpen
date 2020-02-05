package domain.logic;

import dao.Mapper;
import domain.entity.Post;
import domain.entity.User;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;

public class NewPostController {

    public void fetchPicture(HttpSession session, HttpServletRequest request, String uniqueId){
        String usernameAtribute = (String) session.getAttribute("username");
        String imageByteString = null;
        try {
            imageByteString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            byte[] bImg64 = imageByteString.getBytes();
            byte[] bImg = Base64.decodeBase64(bImg64);
            StringBuilder path = new StringBuilder("src/main/webapp/penpencil/posts/");
            path.append(usernameAtribute).append("/");
            path.append(uniqueId).append(".png");
            if(bImg.length>50) {
                FileOutputStream fos = new FileOutputStream(path.toString());
                fos.write(bImg);
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addPost(HttpServletRequest request, HttpSession session, String uniqueId){

        Mapper<User> userMapper = new Mapper<>(User.class);

        String usernameAtribute = (String) session.getAttribute("username");
        String postText = request.getParameter("description");
        String title = request.getParameter("tag");
        int userId = userMapper.getBy("nickname",usernameAtribute).get(0).getId();

        Post post = new Post();
        post.setPicUrl(uniqueId);
        post.setPostText(postText);
        post.setUserId(userId);
        post.setTitle(title);

        Mapper<Post> postMapper = new Mapper<>(Post.class);
        System.out.println(post.toString());
        postMapper.addField(post);

    }
}
