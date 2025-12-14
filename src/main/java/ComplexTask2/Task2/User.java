package ComplexTask2.Task2;


/**
 *  Общая модель пользователей
 *  поля -UserName -Email -Age
 *  globalFlag -validationEnabled - True (Валидация включена)
 *  custom Exception InvalidUserException
 *
 *
 *
 *
 */
public class User {
    private String userName;
    private String email;
    private int userAge;

    public User(String userName, String email, int userAge) {
        this.userName = userName;
        this.email = email;
        this.userAge = userAge;

    }

    public String getUserName() {
        return userName;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User – " + userName + '\'' +
                " email – " + email + '\'' +
                " userAge – " + userAge ;
    }

    public int getUserAge() {
        return userAge;
    }



}
