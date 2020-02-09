package domain.service;

import dao.Mapper;
import domain.entity.Comment;
import domain.entity.Pleasant;
import domain.entity.Post;
import domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

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

    public List<Post> getAllPosts() {
        Mapper<Post> postMapper = new Mapper<>(Post.class);
        List<Post> posts = postMapper.getAll();
        Collections.reverse(posts);
        return posts;
    }

    public void addLike(String postId, String username) {

        Mapper<Post> mapper = new Mapper<>(Post.class);
        List<Post> postList = mapper.getBy("id", postId);

        Mapper<User> userList = new Mapper<>(User.class);
        int userId = userList.getBy("nickname", username).get(0).getId();


        Mapper<Pleasant> pleasantMapper = new Mapper<>(Pleasant.class);
        List<Pleasant> likeList = postList.get(0).getLikeList();

        boolean isLikedByUser = false;
        for (Pleasant like : likeList) {
            if (like.getUserId() == userId) {
                isLikedByUser = true;
                dislikePost(like, pleasantMapper);
                break;
            }
        }
        if (!isLikedByUser) {
            Pleasant like = new Pleasant();
            like.setPostId(Integer.parseInt(postId));
            like.setUserId(userId);

            pleasantMapper.addField(like);
        }
    }

    private void dislikePost(Pleasant like, Mapper<Pleasant> pleasantMapper) {
        pleasantMapper.deleteField(like);
    }
}
