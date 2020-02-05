package domain.logic;

import dao.Mapper;
import domain.entity.User;
import domain.exception.registration.AlreadyExistsException;
import domain.exception.registration.PasswordException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AuthenticationController {

    public void register(HttpServletRequest request) {

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("password-repeat");

        Mapper<User> mapper = new Mapper<>(User.class);

        if (mapper.getBy("email", email).size()==0 &&
            mapper.getBy("nickname", username).size()==0) {

            if (password.equals(repeatedPassword) &&
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

    public boolean login(String nickname, String pass){
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
