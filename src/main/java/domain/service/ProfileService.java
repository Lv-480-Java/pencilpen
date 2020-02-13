package domain.service;

import dao.implementation.PostDao;
import domain.entity.Post;
import domain.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileService {

    public List<Post> getUsersPosts(String username) {
        PostDao postDao = new PostDao();
        User user = new User();

        user.setUsername(username);
        List<Post> postList = postDao.getByUser(user);

        return  postList.stream().filter(Post::getIsActive).collect(Collectors.toList());
    }

}
