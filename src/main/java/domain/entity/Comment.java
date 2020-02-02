package domain.entity;

import dao.TableName;

@TableName(name="Comment")
public class Comment  {
    private int userId;
    private int postId;
    private String addDate;
    private String commentText;
    private int id;

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", addDate='" + addDate + '\'' +
                ", commentText='" + commentText + '\'' +
                ", id=" + id +
                '}';
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
