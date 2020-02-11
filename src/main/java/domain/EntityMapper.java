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

public class EntityMapper {

    public static Post viewToPost(PostView postView) {
        Post post = new Post();
        post.setPicUrl(postView.getPicUrl());
        post.setPostText(postView.getPostText());
        post.setTitle(postView.getTag());
        post.setUsername(postView.getUsername());
        post.setLikeList(postView.getLikeList());
        post.setCommentList(postView.getCommentList());
        post.setId(postView.getId());
        return post;
    }

    public static PostView postToView(Post post) {
        PostView postView = new PostView();
        postView.setPicUrl(post.getPicUrl());
        postView.setPostText(post.getPostText());
        postView.setTag(post.getTitle());
        postView.setUsername(post.getUsername());
        postView.setLikeList(post.getLikeList());
        postView.setCommentList(post.getCommentList());
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

        UserDao userDao = new UserDao();
        int userId = userDao.getByUsername(pleasantView.getUsername()).get(0).getId();

        pleasant.setUserId(userId);
        return pleasant;
    }

    public static Comment viewToComment(CommentView commentView){
        Comment comment = new Comment();
        
    }




}
