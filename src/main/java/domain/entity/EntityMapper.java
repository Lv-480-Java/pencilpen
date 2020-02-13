package domain.entity;

import dao.implementation.UserDao;
import servlet.entity.CommentDto;
import servlet.entity.PleasantDto;
import servlet.entity.PostDto;
import servlet.entity.UserDto;

import java.util.stream.Collectors;

public class EntityMapper {

   private static UserDao userDao = new UserDao();

    public static Post viewToPost(PostDto postDto) {
        Post post = new Post();
        post.setPicUrl(postDto.getPicUrl());
        post.setPostText(postDto.getPostText());
        post.setTitle(postDto.getTag());
        post.setUsername(postDto.getUsername());
        post.setLikeList(postDto.getLikeList().stream().map(EntityMapper::viewToPleasant).collect(Collectors.toList()));
        post.setCommentList(postDto.getCommentList().stream().map(EntityMapper::viewToComment).collect(Collectors.toList()));
        post.setId(postDto.getId());
        return post;
    }

    public static PostDto postToView(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPicUrl(post.getPicUrl());
        postDto.setPostText(post.getPostText());
        postDto.setTag(post.getTitle());
        postDto.setUsername(post.getUsername());
        postDto.setLikeList(post.getLikeList().stream().map(EntityMapper::pleasantToView).collect(Collectors.toList()));
        postDto.setCommentList(post.getCommentList().stream().map(EntityMapper::commentToView).collect(Collectors.toList()));
        postDto.setId(post.getId());
        return postDto;
    }

    public static User viewToUser(UserDto userDto){
        if(userDto==null){return null;}
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPass(userDto.getPassword());
        return user;
    }

    public static UserDto userToView(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPass());
        return userDto;
    }

    public static PleasantDto pleasantToView(Pleasant pleasant){
        PleasantDto pleasantDto = new PleasantDto();
        pleasantDto.setPostId(String.valueOf(pleasant.getPostId()));

        UserDao userDao = new UserDao();
        String username = userDao.getById(String.valueOf(pleasant.getUserId())).get(0).getUsername();
        pleasantDto.setUsername(username);
        return pleasantDto;
    }

    public static Pleasant viewToPleasant (PleasantDto pleasantDto){
        Pleasant pleasant = new Pleasant();
        pleasant.setPostId(Integer.parseInt(pleasantDto.getPostId()));

        int userId = userDao.getByUsername(pleasantDto.getUsername()).get(0).getId();

        pleasant.setUserId(userId);
        return pleasant;
    }

    public static Comment viewToComment(CommentDto commentDto){
        Comment comment = new Comment();
        int userId = userDao.getByUsername(commentDto.getUsername()).get(0).getId();
        comment.setUserId(userId);
        comment.setPostId(commentDto.getPostId());
        comment.setCommentText(commentDto.getCommentText());
        return comment;
    }

    public static CommentDto commentToView(Comment comment){
        CommentDto commentDto = new CommentDto();
        String username = userDao.getById(String.valueOf(comment.getUserId())).get(0).getUsername();
        commentDto.setUsername(username);
        commentDto.setPostId(comment.getPostId());
        commentDto.setCommentText(comment.getCommentText());
        return commentDto;
    }




}
