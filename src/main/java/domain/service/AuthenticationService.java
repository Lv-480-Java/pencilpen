package domain.service;

import dao.Mapper;
import domain.entity.User;
import domain.entity.UserRegistered;
import domain.exception.registration.AlreadyExistsException;
import domain.exception.registration.PasswordException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AuthenticationService {

    public boolean register(User userToRegister, String passwordRepeat) {

        String username = userToRegister.getNickname();
        String password = userToRegister.getPass();
        String email = userToRegister.getEmail();
        Mapper<User> mapper = new Mapper<>(User.class);

        if (username.length() < 3 || username.length() > 50) {
            mapper.addField(new User(email, username, password));
            throw new IllegalArgumentException("Username is too short or too long");
        }

        if (email.length() > 50 || email.length() < 10) {
            mapper.addField(new User(email, username, password));
            throw new IllegalArgumentException("Email or user is too short or too long");
        }

        if ((mapper.getBy("email", email).size() != 0 ||
                mapper.getBy("nickname", username).size() != 0)) {
            throw new AlreadyExistsException("Email or username allready exists");
        }

        if (!(password.equals(passwordRepeat)) ||
                password.length() < 7 ||
                password.length() > 50) {
            throw new PasswordException("Error! password is too weak");
        }
        mapper.addField(userToRegister);
        return true;
    }

    public static boolean validate(UserRegistered user) {

        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return false;
        }

        Mapper<User> userMapper = new Mapper<>(User.class);
        List<User> userList = userMapper.getBy("nickname", user.getUsername());

        if (userList.size() > 0) {
            if (userList.get(0).getPass().equals(user.getPassword())) {
                System.out.println("ITS OKAY");
                return true;
            }
        }

        return false;
    }
}
