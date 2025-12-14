package FreeTask.static_final;

public class UserProfile {
    private final String PASPORT_NUMBER;
    private String userName;

    public UserProfile(String userName, String PASPORT_NUMBER) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }

        this.userName = userName;
        this.PASPORT_NUMBER = PASPORT_NUMBER;
    }


    public String getUserName() {
        return this.userName;
    }

}
