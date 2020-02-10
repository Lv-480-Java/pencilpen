package dao.implementations;

import dao.Mapper;
import domain.entity.Comment;
import domain.entity.Post;
import domain.entity.User;
import javafx.geometry.Pos;

import java.util.List;

public class CommentDao {
    Mapper<Comment> mapper = new Mapper<>(Comment.class);


    public List<Comment> getByUser(User user){
        List<Comment> commentList = mapper.getBy("nickname", user.getNickname());
        return commentList;
    }

    public List<Comment> getByPost(Post post){
        return mapper.getBy("postId", String.valueOf(post.getId()));
    }

    public void addComment(Comment comment){
        UserDao userMapper = new UserDao();
        int userId = userMapper.getByUsername(comment.getNickname()).get(0).getId();
        comment.setUserId(userId);
        mapper.addField(comment);
    }

}
