package dao.implementation;

import dao.Mapper;
import domain.entity.Pleasant;

public class PleasantDao {
    private static Mapper<Pleasant> mapper = new Mapper<>(Pleasant.class);

    public void addLike(Pleasant like){
        mapper.addField(like);
    }

    public void deleteLike(Pleasant like){
        mapper.deleteField(like, "id=\" "+like.getId()+"\"");
    }
}
