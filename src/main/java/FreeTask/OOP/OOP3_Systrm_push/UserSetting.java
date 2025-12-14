package FreeTask.OOP.OOP3_Systrm_push;

public class UserSetting {
    private String theme;
    private String language;
    private String notificationEnabled;

    public UserSetting(String language, String theme, String notificationEnabled) {
        this.language = language;
        this.theme = theme;
        this.notificationEnabled = notificationEnabled;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setNotificationEnabled(String notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public String getTheme() {
        return theme;
    }

    public String getLanguage() {
        return language;
    }

    public String getNotificationEnabled() {
        return notificationEnabled;
    }
}
