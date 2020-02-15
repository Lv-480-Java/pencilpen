package domain.mapping;

import dao.implementation.UserDao;
import domain.entity.Pleasant;
import servlet.entity.PleasantDto;

import static domain.mapping.UserMapper.userDao;

public class PleasantMapper {


    public static PleasantDto pleasantToDto(Pleasant pleasant) {
        PleasantDto pleasantDto = new PleasantDto();
        pleasantDto.setPostId(String.valueOf(pleasant.getPostId()));

        UserDao userDao = new UserDao();
        String username = userDao.getById(String.valueOf(pleasant.getUserId())).get(0).getUsername();
        pleasantDto.setUsername(username);
        return pleasantDto;
    }

    public static Pleasant dtoToPleasant(PleasantDto pleasantDto) {
        Pleasant pleasant = new Pleasant();
        pleasant.setPostId(Integer.parseInt(pleasantDto.getPostId()));
        int userId = userDao.getByUsername(pleasantDto.getUsername()).get(0).getId();
        pleasant.setUserId(userId);
        return pleasant;
    }

}
