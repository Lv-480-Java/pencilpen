package domain.service;

import dao.implementations.UserDao;
import domain.entity.User;
import servlet.entity.UserDto;
import domain.exception.registration.AlreadyExistsException;
import domain.exception.registration.PasswordException;

import java.util.List;

public class AuthenticationService {


    private static UserDao userMapper = new UserDao();

    public boolean register(UserDto userToRegister, String passwordRepeat) {

        String username = userToRegister.getUsername();
        String password = userToRegister.getPassword();
        String email = userToRegister.getEmail();

        if (username.length() < 3 || username.length() > 50) {
            throw new IllegalArgumentException("Username is too short or too long");
        }

        if (email.length() > 50 || email.length() < 10) {
            throw new IllegalArgumentException("Email or user is too short or too long");
        }

        if ((userMapper.getByEmail(email).size() != 0 ||
                userMapper.getByUsername(username).size() != 0)) {
            throw new AlreadyExistsException("Email or username allready exists");
        }

        if (!(password.equals(passwordRepeat)) ||
                password.length() < 7 ||
                password.length() > 50) {
            throw new PasswordException("Error! password is too weak");
        }
        userMapper.setUser(new User(email, username, password));
        return true;
    }

    public static boolean validateUser(UserDto user) {

        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return false;
        }

        List<User> userList = userMapper.getByUsername(user.getUsername());

        if (userList.size() > 0) {
            if (userList.get(0).getPass().equals(user.getPassword())) {
                System.out.println("ITS OKAY");
                return true;
            }
        }

        return false;
    }
}
