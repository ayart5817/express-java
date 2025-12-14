package FreeTask.OOP.OOP3_Systrm_push;

public class UserPreferences {
    private String favoriteCategory;
    private String itemPerPage;

    public void setFavoriteCategory(String favoriteCategory) {
        this.favoriteCategory = favoriteCategory;
    }

    public void setItemPerPage(String itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public String getFavoriteCategory() {
        return favoriteCategory;
    }

    public String getItemPerPage() {
        return itemPerPage;
    }

    public UserPreferences(String favoriteCategory, String itemPerPage) {
        this.favoriteCategory = favoriteCategory;
        this.itemPerPage = itemPerPage;
    }
}
