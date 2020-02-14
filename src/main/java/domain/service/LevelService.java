package domain.service;

import dao.implementation.PleasantDao;
import dao.implementation.PostDao;
import dao.implementation.UserDao;
import domain.entity.Post;
import domain.entity.User;

import java.util.List;

import static java.lang.Math.sqrt;

public class LevelService {

    private static UserDao userMapper = new UserDao();
    private static PostDao postMapper = new PostDao();
    private static PleasantDao pleasantMapper = new PleasantDao();

    public void calculateLevel(String username) {
        int actionValue = 1;
        User userVerified = userMapper.getByUsername(username).get(0);

        int likesByUser = pleasantMapper.getUserLikes(userVerified.getId()).size();
        actionValue += likesByUser;

        List<Post> postList = postMapper.getIdByUserId(String.valueOf(userVerified.getId()));
        actionValue += postList.size();

        for (Post post : postList) {
            int postId = post.getId();

            actionValue += post.getLikeList().size();
            actionValue += post.getCommentList().size();
        }

        int result = (int) (sqrt(1000 * actionValue) / 20);
        userVerified.setLevel(result);
        userMapper.updateUser(userVerified);
    }

    public static int getUserLevel(User user) {
        return userMapper.getByUsername(user.getUsername()).get(0).getLevel();
    }

    public static int getUserLevel(String username) {
        return userMapper.getByUsername(username).get(0).getLevel();
    }

}
