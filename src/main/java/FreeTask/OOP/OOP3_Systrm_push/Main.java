package FreeTask.OOP.OOP3_Systrm_push;

public class Main {
    static void main() {
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setUserLanguage("RU");
        userProfile1.setNotificationEnabled("true");
        userProfile1.setTheme("Dark");
        userProfile1.setItemPerPage("20");
        System.out.println(userProfile1.getLanguage() +" "+ userProfile1.getItemPerPage()+" "+userProfile1.getFavoriteCategory()+" "+ userProfile1.getNotificationEnabled() +" "+ userProfile1.getTheme());
    }
}
