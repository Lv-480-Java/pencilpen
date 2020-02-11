package servlet.entity;

import domain.entity.Comment;
import domain.entity.Pleasant;
import domain.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class PostView {

    private int id;
    private String postText;
    private String picUrl;
    private String username;
    private String tag;

    private List<Pleasant> likeList = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();

    public List<Pleasant> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Pleasant> likeList) {
        this.likeList = likeList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "PostView{" +
                "id=" + id +
                ", postText='" + postText + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", username='" + username + '\'' +
                ", tag='" + tag + '\'' +
                ", likeList=" + likeList +
                ", commentList=" + commentList +
                '}';
    }
}