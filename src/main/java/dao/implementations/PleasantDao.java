package dao.implementations;

import domain.entity.Pleasant;

public class PleasantDao {
    Mapper<Pleasant> mapper = new Mapper<>(Pleasant.class);

    public void addLike(Pleasant like){
        mapper.addField(like);
    }
}
