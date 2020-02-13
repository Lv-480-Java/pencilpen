package dao.implementation;

import dao.Mapper;
import domain.entity.Pleasant;

import java.util.List;

public class PleasantDao {
    private static Mapper<Pleasant> mapper = new Mapper<>(Pleasant.class);

    public void addLike(Pleasant like){
        mapper.addField(like);
    }

    public void deleteLike(Pleasant like){
        mapper.deleteField(like, "id=\" "+like.getId()+"\"");
    }

    public List<Pleasant> getUserLikes(int userId){
        return mapper.getBy("userId", userId+"");
    }

    public List<Pleasant> getPostLikes(int postId){
        return mapper.getBy("postId", postId+"");
    }




}
