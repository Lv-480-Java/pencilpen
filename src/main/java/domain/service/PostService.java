package domain.service;

import dao.implementations.Mapper;
import dao.implementations.CommentDao;
import dao.implementations.PostDao;
import dao.implementations.UserDao;
import domain.entity.*;
import servlet.entity.PleasantView;

import java.util.Collections;
import java.util.List;

public class PostService {
    private UserDao userDao = new UserDao();
    private PostDao postDao = new PostDao();

    public void addPost(Post post) {
        int userId = userDao.getByUsername(post.getUsername()).get(0).getId();
        post.setUserId(userId);
        postDao.setPost(post);
    }

    public Post getPost(String id) {
        return postDao.getById(id).get(0);
    }

    public void addComment(Comment comment) {
        CommentDao commentMapper = new CommentDao();
        commentMapper.addComment(comment);
    }

    public List<Post> getAllPosts() {

        List<Post> posts = postDao.getAll();
        Collections.reverse(posts);
        return posts;
    }

    public void addLike(PleasantView pleasant) {

    }
}
