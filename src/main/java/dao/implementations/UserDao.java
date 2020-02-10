package dao.implementations;

import dao.Mapper;
import domain.entity.Comment;
import domain.entity.Post;
import domain.entity.User;

import java.util.List;

public class UserDao {

    Mapper<User> mapper = new Mapper<>(User.class);

    public List<User> getByUsername(String username){
        List<User> UserList = mapper.getBy("nickname", username);
        return UserList;
    }
}
