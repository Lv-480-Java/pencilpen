package domain.service;

import dao.implementation.CommentDao;
import dao.implementation.PleasantDao;
import dao.implementation.PostDao;
import dao.implementation.UserDao;
import domain.entity.Comment;
import domain.entity.Pleasant;
import domain.entity.Post;
import domain.entity.User;

import java.util.Collections;
import java.util.List;

public class PostService {
    private UserDao userDao = new UserDao();
    private PostDao postDao = new PostDao();
    private PleasantDao pleasantDao = new PleasantDao();

    public Post getPost(String id) {
        return postDao.getById(id).get(0);
    }

    public void addComment(Comment comment) {
        CommentDao commentMapper = new CommentDao();
        commentMapper.addComment(comment);
    }

    public void addPost(Post post) {
        int userId = userDao.getByUsername(post.getUsername()).get(0).getId();
        post.setUserId(userId);
        postDao.setPost(post);
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postDao.getAll();
        Collections.reverse(posts);
        return posts;
    }

    public void addLike(Pleasant pleasant) {
        Post post = postDao.getById(String.valueOf(pleasant.getPostId())).get(0);

        Pleasant like = post
                .getLikeList()
                .stream()
                .filter(p -> p.getUserId() == pleasant.getUserId())
                .findFirst()
                .orElse(null);

        if (like != null) {
            pleasantDao.deleteLike(like);
        } else {
            pleasantDao.addLike(pleasant);
        }
    }

    public void removePost(String postId, User user) throws IllegalAccessException {
        if (user == null) {
            throw new IllegalAccessException("You have no Permission do delete other posts");
        }
        Post postFromDb = postDao.getById(postId).get(0);
        User userFromDb = userDao.getByUsername(user.getUsername()).get(0);

        if (userFromDb.getId() == postFromDb.getUserId()) {
            postFromDb.setIsActive(false);
            postDao.updatePost(postFromDb);
        } else {
            throw new IllegalAccessException("You have no Permission do delete other posts");
        }
    }
}
