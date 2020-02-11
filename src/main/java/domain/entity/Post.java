package domain.entity;

import dao.implementations.Mapper;
import dao.TableName;

import java.util.ArrayList;
import java.util.List;


@TableName(name="Post")
public class Post {
    private int id;
    private String postText;
    private String picUrl;
    private int userId;
    private String nickname;
    private String title;
    private String postDate;
    private String location;

    private List<Pleasant> likeList = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();
    private List<Tag> tagList = new ArrayList<>();

    private Mapper<Pleasant> likeListMapper = new Mapper<>(Pleasant.class);
    private Mapper<Comment> commentListMapper = new Mapper<>(Comment.class);
    private Mapper<Tag> tagListMapper = new Mapper<>(Tag.class);

    public Post(){
    }

    public void addLike(Pleasant like) {
        likeList.add(like);
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
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
        return nickname;
    }

    public void setUsername(String nickname) {
        this.nickname = nickname;
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
}
