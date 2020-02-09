package domain.service;

import dao.Mapper;
import domain.entity.User;
import domain.exception.registration.AlreadyExistsException;
import domain.exception.registration.PasswordException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AuthenticationService {

    public void register(User userToRegister, String passwordRepeat) {

        String username = userToRegister.getNickname();
        String password = userToRegister.getPass();
        String email = userToRegister.getEmail();
        Mapper<User> mapper = new Mapper<>(User.class);

        if (mapper.getBy("email", email).size()==0 &&
            mapper.getBy("nickname", username).size()==0) {

            if (password.equals(passwordRepeat) &&
                password.length() > 7 &&
                password.length() < 50) {
                if (email.length() < 50 &&
                    username.length()>3 &&
                    username.length() < 50
                ) {
                    mapper.addField(
                            new User(email,
                                     username,
                                     password
                            ));
                } else {
                    throw new IllegalArgumentException("not good");
                }
            } else {
                throw new PasswordException("not good");
            }
        } else {
            throw new AlreadyExistsException("not good");
        }
    }

    public static boolean validate(String nickname, String pass){
        if(nickname==null || pass==null){
            return false;
        }
        Mapper<User> userMapper = new Mapper<>(User.class);
        List<User> userList =  userMapper.getBy("nickname", nickname);
        if(userList.size()>0) {
            if (userList.get(0).getPass().equals(pass)) {
                System.out.println("ITS OKAY");
                return true;
            }
        }
        return false;
    }
}
