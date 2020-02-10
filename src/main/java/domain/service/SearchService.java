package domain.service;

import dao.Mapper;
import dao.implementations.PostDao;
import domain.entity.Post;
import domain.entity.Tag;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchService {

    public List<Post> findByTag(String tagText){

        PostDao postMapper = new PostDao();

        Tag tag = new Tag();
        tag.setTagName(tagText);

        List<Post> postList = postMapper.getByTag(tag);
        Collections.reverse(postList);

        return postList;
    }
}
