package MOCTask2.Pavel;

/**
 *  есть саит пользователи есть ананимные – время и
 *  авторизованнеы - время и id
 *  корпоративными время id и корпорация
 *
 *  выводит все посещения
 *
 */

public abstract class User {
    private String time;

    public User(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public abstract void getInfo();
}
