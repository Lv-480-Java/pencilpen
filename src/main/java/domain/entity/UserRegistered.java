package domain.entity;

import java.util.ArrayList;
import java.util.List;

public class UserRegistered extends User {

    private int level;
    private List<Post> postList = new ArrayList<>();

    public UserRegistered(String nickname, String pass, String email) {
        super(nickname, pass, email);
    }

    public UserRegistered(int id, String nickname, String pass, String email, int level) {
        super(email,nickname,pass);
        this.level = level;
        setId(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getId() {
        return this.getId();
    }

    @Override
    public String getNickname() {
        return super.getNickname();
    }

    @Override
    public String getPass() {
        return super.getPass();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    public int getLevel() {
        return this.getLevel();
    }

    public List<Post> getPostList() {
        return this.getPostList();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setNickname(String nickname) {
        super.setNickname(nickname);
    }

    @Override
    public void setPass(String pass) {
        super.setPass(pass);
    }
}
