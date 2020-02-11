package domain;

import dao.implementations.PleasantDao;
import dao.implementations.UserDao;
import domain.entity.Comment;
import domain.entity.Pleasant;
import domain.entity.Post;
import domain.entity.User;
import servlet.entity.CommentView;
import servlet.entity.PleasantView;
import servlet.entity.PostView;
import servlet.entity.UserView;

import java.util.stream.Collectors;

public class EntityMapper {

   private static UserDao userDao = new UserDao();

    public static Post viewToPost(PostView postView) {
        Post post = new Post();
        post.setPicUrl(postView.getPicUrl());
        post.setPostText(postView.getPostText());
        post.setTitle(postView.getTag());
        post.setUsername(postView.getUsername());
        post.setLikeList(postView.getLikeList().stream().map(EntityMapper::viewToPleasant).collect(Collectors.toList()));
        post.setCommentList(postView.getCommentList().stream().map(EntityMapper::viewToComment).collect(Collectors.toList()));
        post.setId(postView.getId());
        return post;
    }

    public static PostView postToView(Post post) {
        PostView postView = new PostView();
        postView.setPicUrl(post.getPicUrl());
        postView.setPostText(post.getPostText());
        postView.setTag(post.getTitle());
        postView.setUsername(post.getUsername());
        postView.setLikeList(post.getLikeList().stream().map(EntityMapper::pleasantToView).collect(Collectors.toList()));
        postView.setCommentList(post.getCommentList().stream().map(EntityMapper::commentToView).collect(Collectors.toList()));
        postView.setId(post.getId());
        return postView;
    }

    public static User viewToUser(UserView userView){
        User user = new User();
        user.setNickname(userView.getUsername());
        user.setEmail(userView.getEmail());
        user.setPass(userView.getPassword());
        return user;
    }

    public static UserView userToView(User user){
        UserView userView = new UserView();
        userView.setUsername(user.getNickname());
        userView.setEmail(user.getEmail());
        userView.setPassword(user.getPass());
        return userView;
    }

    public static PleasantView pleasantToView(Pleasant pleasant){
        PleasantView pleasantView = new PleasantView();
        pleasantView.setPostId(String.valueOf(pleasant.getPostId()));

        UserDao userDao = new UserDao();
        String nickname = userDao.getById(String.valueOf(pleasant.getUserId())).get(0).getNickname();
        pleasantView.setUsername(nickname);
        return pleasantView;
    }

    public static Pleasant viewToPleasant (PleasantView pleasantView){
        Pleasant pleasant = new Pleasant();
        pleasant.setPostId(Integer.parseInt(pleasantView.getPostId()));

        int userId = userDao.getByUsername(pleasantView.getUsername()).get(0).getId();

        pleasant.setUserId(userId);
        return pleasant;
    }

    public static Comment viewToComment(CommentView commentView){
        Comment comment = new Comment();
        int userId = userDao.getByUsername(commentView.getNickname()).get(0).getId();
        comment.setUserId(userId);
        comment.setPostId(commentView.getPostId());
        comment.setCommentText(commentView.getCommentText());
        return comment;
    }

    public static CommentView commentToView(Comment comment){
        CommentView commentView = new CommentView();
        System.out.println(String.valueOf(comment.getUserId()));
        String nickname = userDao.getById(String.valueOf(comment.getUserId())).get(0).getNickname();
        commentView.setNickname(nickname);
        commentView.setPostId(comment.getPostId());
        commentView.setCommentText(comment.getCommentText());
        return commentView;
    }




}
