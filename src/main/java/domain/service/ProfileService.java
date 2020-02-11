package domain.service;

import dao.implementations.Mapper;
import dao.implementations.PostDao;
import domain.entity.Post;
import domain.entity.User;

import java.util.List;

public class ProfileService {

    public List<Post> getUsersPosts(String username) {
        PostDao postDao = new PostDao();
        User user = new User();
        user.setNickname(username);
        return postDao.getByUser(user);

    }


}
