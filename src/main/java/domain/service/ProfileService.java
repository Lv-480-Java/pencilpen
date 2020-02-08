package domain.service;

import dao.Mapper;
import domain.entity.Post;

import java.util.List;

public class ProfileService {

    public List<Post> getUsersPosts(String username){
        Mapper<Post> postMapper = new Mapper<>(Post.class);
        List<Post> posts = postMapper.getBy("nickname", username);
        return posts;
    }


}
