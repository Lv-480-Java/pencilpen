import dao.mapper.Mapper;
import domain.entity.Post;
import domain.entity.User;
import org.junit.Test;

import java.util.ArrayList;

public class MapperTest {

    @Test
    public void getBy() {
        ArrayList<User> users = new ArrayList<>();

        Mapper<Post> mapper = new Mapper<>(Post.class);
        System.out.println(mapper.getBy("id","1").get(0).toString());
    }
}