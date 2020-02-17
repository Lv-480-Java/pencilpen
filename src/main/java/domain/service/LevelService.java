package domain.service;

import dao.implementation.PleasantDao;
import dao.implementation.PostDao;
import dao.implementation.UserDao;
import domain.entity.Post;
import domain.entity.User;

import java.util.List;

import static java.lang.Math.sqrt;

public class LevelService {

    private static UserDao userDao = new UserDao();
    private static PostDao postDao = new PostDao();
    private static PleasantDao pleasantDao = new PleasantDao();

    public void calculateLevel(String username) {
        int actionValue = 1;
        User userVerified = userDao.getByUsername(username).get(0);

        int likesByUser = pleasantDao.getUserLikes(userVerified.getId()).size();
        actionValue += likesByUser;

        List<Post> postList = postDao.getIdByUserId(String.valueOf(userVerified.getId()));
        actionValue += postList.size();

        for (Post post : postList) {
            int postId = post.getId();

            actionValue += post.getLikeList().size();
            actionValue += post.getCommentList().size();
        }

        int result = (int) (sqrt(1000 * actionValue) / 20);
        userVerified.setLevel(result);
        userDao.updateUser(userVerified);
    }

    public static int getUserLevel(String username) {
        return userDao.getByUsername(username).get(0).getLevel();
    }

}
