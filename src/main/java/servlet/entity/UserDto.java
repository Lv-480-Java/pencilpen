package servlet.entity;

public class UserDto {

    private String username;
    private String password;
    private String email;
    private int level;
    private String userRole;

    public UserDto() {
    }

    public UserDto(String email, String username, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

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
