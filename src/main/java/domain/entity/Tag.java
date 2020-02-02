package domain.entity;

import dao.TableName;


@TableName(name="Tag")
public class Tag {
    private String tagName;
    private int id;
    private int postId;

    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagName='" + tagName + '\'' +
                ", id=" + id +
                ", postId=" + postId +
                '}';
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
