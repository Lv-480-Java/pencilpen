package domain.mapping;

import dao.implementation.UserDao;
import domain.entity.User;
import servlet.entity.UserDto;

public class UserMapper {


    public static UserDao userDao = new UserDao();

    public static User dtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPass(userDto.getPassword());
        user.setLevel(userDto.getLevel());
        user.setRole(userDto.getUserRole());
        return user;
    }

    public static UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPass());
        userDto.setLevel(user.getLevel());
        userDto.setUserRole(user.getRole());
        return userDto;
    }

}
