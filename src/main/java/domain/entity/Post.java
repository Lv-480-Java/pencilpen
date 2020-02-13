package domain.entity;

import dao.Mapper;
import dao.TableName;

import java.util.ArrayList;
import java.util.List;


@TableName(name="Post")
public class Post {
    private int id;
    private String postText;
    private String picUrl;
    private int userId;
    private String username;
    private String title;
    private String postDate;
    private String location;
    private String isActive;

    private List<Pleasant> likeList ;
    private List<Comment> commentList ;

    private List<Tag> tagList = new ArrayList<>();

    private static Mapper<Pleasant> likeListMapper = new Mapper<>(Pleasant.class);
    private static Mapper<Comment> commentListMapper = new Mapper<>(Comment.class);
    private static Mapper<Tag> tagListMapper = new Mapper<>(Tag.class);

    public Post(){
    }



    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postText='" + postText + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", postDate='" + postDate + '\'' +
                ", location='" + location + '\'' +
                ", likeList=" + likeList.toString() +
                ", commentList=" + commentList.toString() +
                ", tagList=" + tagList.toString() +
                '}';
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public boolean getIsActive() {
        return Boolean.parseBoolean(isActive);
    }

    public void setIsActive(boolean isActive) {
        this.isActive = String.valueOf(isActive);
    }
}
