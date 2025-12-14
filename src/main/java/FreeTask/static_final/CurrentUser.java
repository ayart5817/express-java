package FreeTask.static_final;

public class CurrentUser {
    private static User currentUser;

    private CurrentUser() {
        //Утилитарный класс
    }
    public static void setCurrentUser(User user) {
        CurrentUser.currentUser = user;
    }
    public static User getCurrentUser() {
        return currentUser;
    }

}
