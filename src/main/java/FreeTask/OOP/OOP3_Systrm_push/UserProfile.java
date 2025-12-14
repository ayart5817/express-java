package FreeTask.OOP.OOP3_Systrm_push;

public class UserProfile {
    UserPreferences userPreference;
    UserSetting userSetting;
    public UserProfile() {
        this.userPreference = new UserPreferences("general", "10");
        this.userSetting = new UserSetting("en", "light", "true");
    }

    public String getTheme() {
        return userSetting.getTheme();
    }
    public String getLanguage() {
        return userSetting.getLanguage();
    }
    public String getNotificationEnabled() {
        return userSetting.getNotificationEnabled();
    }

    public String getFavoriteCategory() {
        return userPreference.getFavoriteCategory();
    }
    public String getItemPerPage() {
        return userPreference.getItemPerPage();
    }
    public void setGeneral(String newGeneral) {
       userPreference.setFavoriteCategory(newGeneral);
    }
    public void setItemPerPage(String newItemPerPage) {
       userPreference.setItemPerPage(newItemPerPage);
    }
        public void setUserLanguage(String newLanguage) {
        userSetting.setLanguage(newLanguage);

        } public void setTheme(String newTheme) {
        userSetting.setTheme(newTheme);

        } public void setNotificationEnabled(String newNotificationEnabled) {
        userSetting.setNotificationEnabled(newNotificationEnabled);
        }
}
