package FreeTask.static_final;

public class UserAccount {
    //  Логин — задаётся один раз и не меняется
    private final String login;

    // ✏️ Остальные поля — изменяемые
    private String name;
    private String email;

    // Конструктор: логин фиксируется навсегда
    public UserAccount(String login, String name) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Логин не может быть пустым");
        }
        this.login = login.trim();
        this.name = name; // можно null или пустой — по желанию
    }

    //  Геттер для логина (только чтение)
    public String getLogin() {
        return login;
    }

    // Геттеры и сеттеры для изменяемых полей
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Пример использования
    public static void main(String[] args) {
        UserAccount user = new UserAccount("alice123", "Алиса");
        System.out.println("Логин: " + user.getLogin()); // alice123
        System.out.println("Имя: " + user.getName());     // Алиса

        //  Можно менять имя и email
        user.setName("Алиса Иванова");
        user.setEmail("alice@example.com");

        //  Нельзя изменить логин — нет сеттера, и поле final
        // user.login = "new_login"; // ← Ошибка компиляции!
    }
}