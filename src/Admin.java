import java.util.ArrayList;
import java.util.List;

class Admin {
    protected List<User> users;

    public Admin() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println(user.name + " has been added.");
    }

    public void displayUsers() {
        System.out.println("List of Users:");
        for (User user : users) {
            System.out.println(user.name + " (" + user.getClass().getSimpleName() + ")");
        }
    }
}