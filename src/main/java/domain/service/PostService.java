package domain.service;

import dao.Mapper;
import domain.entity.Comment;
import domain.entity.Post;
import domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PostService {

    public void addPost(Post post) {
        Mapper<User> userMapper = new Mapper<>(User.class);
        Mapper<Post> postMapper = new Mapper<>(Post.class);
        int userId = userMapper.getBy("nickname", post.getUsername()).get(0).getId();
        post.setUserId(userId);
        postMapper.addField(post);
    }

    public Post getPost(String id) {
        Mapper<Post> postMapper = new Mapper<>(Post.class);
        return (Post) postMapper.getBy("id", id).get(0);
    }

    public void addComment(HttpServletRequest request, HttpSession session) {

        String passwordAtribute = (String) session.getAttribute("password");
        String usernameAtribute = (String) session.getAttribute("username");

        String commentAtr = (String) request.getParameter("comment-add");
        String postIdAtr = (String) request.getParameter("post-id");

        Mapper<User> userMapper = new Mapper<>(User.class);
        Mapper<Comment> postMapper = new Mapper<>(Comment.class);

        int userId = userMapper.getBy("nickname", (String) session.getAttribute("username")).get(0).getId();

        Comment comment = new Comment();
        comment.setCommentText(commentAtr);
        comment.setPostId(postIdAtr);
        comment.setNickname(usernameAtribute);
        comment.setUserId(userId);

        postMapper.addField(comment);
    }
}
