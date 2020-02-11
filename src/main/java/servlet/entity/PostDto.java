package servlet.entity;

import java.util.ArrayList;
import java.util.List;

public class PostDto {

    private int id;
    private String postText;
    private String picUrl;
    private String username;
    private String tag;

    private List<PleasantDto> likeList = new ArrayList<>();
    private List<CommentDto> commentList = new ArrayList<>();

    public List<PleasantDto> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<PleasantDto> likeList) {
        this.likeList = likeList;
    }

    public List<CommentDto> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDto> commentList) {
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
