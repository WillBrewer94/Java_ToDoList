public class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userNameIn) {
        userName = userNameIn;
    }
}