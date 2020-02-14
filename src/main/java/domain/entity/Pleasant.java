package domain.entity;

import dao.TableName;

@TableName(name = "Pleasant")
public class Pleasant {
    private int userId;
    private int postId;
    private String likeDate;
    private int id;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Pleasant{" +
                "userId=" + userId +
                ", likeDate='" + likeDate + '\'' +
                ", id=" + id +
                '}';
    }

    public Pleasant() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(String likeDate) {
        this.likeDate = likeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
