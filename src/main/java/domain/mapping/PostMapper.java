package domain.mapping;

import domain.entity.Post;
import servlet.entity.PostDto;

import java.util.stream.Collectors;

public class PostMapper {

    public static Post dtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setPicUrl(postDto.getPicUrl());
        post.setPostText(postDto.getPostText());
        post.setTitle(postDto.getTag());
        post.setUsername(postDto.getUsername());
        post.setLikeList(postDto.getLikeList().stream().map(PleasantMapper::dtoToPleasant).collect(Collectors.toList()));
        post.setCommentList(postDto.getCommentList().stream().map(СommentMapper::dtoToComment).collect(Collectors.toList()));
        post.setId(postDto.getId());
        return post;
    }

    public static PostDto postToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPicUrl(post.getPicUrl());
        postDto.setPostText(post.getPostText());
        postDto.setTag(post.getTitle());
        postDto.setUsername(post.getUsername());
        if (post.getLikeList() != null)
            postDto.setLikeList(post.getLikeList().stream().map(PleasantMapper::pleasantToDto).collect(Collectors.toList()));
        if (post.getCommentList() != null)
            postDto.setCommentList(post.getCommentList().stream().map(СommentMapper::commentToDto).collect(Collectors.toList()));
        postDto.setId(post.getId());
        return postDto;
    }
}
