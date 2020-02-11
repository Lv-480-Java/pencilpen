package dao.implementations;

import domain.entity.User;

import java.util.List;

public class UserDao {

    Mapper<User> mapper = new Mapper<>(User.class);

    public List<User> getByUsername(String username){
        List<User> UserList = mapper.getBy("nickname", username);
        return UserList;
    }

    public List<User> getByEmail(String email){
        List<User> UserList = mapper.getBy("email", email);
        return UserList;
    }

    public List<User> getById(String id){
        List<User> UserList = mapper.getBy("email", id);
        return UserList;
    }

    public void setUser(User user){
        mapper.addField(user);
    }
}
