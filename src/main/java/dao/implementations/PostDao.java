package dao.implementations;

import dao.Mapper;
import domain.entity.Post;
import domain.entity.Tag;
import domain.entity.User;

import java.util.List;

public class PostDao {
    Mapper<Post> mapper = new Mapper<>(Post.class);

    public List<Post> getAll(){
        return mapper.getAll();
    }

    public List<Post> getByUser(User user){
        return mapper.getBy("nickname", user.getNickname());
    }

    public List<Post> getByTag(Tag tag){
        return mapper.getLike("title" , tag.getTagName());
    }
}
