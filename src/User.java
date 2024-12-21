abstract class User {
    protected int id;
    protected String username;
    protected String password;
    protected String name;
    protected String phoneNumber;
    protected String email;

    public User(int id, String username, String password, String name, String phoneNumber, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public abstract void displayDashboard();
}
