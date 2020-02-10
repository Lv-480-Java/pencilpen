package domain.service;

import dao.Mapper;
import domain.entity.Post;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchService {

    public List<Post> findByTag(String tag){

        Mapper<Post> mapper = new Mapper<>(Post.class);
        List<Post> postList = mapper.getAll();

        List<Post> postListAccept = new ArrayList<>();

        for(Post post: postList){
            if(post.getTitle().toLowerCase().contains(tag.toLowerCase())){
                postListAccept.add(post);
            }
        }
        Collections.reverse(postListAccept);
        return postListAccept;
    }
}
