package servlet.entity;

public class UserView {

    private String username;
    private String password;
    private String email;


    public UserView(String email, String username  ,String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public UserView(String username  ,String password) {
        this.username = username;
        this.password = password;
    }

    public UserView(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
