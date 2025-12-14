package FreeTask.static_final;

public class User {
    public static int userCount;
    public String userName;

    public User(String userName) {
        userCount++;
        this.userName = userName;
    }
    public static int getUserCount() {
        return userCount;
    }

    public static void main(String[] arg) {
        User user1 = new User("one");
        User user2= new User("two");
        System.out.println(User.getUserCount());
    }
}


