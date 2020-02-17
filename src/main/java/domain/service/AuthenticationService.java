package domain.service;

import dao.Mapper;
import dao.implementation.UserDao;
import domain.entity.User;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationService {
    private static final Pattern emailRegex = Pattern.compile("^\\S+@\\S+$");
    private static final Pattern usernameRegex = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
    private static final Pattern passwordRegex = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");

    private static UserDao userDao = new UserDao();
    private static Logger log = Logger.getLogger(Mapper.class.getName());

    public void register(User userToRegister, String passwordRepeat) {

        String username = userToRegister.getUsername();
        String password = userToRegister.getPass();
        String email = userToRegister.getEmail();

        String passwordHash = null;
        Map<String, Boolean> resultMap = validateUserFields(userToRegister, passwordRepeat);

        if (resultMap.containsValue(false)) {
            String result = resultMap.keySet().toString().replaceAll("[^a-zA-Z0-9,]+", " ");
            throw new IllegalArgumentException(result);
        }
        try {
            passwordHash = hashPassword(password, username);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Cannot hash password", e);
            throw new IllegalArgumentException("Cannot hash password");
        }
        userDao.setUser(new User(email, username, passwordHash));
    }

    private Map<String, Boolean> validateUserFields(User user, String passwordRepeat) {
        Map<String, Boolean> resultMap = new HashMap<>();

        Matcher emailMatcher = emailRegex.matcher(user.getEmail());
        Matcher usernameMatcher = usernameRegex.matcher(user.getUsername());
        Matcher passwordMatcher = passwordRegex.matcher(user.getPass());

        boolean usernameExists = userDao.getByUsername(user.getUsername()).size() > 0;
        boolean emailExists = userDao.getByEmail(user.getEmail()).size() > 0;

        if (!usernameMatcher.matches() || usernameExists) {
            resultMap.put("username already exists or not right", false);
        }

        if (!emailMatcher.matches() || emailExists) {
            resultMap.put("email is not valid or already exists", false);
        }

        boolean isValid = (user.getPass().equals(passwordRepeat)) || passwordMatcher.matches();

        if (!isValid) {
            resultMap.put("password does'nt match", false);
        }

        return resultMap;
    }


    public String hashPassword(String password, String saltString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String result;
        byte[] salt;

        salt = saltString.getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }

    public static boolean validateUser(User user) {
        if (user == null || user.getUsername() == null || user.getPass() == null) {
            return false;
        }
        String passwordHashed = null;
        try {
            AuthenticationService service = new AuthenticationService();
            passwordHashed = service.hashPassword(user.getPass(), user.getUsername());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Cannot hash password, e");
        }

        List<User> userList = userDao.getByUsername(user.getUsername());
        if (userList.size() > 0) {
            if (userList.get(0).getPass().equals(passwordHashed)) {
                return true;
            }
        }
        return false;
    }
}
