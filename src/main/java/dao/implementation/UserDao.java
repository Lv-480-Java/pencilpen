package dao.implementation;

import dao.Mapper;
import domain.entity.User;

import java.util.List;

public class UserDao {

    private static Mapper<User> mapper = new Mapper<>(User.class);

    public List<User> getByUsername(String username) {
        List<User> UserList = mapper.getBy("username", username);
        return UserList;
    }

    public List<User> getByEmail(String email) {
        List<User> UserList = mapper.getBy("email", email);
        return UserList;
    }

    public List<User> getById(String id) {
        List<User> UserList = mapper.getBy("id", id);
        return UserList;
    }

    public void setUser(User user) {
        mapper.addField(user);
    }

    public void updateUser(User user) {
        mapper.updateField(user);
    }
}
