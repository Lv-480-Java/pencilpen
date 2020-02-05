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

    public void addPost(Post post, String usernameAtribute){
        Mapper<User> userMapper = new Mapper<>(User.class);
        int userId = userMapper.getBy("nickname",usernameAtribute).get(0).getId();
        System.out.println(userId);
        post.setUserId(userId);
        Mapper<Post> postMapper = new Mapper<>(Post.class);
        postMapper.addField(post);

    }
}
