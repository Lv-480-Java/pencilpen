package dao.implementation;

import dao.Mapper;
import domain.entity.Comment;
import domain.entity.Post;
import domain.entity.User;

import java.util.List;

public class CommentDao {
    private static Mapper<Comment> mapper = new Mapper<>(Comment.class);


    public List<Comment> getByUser(User user){
        List<Comment> commentList = mapper.getBy("username", user.getUsername());
        return commentList;
    }

    public List<Comment> getByPost(Post post){
        return mapper.getBy("postId", String.valueOf(post.getId()));
    }

    public void addComment(Comment comment){
        mapper.addField(comment);
    }

}
