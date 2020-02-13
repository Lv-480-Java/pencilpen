package dao.implementation;

import dao.Mapper;
import domain.entity.Post;
import domain.entity.Tag;
import domain.entity.User;

import java.util.List;

public class PostDao {
    private static Mapper<Post> mapper = new Mapper<>(Post.class);

    public List<Post> getAll() {
        return mapper.getBy("isActive", "true");
    }

    public List<Post> getByUser(User user) {
        return mapper.getBy("username", user.getUsername());
    }

    public List<Post> getByTag(Tag tag) {
        return mapper.getLike("title", tag.getTagName());
    }

    public List<Post> getById(String id) {
        return mapper.getBy("id", id);
    }

    public void setPost(Post post) {
        mapper.addField(post);
    }

    public void deletePostBeforeDate(String date, boolean isActive) {
        String condition = " postDate < \"%s\" AND isactive = \"%s\" ";
        mapper.deleteField(new Post(), String.format(condition, date, isActive));
    }

    public void updatePost(Post post){
        mapper.updateField(post);
    }
}
