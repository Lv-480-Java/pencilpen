package domain.entity;

import dao.mapper.TableName;

@TableName(name="User")
public class User {

    private String email;
    private String nickname;
    private String pass;
    private int id;

    public User(String email, String nickname, String pass) {
        this.nickname = nickname;
        this.pass = pass;
        this.email = email;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
