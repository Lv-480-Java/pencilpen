package domain.logic;

import dao.layer.UserDao;
import domain.entity.User;
import domain.exception.registration.AlreadyExistsException;
import domain.exception.registration.PasswordException;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationController {

    public void register(HttpServletRequest request) {

        UserDao userDao = new UserDao();
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("password-repeat");

        if (userDao.getUserByEmail(email) == null &&
            userDao.getUserByNickname(username) == null) {

            if (password.equals(repeatedPassword) &&
                password.length() > 7 &&
                password.length() < 50) {

                if (email.length() < 50 &&
                    username.length() < 50
                ) {
                    userDao.create(
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
}
