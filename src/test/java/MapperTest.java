import dao.Mapper;
import domain.entity.Comment;
import domain.entity.User;
import org.junit.Test;

import java.util.ArrayList;

public class MapperTest {

    @Test
    public void getBy() {
        ArrayList<User> users = new ArrayList<>();

        Mapper<Comment> mapper = new Mapper<>(Comment.class);
        Comment comment = new Comment();
        comment.setAddDate("2020-02-02 22:14:29");
        comment.setCommentText("my veerr good comment");
        comment.setPostId(1);
        comment.setUserId(1);
        comment.setId(4);
        mapper.deleteField(comment);
    }
}